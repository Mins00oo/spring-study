package org.mycom.springstudy.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mycom.springstudy.common.ErrorCode;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private final ErrorCode errorCode;
}
