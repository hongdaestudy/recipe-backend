package com.hongdaestudy.recipebackend.user.application.out;

import com.hongdaestudy.recipebackend.user.domain.Tokens;
import com.hongdaestudy.recipebackend.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRegisterCommandResult {
    private long userId;
    private String accessToken;
    private String refreshToken;

    public static UserRegisterCommandResult from(User user, Tokens tokens){
        return new UserRegisterCommandResult(user.getId(), tokens.getAccessToken(), tokens.getRefreshToken());
    }
}
