package com.ajouway.dto.auth;

public record GoogleUserInfoResponse(
        String sub, // Google 고유 사용자 ID
        String email,
        boolean emailVerified,
        String name,
        String givenName,
        String familyName,
        String pictureUrl,
        String hostedDomain // Ex) ajou.ac.kr
) {
}