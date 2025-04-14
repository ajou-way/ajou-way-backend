//package com.ajouway.domain.persistence.entity.user;
//
//import com.ajouway.domain.enums.ProviderType;
//import com.ajouway.domain.enums.UserRole;
//import com.ajouway.domain.persistence.entity.BaseTimeEntity;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.AccessLevel;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Table(name = "\"user\"")
//public class User extends BaseTimeEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id")
//    private Long id;
//
//    @Column(nullable = false, unique = true)
//    private String email;
//
//    private String password;
//
//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private ProviderType providerType;
//
//    private String providerId;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private UserRole role;
//
//    @Builder
//    private User(
//            final String email, final String password, final ProviderType providerType, final String providerId,
//            final String name, final UserRole role
//    ) {
//        this.email = email;
//        this.password = password;
//        this.providerId = providerId;
//        this.providerType = providerType;
//        this.name = name;
//        this.role = role;
//    }
//
//    public static User from(final String email, final String password, final ProviderType providerType,
//                            final String providerId, final String name, final UserRole role) {
//        return User.builder()
//                .email(email)
//                .password(password)
//                .providerId(providerId)
//                .providerType(providerType)
//                .name(name)
//                .role(role)
//                .build();
//    }
//    public static User from(final String email, final String password, final String name, final UserRole role) {
//        return User.builder()
//                .email(email)
//                .password(password)
//                .providerType(ProviderType.NONE)
//                .name(name)
//                .role(role)
//                .build();
//    }
//}
