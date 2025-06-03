package com.ajouway.controller.user;

import com.ajouway.common.security.jwt.CustomUserDetails;
import com.ajouway.domain.service.user.UserService;
import com.ajouway.dto.user.UserProfilePatchRequest;
import com.ajouway.dto.user.UserProfileResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@PreAuthorize("hasRole('USER')")
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public UserProfileResponse getProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUserId();
        return userService.getUserProfile(userId);
    }

    @PatchMapping("/profile")
    public void updateProfile(@AuthenticationPrincipal CustomUserDetails userDetails,
                                        @RequestBody @Valid UserProfilePatchRequest request) {
        Long userId = userDetails.getUserId();
        request.validate();
        userService.updateUserProfile(userId, request);
    }
}
