package com.hongdaestudy.recipebackend.config.error.exception;

import com.hongdaestudy.recipebackend.config.error.ErrorCode;

public class DuplicationException extends RuntimeException {
  private ErrorCode errorCode;

  public DuplicationException(String message,ErrorCode errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

}