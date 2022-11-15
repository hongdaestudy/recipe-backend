package com.hongdaestudy.recipebackend.config.security;

import com.hongdaestudy.recipebackend.config.error.exception.UserTokenNotFoundException;
import com.hongdaestudy.recipebackend.user.domain.*;
import com.hongdaestudy.recipebackend.user.repository.BearerTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenGenerator {
    private final Long accessTokenValidSeconds;
    private final Long refreshTokenValidSeconds;
    private final String jwtSecret;
    private final Key key;
    private final BearerTokenRepository bearerTokenRepository;

    public JwtTokenGenerator(@Value("${jwt.access-token-valid-seconds}") Long accessTokenValidSeconds,
                             @Value("${jwt.refresh-token-valid-seconds}") Long refreshTokenValidSeconds,
                             @Value("${jwt.secret}")  String jwtSecret,
                             BearerTokenRepository bearerTokenRepository) {
        this.accessTokenValidSeconds = accessTokenValidSeconds;
        this.refreshTokenValidSeconds = refreshTokenValidSeconds;
        this.jwtSecret = jwtSecret;
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        this.bearerTokenRepository = bearerTokenRepository;
    }

    public Tokens create(User user, UserProfile userProfile) {
        long nowInMilliseconds = new Date().getTime();
        String accessToken = createAccessToken(String.valueOf(user.getId()),
                String.valueOf(userProfile.getId()),
                user.getEmail(),
                "ROLE_USER",
                new Date(nowInMilliseconds + accessTokenValidSeconds * 1000));
        String refreshToken = createRefreshToken(new Date(nowInMilliseconds + refreshTokenValidSeconds * 1000));
        bearerTokenRepository.deleteAllByUserId(user.getId());
        bearerTokenRepository.save(new BearerToken(user.getId(), userProfile.getId(), refreshToken, accessToken));
        return new Tokens(accessToken, refreshToken);
    }

    public Tokens exchangeAccessToken(User user,UserProfile profile, String accessToken) {
        BearerToken token = bearerTokenRepository.findByAccessToken(accessToken)
                .orElseThrow(() -> new UserTokenNotFoundException("accessToken not found. token : " + accessToken));
        long nowInMilliseconds = new Date().getTime();
        String changedAccessToken = createAccessToken(String.valueOf(user.getId()),
                String.valueOf(profile.getId()),
                user.getEmail(),
                "ROLE_USER",
                new Date(nowInMilliseconds + accessTokenValidSeconds * 1000));
        token.exchangeAccessToken(changedAccessToken);
        bearerTokenRepository.save(token);
        return new Tokens(changedAccessToken, token.getRefreshToken());
    }

    private String createAccessToken(String userId, String userProfileId, String email, String role, Date expiry) {
        Claims claims = Jwts.claims().setSubject(userId).setExpiration(expiry).setIssuedAt(new Date());
        claims.put("email", email);
        claims.put("role", role);
        claims.put("userProfileId", userProfileId);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(key)
                .compact();
    }

    private String createRefreshToken(Date expiry) {
        return Jwts.builder()
                .setClaims(Jwts.claims().setExpiration(expiry).setIssuedAt(new Date()))
                .signWith(key)
                .compact();
    }
}
