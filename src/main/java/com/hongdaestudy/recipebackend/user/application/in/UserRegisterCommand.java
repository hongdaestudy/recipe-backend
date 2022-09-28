package com.hongdaestudy.recipebackend.user.application.in;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UserRegisterCommand {
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
