package com.koko.exception;

import com.koko.dto.CommonResult;
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
    public CommonResult defaultException(Exception e) {
        log.error(e.getMessage(), e);
        return CommonResult.error(500, e.getMessage());
    }

    /**
     * 用户未找到异常
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public CommonResult usernameNotFoundException(UsernameNotFoundException e) {
        log.error(e.getMessage(), e);
        return CommonResult.error(401, e.getMessage());
    }

    /**
     * 账户过期异常
     */
    @ExceptionHandler(AccountExpiredException.class)
    public CommonResult accountExpiredException(AccountExpiredException e) {
        log.error(e.getMessage(), e);
        return CommonResult.error(402, e.getMessage());
    }

    /**
     * 文件大小超出限制
     */
    @ExceptionHandler(FileSizeLimitExceededException.class)
    public CommonResult fileSizeLimitExceededException(FileSizeLimitExceededException e){
        log.error(e.getMessage(), e);
        return CommonResult.error(403, e.getMessage());
    }


}
