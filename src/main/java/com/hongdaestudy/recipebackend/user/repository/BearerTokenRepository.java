package com.hongdaestudy.recipebackend.user.repository;

import com.hongdaestudy.recipebackend.user.domain.BearerToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BearerTokenRepository extends JpaRepository<BearerToken, Long> {
    Optional<BearerToken> findByRefreshToken(String refreshToken);
    Optional<BearerToken> findByAccessToken(String accessToken);

    @Modifying
    @Query(value = "delete from BearerToken rt where rt.userId = :userId")
    void deleteAllByUserId(Long userId);
}
