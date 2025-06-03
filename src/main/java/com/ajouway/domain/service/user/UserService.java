package com.ajouway.domain.service.user;

import com.ajouway.common.exception.CustomException;
import com.ajouway.common.exception.CustomExceptionInfo;
import com.ajouway.domain.repository.UserRepository;
import com.ajouway.dto.user.UserProfilePatchRequest;
import com.ajouway.dto.user.UserProfileResponse;
import com.ajouway.storage.entity.user.UserEntity;
import com.ajouway.storage.repository.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long updateUserProfile(Long userId, UserProfilePatchRequest request) {
        UserEntity user = userRepository.getById(userId);
        user.completeRegistration(request.major(), request.studentId());
        userRepository.save(user);
        return user.getId();
    }

    public UserProfileResponse getUserProfile(Long userId) {
        UserEntity user = userRepository.getById(userId);
        return UserProfileResponse.of(
                user.getId(),
                user.getRole(),
                user.getName(),
                user.getEmail()
        );
    }
}