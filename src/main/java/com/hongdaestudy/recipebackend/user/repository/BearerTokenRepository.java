package com.hongdaestudy.recipebackend.user.repository;

import com.hongdaestudy.recipebackend.user.domain.BearerToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BearerTokenRepository extends JpaRepository<BearerToken, Long> {
    Optional<BearerToken> findFirstByRefreshToken(String refreshToken);
    Optional<BearerToken> findFirstByAccessToken(String accessToken);
}
