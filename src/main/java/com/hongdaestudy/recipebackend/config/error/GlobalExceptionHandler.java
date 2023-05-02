package com.hongdaestudy.recipebackend.config.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.sql.SQLIntegrityConstraintViolationException;

import static com.hongdaestudy.recipebackend.config.error.ErrorCode.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponse>  handleAccessDeniedException(AccessDeniedException e) {
        log.error("handleAccessDeniedException", e);
        final ErrorResponse response = ErrorResponse.of(HANDLE_ACCESS_DENIED);
        return new ResponseEntity<>(response, HttpStatus.valueOf(HANDLE_ACCESS_DENIED.getStatus()));
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    protected ResponseEntity<ErrorResponse> handleSQLViolationException(SQLIntegrityConstraintViolationException e) {
        log.error("handleSQLIntegrityConstraintViolationException: {}", e.getMessage());
        final ErrorResponse response = ErrorResponse.of(FOLLOW_DUPLICATION);
        return new ResponseEntity<>(response, HttpStatus.valueOf(FOLLOW_DUPLICATION.getStatus()));
    }
}
