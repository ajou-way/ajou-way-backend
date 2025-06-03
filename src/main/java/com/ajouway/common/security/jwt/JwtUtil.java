package com.ajouway.common.security.jwt;

import com.ajouway.common.exception.JwtInvalidException;
import com.ajouway.domain.enums.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;

import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String REFRESH_TOKEN_HEADER = "X-Refresh-Token";

    public static final String CLAIM_ROLE = "role";
    public static final String CLAIM_USER_ID = "userId";

    // 나중에 환경변수 설정?
    public static final Long ACCESS_DURATION = 100000 * 1000 * 60L;
    public static final Long REFRESH_DURATION = 240 * 1000 * 60L;

    private final SecretKey accessSecret;
    private final SecretKey refreshSecret;

    public JwtUtil(@Value("${jwt.access-secret}") String accessSecretKey,
                   @Value("${jwt.refresh-secret}") String refreshSecretKey) {
        this.accessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessSecretKey));
        this.refreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshSecretKey));
    }

    public String generateAccessToken(final Long userId, final UserRole role) {
        return Jwts.builder()
                .claim(CLAIM_USER_ID, userId)
                .claim(CLAIM_ROLE, role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + ACCESS_DURATION))
                .signWith(accessSecret)
                .compact();
    }

//    public String createRefreshToken(final Long userId) {
//        return Jwts.builder()
//                .claim(CLAIM_USER_ID, userId)
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(new Date(System.currentTimeMillis() + REFRESH_DURATION))
//                .signWith(refreshSecret)
//                .compact();
//    }

    public Claims parseClaimsFromToken(final JwtType jwtType, final String token) {
        Claims claims = null;
        try {
            if (jwtType.equals(JwtType.ACCESS)) {
                claims = Jwts.parser().verifyWith(accessSecret).build().parseSignedClaims(token).getPayload();
            } else if (jwtType.equals(JwtType.REFRESH)) {
                claims = Jwts.parser().verifyWith(refreshSecret).build().parseSignedClaims(token).getPayload();
            }
            return claims;
        } catch (SignatureException signatureException) {
            throw new JwtInvalidException("signature key is different");
        } catch (ExpiredJwtException expiredJwtException) {
            throw new JwtInvalidException("expired token");
        } catch (MalformedJwtException malformedJwtException) {
            throw new JwtInvalidException("malformed token");
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new JwtInvalidException("using illegal argument like null");
        }
    }

    public Long getUserIdFromToken(final JwtType jwtType, final String token) {
        return (Long) parseClaimsFromToken(jwtType, token)
                .get(CLAIM_USER_ID);
    }
}

