package com.hongdaestudy.recipebackend.user.application.in;

import lombok.Getter;

@Getter
public class EmailCodeCommand {
    private String email;
    private String code;
}
