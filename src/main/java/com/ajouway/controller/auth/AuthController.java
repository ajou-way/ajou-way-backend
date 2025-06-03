package com.ajouway.controller.auth;

import com.ajouway.common.security.jwt.CustomUserDetails;
import com.ajouway.domain.service.auth.AuthService;
import com.ajouway.domain.service.user.UserService;
import com.ajouway.dto.auth.JwtResponse;
import com.ajouway.dto.auth.SocialLoginRequest;
import com.ajouway.dto.auth.SignUpResponse;
import com.ajouway.dto.user.UserProfilePatchRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody @Valid SocialLoginRequest request) {
        return authService.registerUser(request);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/logout")
    public void logout(@AuthenticationPrincipal CustomUserDetails userDetails) {
        final String userId = userDetails.getUsername();
        authService.logout(userId);
    }

    @PreAuthorize("hasAnyRole('SOCIAL')")
    @PutMapping("/sign-up")
    public SignUpResponse signUp(@AuthenticationPrincipal CustomUserDetails userDetails,
                                 @RequestBody @Valid UserProfilePatchRequest request) {
        Long userId = userDetails.getUserId();
        request.validate();
        return authService.signUp(userId, request);
    }
}
