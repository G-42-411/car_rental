package com.koko.controller;

import com.koko.pojo.LoginUser;
import com.koko.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collection;
import java.util.Set;

/**
 * @author 13629
 * @create 2021/2/18 21:23
 */
@Slf4j
public class BaseController {

    /**
     * 获取登录用户信息
     * @return
     */
    public LoginUser getLoginUser() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
