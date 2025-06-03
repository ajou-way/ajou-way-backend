package com.ajouway.storage.repository.user;

import com.ajouway.domain.enums.AuthProvider;
import com.ajouway.storage.entity.user.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByAuthProviderAndProviderId(AuthProvider authProvider, String providerId);
}
