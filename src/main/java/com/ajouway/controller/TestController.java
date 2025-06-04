package com.ajouway.controller;

import com.ajouway.common.security.jwt.JwtUtil;
import com.ajouway.domain.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final JwtUtil jwtUtil;

    @GetMapping("/test/admin-token")
    public String getAdminToken() {
        return jwtUtil.generateAccessToken(1L, UserRole.ADMIN);
    }
}
