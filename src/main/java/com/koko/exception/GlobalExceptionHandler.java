package com.koko.exception;

import com.koko.util.ResultUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 13629
 * @create 2020/12/17 11:00
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 通用异常
     */
    @ExceptionHandler(Exception.class)
    public ResultUtil defaultException(Exception e) {
        log.error(e.getMessage(), e);
        return ResultUtil.error(500, e.getMessage());
    }

    /**
     * 用户未找到异常
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResultUtil usernameNotFoundException(UsernameNotFoundException e) {
        log.error(e.getMessage(), e);
        return ResultUtil.error(401, e.getMessage());
    }

    /**
     * 账户过期异常
     */
    @ExceptionHandler(AccountExpiredException.class)
    public ResultUtil accountExpiredException(AccountExpiredException e) {
        log.error(e.getMessage(), e);
        return ResultUtil.error(402, e.getMessage());
    }


}
