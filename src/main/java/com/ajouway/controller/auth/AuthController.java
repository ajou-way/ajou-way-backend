package com.ajouway.controller.auth;

import com.ajouway.common.security.jwt.CustomUserDetails;
import com.ajouway.domain.service.auth.AuthService;
import com.ajouway.dto.auth.JwtResponse;
import com.ajouway.dto.auth.SocialLoginRequest;
import com.ajouway.dto.auth.UserProfileIdResponse;
import com.ajouway.dto.auth.UserProfilePatchRequest;
import com.ajouway.dto.auth.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public JwtResponse signup(@RequestBody SocialLoginRequest request) {
        return authService.registerUser(request);
    }

    @PostMapping("/logout")
    public void logout(@AuthenticationPrincipal CustomUserDetails userDetails) {
        final String userId = userDetails.getUsername();
        authService.logout(userId);
    }

    @GetMapping("/profile")
    public UserProfileResponse getProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUserId();
        return authService.getUserProfile(userId);
    }

    @PatchMapping("/profile")
    public UserProfileIdResponse updateProfile(@AuthenticationPrincipal CustomUserDetails userDetails,
                                               @RequestBody UserProfilePatchRequest request) {
        Long userId = userDetails.getUserId();
        request.validate();

        return UserProfileIdResponse.of(authService.updateUserProfile(userId, request));
    }
}