package org.mycom.springstudy.common;

import lombok.Getter;

@Getter
public enum StatusCode {
    // Success
    SUCCESS(true, 100, "요청에 성공하였습니다."),

    // Common
    BAD_REQUEST(false, 200, "입력값이 잘못 되었습니다"),


    // USER
    ALREADY_EXIST(false, 300, "이미 존재하는 계정입니다."),
    NOT_VALID_PASSWORD(false, 301, "비밀번호 입력값이 잘못되었습니다."),
    NOT_VALID_EMAIL(false, 302, "해당 이메일의 계정을 찾을 수 없습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    StatusCode(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
