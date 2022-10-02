package com.hongdaestudy.recipebackend.config.error;

public enum ErrorCode {
    HANDLE_ACCESS_DENIED(403, "C_001", "권한이 없습니다."),
    MEMBER_NOT_FOUND(400, "C_002", "사용자 정보를 찾을 수 없습니다.");

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
