package com.qtfycg.common.exception;

import com.qtfycg.common.utils.R;
import com.qtfycg.common.utils.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public R handleCustomException(CustomException ex) {
        log.error("业务异常: {}", ex.getMessage());
        return R.error().code(ex.getCode()).message(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleValidationException(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldError().getDefaultMessage();
        log.error("参数校验异常: {}", msg);
        return R.error().code(ResultCodeEnum.VALIDATE_ERROR.getCode()).message(msg);
    }

    @ExceptionHandler(Exception.class)
    public R handleUnknownException(Exception ex) {
        log.error("系统异常: ", ex);
        return R.error().message("系统繁忙，请稍后再试");
    }
}
