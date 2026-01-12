package com.wjc.codetest._support.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommonMessage {

    SUCCESS_DELETE(HttpStatus.OK,"삭제 완료되었습니다."),

    FAIL_NO_RESULT(HttpStatus.NOT_FOUND,"해당 데이터가 존재하지 않습니다."),
    FAIL_VALID(HttpStatus.BAD_REQUEST,"데이터가 적합하지 않습니다"),
    FAIL_DUPLICATE(HttpStatus.CONFLICT, "이미 존재하는 제품이 있습니다."),
    FAIL_INTERNAL(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    CommonMessage(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
