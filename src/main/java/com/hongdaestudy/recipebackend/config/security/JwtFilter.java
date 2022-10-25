package com.hongdaestudy.recipebackend.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hongdaestudy.recipebackend.config.error.ErrorCode;
import com.hongdaestudy.recipebackend.config.error.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // Request로 들어오는 JwtToken의 유효성을 검증(jwtTokenProvider.validateToken)하는 filter을 filter chain에 등록한다.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String path = request.getServletPath();

            if (!path.startsWith("/api/auth/refresh")) {
                String token = jwtTokenProvider.resolveToken(request);
                if(token != null && jwtTokenProvider.validateToken(token)) {
                    Authentication authentication = jwtTokenProvider.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            ErrorResponse res = ErrorResponse.of(ErrorCode.ACCESS_TOKEN_EXPIRED);
            response.getWriter().write(new ObjectMapper().writeValueAsString(res));
            response.getWriter().flush();
        }

    }
}
