package com.ajouway.storage.entity.user;

import com.ajouway.domain.enums.AuthProvider;
import com.ajouway.domain.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "\"user\"")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(name = "auth_provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    @Column(name = "provider_id", nullable = false)
    private String providerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "major")
    private String major;

    @Column(name = "student_id")
    private String studentId;

    @Builder
    public UserEntity(String email, AuthProvider authProvider, Long id, String name, String providerId, UserRole role,
                      String major, String studentId) {
        this.id = id;
        this.email = email;
        this.authProvider = authProvider;
        this.name = name;
        this.providerId = providerId;
        this.role = role;
        this.major = major;
        this.studentId = studentId;
    }

    public static UserEntity create(final String email, final String name, final AuthProvider authProvider,
                                    final String providerId, final UserRole role) {
        return UserEntity.builder()
                .email(email)
                .name(name)
                .authProvider(authProvider)
                .providerId(providerId)
                .name(name)
                .role(role)
                .build();
    }

    public void completeRegistration(String major, String studentId) {
        this.major = major;
        this.studentId = studentId;
        this.role = UserRole.ROLE_USER;
    }
}
