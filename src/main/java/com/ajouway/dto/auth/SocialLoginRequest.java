package com.ajouway.dto.auth;

import com.ajouway.domain.enums.AuthProvider;

public record SocialLoginRequest (
        AuthProvider provider,
        String accessToken
){
}
