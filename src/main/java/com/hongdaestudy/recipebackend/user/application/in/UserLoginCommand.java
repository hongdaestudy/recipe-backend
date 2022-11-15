package com.hongdaestudy.recipebackend.user.application.in;

import lombok.Getter;

@Getter
public class UserLoginCommand {
    private String email;
    private String password;
}
