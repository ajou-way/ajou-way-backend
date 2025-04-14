//package com.ajouway.controller.auth;
//
//import com.ajouway.common.security.jwt.CustomUserDetails;
//import com.ajouway.common.security.jwt.JwtUtil;
//import com.ajouway.dto.auth.JwtResponse;
//import com.ajouway.dto.auth.LoginRequest;
//import com.ajouway.dto.auth.SignupRequest;
//import com.ajouway.service.auth.AuthService;
//import com.ajouway.service.user.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/auth")
//public class AuthController {
//    private final AuthService authService;
//    private final UserService userService;
//
//    @PostMapping("/signup")
//    public void signup(@RequestBody SignupRequest request){
//        userService.registerUser(request);
//    }
//
//    @PostMapping("/ouath2/authorize/{provider}")
//    public String redirectToOAuth(@PathVariable String provider){
//        return authService.getOAuthRedirectUrl(provider);
//    }
//
//    @GetMapping("/oauth2/callback/{provider}")
//    public JwtResponse oauthLogin(@PathVariable String provider, @RequestParam("code") String authCode) {
//        return authService.authenticateOAuthUser(provider, authCode);
//    }
//
//    @PostMapping("/login")
//    public JwtResponse login(@RequestBody LoginRequest request) {
//        return authService.generateTokenForLogin(request);
//    }
//
//    @GetMapping("/reissue")
//    public JwtResponse reissueToken(@RequestHeader(JwtUtil.REFRESH_TOKEN_HEADER) String refreshToken) {
//        return authService.generateTokenForReissue(refreshToken);
//    }
//
//    @PostMapping("/logout")
//    public void logout(@AuthenticationPrincipal CustomUserDetails userDetails) {
//        final String loginId = userDetails.getUsername();
//        authService.deleteExistingRefreshToken(loginId);
//    }
//}