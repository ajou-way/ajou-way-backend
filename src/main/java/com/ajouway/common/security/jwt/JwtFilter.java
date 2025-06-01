package com.ajouway.common.security.jwt;

import static com.ajouway.common.security.jwt.JwtUtil.AUTHORIZATION_HEADER;
import static com.ajouway.common.security.jwt.JwtUtil.BEARER_PREFIX;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws IOException, ServletException {
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        log.info("JwtFilter - Authorization Header: {}", authorization);

        if (authorization == null) {
            log.error("Authorization header is missing or empty");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String accessToken = extractAccessTokenFromAuthorization(authorization);
            Claims claims = jwtUtil.parseClaimsFromToken(JwtType.ACCESS, accessToken);
            CustomUserDetails userDetails = new CustomUserDetails(claims);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception exception) {
            log.error("JWT 인증 실패: {}", exception.getMessage());
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\":\"Unauthorized: " + exception.getMessage() + "\"}");
            return;
        }
        filterChain.doFilter(request, response);
    }

    private String extractAccessTokenFromAuthorization(final String authorization) {
        log.info("extractAccessTokenFromAuthorization - Authorization: {}", authorization);
        if (StringUtils.hasText(authorization) && authorization.startsWith(BEARER_PREFIX)) {
            return authorization.substring(7);
        }
        return null;
    }
}
