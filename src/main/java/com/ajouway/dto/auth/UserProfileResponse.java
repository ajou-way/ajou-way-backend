package com.ajouway.dto.auth;

import com.ajouway.domain.enums.UserRole;

public record UserProfileResponse(
        Long userId,
        UserRole userRole,
        String userName,
        String email
) {

    public static UserProfileResponse of(
            Long userId,
            UserRole userRole,
            String userName,
            String email
    ) {
        return new UserProfileResponse(userId, userRole, userName, email);
    }
}
