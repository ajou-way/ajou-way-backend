package com.ajouway.dto.auth;



public record JwtResponse(
        String accessToken
) {
    public static JwtResponse of(final String accessToken) {
        return new JwtResponse(accessToken);
    }
}