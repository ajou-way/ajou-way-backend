package com.ajouway.dto.auth;

import com.ajouway.domain.enums.AuthProvider;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

public record SocialLoginRequest (
        @NonNull
        AuthProvider provider,

        @NotBlank
        String accessToken
){
}
