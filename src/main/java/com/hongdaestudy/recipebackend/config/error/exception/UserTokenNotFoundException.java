package com.hongdaestudy.recipebackend.config.error.exception;

public class UserTokenNotFoundException extends RuntimeException {

    public UserTokenNotFoundException(String message) {
        super(message);
    }

}
