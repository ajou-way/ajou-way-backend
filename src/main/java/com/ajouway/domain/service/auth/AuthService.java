package com.ajouway.domain.service.auth;

import com.ajouway.common.security.jwt.JwtUtil;
import com.ajouway.domain.enums.AuthProvider;
import com.ajouway.domain.enums.UserRole;
import com.ajouway.domain.repository.UserRepository;
import com.ajouway.dto.auth.GoogleUserInfoResponse;
import com.ajouway.dto.auth.JwtResponse;
import com.ajouway.dto.auth.SignUpResponse;
import com.ajouway.dto.auth.SocialLoginRequest;
import com.ajouway.dto.user.UserProfilePatchRequest;
import com.ajouway.storage.entity.user.UserEntity;
import com.ajouway.storage.repository.user.UserJpaRepository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    private static final RestTemplate restTemplate = new RestTemplate();

    @Transactional
    public JwtResponse registerUser(final SocialLoginRequest request) {
        // 소셜 유저의 식별자 가져오기 (예: 구글 userId) ← 실제로는 OAuth 클라이언트를 통해 확인
        AuthProvider provider = request.provider();
        String accessToken = request.accessToken();
        System.out.println("Access Token: " + accessToken);
        ResponseEntity<GoogleUserInfoResponse> response = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v3/userinfo",
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(accessToken)),
                GoogleUserInfoResponse.class
        );
        GoogleUserInfoResponse userInfo = response.getBody();
        if (userInfo == null || userInfo.sub() == null) {
            throw new IllegalArgumentException("Invalid access token or user info not found");
        }

        // 기존 유저 있는지 확인
        String providerId = userInfo.sub();
        Optional<UserEntity> optionalUser = userRepository.findByAuthProviderAndProviderId(provider, providerId);

        // 기존 유저가 있다면 JWT 토큰 생성 후 반환
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            String jwtAccessToken = jwtUtil.generateAccessToken(user.getId(), UserRole.USER);
            log.info("[USER LOGIN] User already exists: {}", user.getEmail());
            return JwtResponse.of(jwtAccessToken);
        }

        // 새로운 TEMP 유저 등록
        UserEntity newUser = UserEntity.create(
                userInfo.email(),
                userInfo.name(),
                provider,
                providerId,
                UserRole.SOCIAL
        );
        userRepository.save(newUser);

        String jwtAccessToken = jwtUtil.generateAccessToken(newUser.getId(), UserRole.SOCIAL);
        log.info("[USER REGISTER] New user registered: {}", newUser.getEmail());
        return JwtResponse.of(jwtAccessToken);
    }


    public void logout(String userId) {
        // TODO : 로그아웃 로직 구현
    }

    @Transactional
    public SignUpResponse signUp(Long userId, UserProfilePatchRequest request) {
        UserEntity user = userRepository.getById(userId);
        user.completeRegistration(request.major(), request.studentId());
        userRepository.save(user);
        return SignUpResponse.of(user.getId(),jwtUtil.generateAccessToken(user.getId(), user.getRole()));
    }

    // private
    private HttpHeaders createHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        return headers;
    }
}