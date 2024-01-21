package org.mycom.springstudy.common;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // Success
    SUCCESS(true, 100, "요청에 성공하였습니다."),

    // Common
    BAD_REQUEST(false, 200, "입력값이 잘못 되었습니다"),


    // USER
    ALREADY_EXIST(false, 300, "이미 존재하는 계정입니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    ErrorCode(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
