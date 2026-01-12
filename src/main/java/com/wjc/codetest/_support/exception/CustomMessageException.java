package com.wjc.codetest._support.exception;

import com.wjc.codetest._support.common.CommonMessage;
import lombok.Getter;

@Getter
public class CustomMessageException extends RuntimeException {

    private final CommonMessage commonMessage;

    public CustomMessageException(CommonMessage commonMessage) {
        super(commonMessage.getMessage());
        this.commonMessage = commonMessage;
    }
}