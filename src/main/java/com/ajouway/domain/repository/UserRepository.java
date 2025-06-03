package com.ajouway.domain.repository;

import com.ajouway.domain.enums.AuthProvider;
import com.ajouway.storage.entity.user.UserEntity;

import java.util.Optional;

public interface UserRepository {
    UserEntity getById(Long id);

    UserEntity save(UserEntity user);

    Optional<UserEntity> findByAuthProviderAndProviderId(AuthProvider authProvider, String providerId);
}
