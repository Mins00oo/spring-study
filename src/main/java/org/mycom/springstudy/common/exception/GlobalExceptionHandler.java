package org.mycom.springstudy.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.mycom.springstudy.common.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> globalException(final BaseException exception) {
        log.error("Error occurs {}", exception.toString());
        return ResponseEntity.status(200)
                .body(new BaseResponse<>(exception.getStatusCode()));
    }
}
