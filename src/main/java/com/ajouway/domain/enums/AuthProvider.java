package com.ajouway.domain.enums;

import lombok.Getter;

@Getter
public enum AuthProvider {
    NONE("none"),    // 일반 로그인 (OAuth 사용 안 함)
    GOOGLE("google"),
    ;

    private final String name;

    AuthProvider(final String name) {
        this.name = name;
    }
}