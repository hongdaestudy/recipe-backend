package com.hongdaestudy.recipebackend.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tokens {
    String accessToken;
    String refreshToken;
}
