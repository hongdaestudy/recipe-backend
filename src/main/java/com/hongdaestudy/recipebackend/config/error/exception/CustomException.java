package com.hongdaestudy.recipebackend.config.error.exception;

import com.hongdaestudy.recipebackend.config.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

	private final ErrorCode errorCode;
}
