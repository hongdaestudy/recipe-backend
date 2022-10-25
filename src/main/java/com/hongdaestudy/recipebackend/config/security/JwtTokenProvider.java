package com.hongdaestudy.recipebackend.config.security;

import com.hongdaestudy.recipebackend.user.application.CustomUserDetailService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider { // JWT 토큰 검증 모듈

    private final CustomUserDetailService customUserDetailService;

    @Value("${jwt.secret}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // Request의 Header에서 token파싱
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public Claims decode(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Jwt 토큰으로 인증 정보를 조회
    public Authentication getAuthentication(String token) {
        Claims claims = decode(token.substring("Bearer ".length()));
        UserDetails userDetails = customUserDetailService.loadUserByUsername(String.valueOf(claims.get("email")));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // Jwt 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken.substring("Bearer ".length()));
            return !claims.getBody().getExpiration().before(new Date());// 만료시간이 현재시간보다 전인지 확인
        } catch (Exception e) {
            return false;
        }
    }
}
