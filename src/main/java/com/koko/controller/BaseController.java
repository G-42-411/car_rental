package com.koko.controller;

import com.koko.pojo.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 13629
 * @create 2021/2/18 21:23
 */
@Slf4j
@RestController
public class BaseController {

    /**
     * 获取登录用户信息
     * @return
     */
    public LoginUser getLoginUser() {
        return (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
