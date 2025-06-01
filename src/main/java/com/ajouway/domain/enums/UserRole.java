package com.ajouway.domain.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ROLE_SOCIAL("social"),
    ROLE_USER("user"),
    ROLE_ADMIN("admin");

    private final String role;

    UserRole(final String role){
        this.role = role;
    }
}