package com.ajouway.dto.auth;

public record UserProfileIdResponse(
        Long userId
) {
    public static UserProfileIdResponse of(Long userId) {
        return new UserProfileIdResponse(userId);
    }
}
