package com.qtfycg.common.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {
    private final Integer code;

    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
