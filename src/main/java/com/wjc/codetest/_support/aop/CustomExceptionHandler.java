package com.wjc.codetest._support.aop;

import com.wjc.codetest._support.common.CommonMessage;
import com.wjc.codetest._support.common.CustomResponse;
import com.wjc.codetest._support.exception.CustomMessageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * 비즈니스 예외 (CommonMessage 기반)
     */
    @ExceptionHandler(CustomMessageException.class)
    public CustomResponse<String> handleCustomMessage(CustomMessageException e) {
        CommonMessage cm = e.getCommonMessage();
        log.warn("BusinessException: type={}, status={}, message={}",cm.name(), cm.getHttpStatus(), cm.getMessage());
        return CustomResponse.failed(cm.getHttpStatus(), cm.getMessage());
    }

    /**
     * 요청 검증/바인딩 예외 공통 처리
     */
    @ExceptionHandler({MethodArgumentNotValidException.class,BindException.class,ServletRequestBindingException.class})
    public CustomResponse<String> handleValidation(Exception e) {
        log.warn("ValidationException: {}", e.getMessage());
        return CustomResponse.failed(CommonMessage.FAIL_VALID.getHttpStatus(),CommonMessage.FAIL_VALID.getMessage());
    }

    /**
     * 예상치 못한 예외 (최후 처리)
     */
    @ExceptionHandler(Exception.class)
    public CustomResponse<String> handleUnexpected(Exception e) {
        log.error("UnexpectedException", e);
        return CustomResponse.failed(CommonMessage.FAIL_INTERNAL.getHttpStatus(), CommonMessage.FAIL_INTERNAL.getMessage());

    }

}
