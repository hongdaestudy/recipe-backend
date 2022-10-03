package com.hongdaestudy.recipebackend.user.domain;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BearerToken {
    public static final String ACCESS_TOKEN_CACHE_KEY = "access.token";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bearer_token_id")
    private Long id;

    private Long userId;

    private Long userProfileId;

    private String refreshToken;

    private String accessToken;

    public BearerToken(Long userId, Long userProfileId, String refreshToken, String accessToken) {
        this.userId = userId;
        this.userProfileId = userProfileId;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }

    public void exchangeAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
