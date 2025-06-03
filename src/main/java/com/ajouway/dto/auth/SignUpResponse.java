package com.ajouway.dto.auth;

import lombok.Builder;

@Builder
public record SignUpResponse(
        Long userId,
        JwtResponse jwt
) {
    public static SignUpResponse of(Long userId, String accessToken) {
        return SignUpResponse.builder()
                .userId(userId)
                .jwt(JwtResponse.of(accessToken))
                .build();
    }
}
