//package com.ajouway.service.auth;
//
//import com.ajouway.common.exception.CustomException;
//import com.ajouway.common.exception.CustomExceptionInfo;
//import com.ajouway.common.security.jwt.JwtType;
//import com.ajouway.common.security.jwt.JwtUtil;
//import com.ajouway.common.security.jwt.RedisUtil;
//import com.ajouway.domain.enums.ProviderType;
//import com.ajouway.domain.persistence.entity.user.User;
//import com.ajouway.domain.persistence.repository.UserRepository;
//import com.ajouway.dto.auth.JwtResponse;
//import com.ajouway.dto.auth.LoginRequest;
//import java.util.Collections;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional(readOnly = true)
//@RequiredArgsConstructor
//public class AuthService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtUtil jwtUtil;
//    private final RedisUtil redisUtil;
//
//
//    public User validateUserByPassword(final LoginRequest loginRequest) {
//        User user = userRepository.findByLoginIdOrThrow(loginRequest.loginId());
//        if (!passwordEncoder.matches(loginRequest.loginPw(), user.getPassword())) {
//            throw new CustomException(CustomExceptionInfo.INVALID_PASSWORD);
//        }
//        return user;
//    }
//
//    public JwtResponse generateTokenForLogin(final LoginRequest userLoginRequest) {
//        User user = validateUserByPassword(userLoginRequest);
//        return createJwtTokens(user);
//    }
//
//    //refresh
//    public JwtResponse generateTokenForReissue(final String refreshToken) {
//        Long userId = jwtUtil.getUserIdFromToken(JwtType.REFRESH, refreshToken);
//        User user = userRepository.findByIdOrThrow(userId);   //해당 user 존재 검증
//
//        redisUtil.validateRefreshToken(user.getEmail(), refreshToken);
//        deleteExistingRefreshToken(user.getEmail());
//        return createJwtTokens(user);
//    }
//
//    public void deleteExistingRefreshToken(final String loginId) {
//        redisUtil.deleteByKey(loginId);
//    }
//
//    //private
//    private JwtResponse createJwtTokens(final User user) {
//        String accessToken = jwtUtil.createAccessToken(user.getId(), user.getEmail(),
//                Collections.singletonList(user.getRole()));
//        String refreshToken = jwtUtil.createRefreshToken(user.getId());
//        redisUtil.save(user.getEmail(), refreshToken);
//        return JwtResponse.builder()
//                .grantType(JwtUtil.BEARER_PREFIX)
//                .accessToken(accessToken)
//                .refreshToken(refreshToken)
//                .build();
//    }
//
//    //OAuth
//    public String getOAuthRedirectUrl(final String provider) {
//        if (provider.equalsIgnoreCase(ProviderType.GOOGLE)) {
//            return "https://accounts.google.com/o/oauth2/v2/auth?...";
//        } else if ("kakao".equalsIgnoreCase(provider)) {
//            return "https://kauth.kakao.com/oauth/authorize?...";
//        }
//        throw new IllegalArgumentException("지원하지 않는 OAuth 제공자입니다.");
//    }
//}