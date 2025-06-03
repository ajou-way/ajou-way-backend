package com.ajouway.infra.persistence;

import com.ajouway.common.exception.CustomException;
import com.ajouway.common.exception.CustomExceptionInfo;
import com.ajouway.domain.enums.AuthProvider;
import com.ajouway.domain.repository.UserRepository;
import com.ajouway.storage.entity.user.UserEntity;
import com.ajouway.storage.repository.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public UserEntity getById(Long id) {
        return userJpaRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomExceptionInfo.NOT_FOUND_USER));
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userJpaRepository.save(user);
    }

    @Override
    public Optional<UserEntity> findByAuthProviderAndProviderId(AuthProvider authProvider, String providerId) {
        return userJpaRepository.findByAuthProviderAndProviderId(authProvider, providerId);
    }
}
