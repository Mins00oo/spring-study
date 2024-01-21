package org.mycom.springstudy.common.exception;

import lombok.Getter;
import org.mycom.springstudy.common.ErrorCode;

@Getter
public class BaseException extends RuntimeException {
    private final ErrorCode errorCode;

    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
