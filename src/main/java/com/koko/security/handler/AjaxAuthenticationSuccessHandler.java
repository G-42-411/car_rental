package com.koko.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.koko.common.constants.Constants;
import com.koko.pojo.LoginUser;
import com.koko.util.JWTUtil;
import com.koko.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 处理用户登录成功
 * @author 13629
 * @create 2021/1/21 11:02
 */
@Slf4j
@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        HashMap<String, String> resultMap = new HashMap();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String token = jwtUtil.generate(loginUser.getUser().getName());
        resultMap.put("token", token);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.getWriter()
                .write(JSONObject.toJSONString(ResultUtils.ok(resultMap)));
    }
}
