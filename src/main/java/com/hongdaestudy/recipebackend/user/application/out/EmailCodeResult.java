package com.hongdaestudy.recipebackend.user.application.out;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class EmailCodeResult {
    private String code;

    public EmailCodeResult(String code) {
        this.code = code;
    }

    public static EmailCodeResult from(String verifyCode) {
        return new EmailCodeResult(verifyCode);
    }
}
