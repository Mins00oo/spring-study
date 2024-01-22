package org.mycom.springstudy.common.exception;

import lombok.Getter;
import org.mycom.springstudy.common.StatusCode;

@Getter
public class BaseException extends RuntimeException {
    private final StatusCode statusCode;

    public BaseException(StatusCode statusCode) {
        super(statusCode.getMessage());
        this.statusCode = statusCode;
    }
}
