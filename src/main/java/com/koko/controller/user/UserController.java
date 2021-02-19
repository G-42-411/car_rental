package com.koko.controller.user;

import com.koko.controller.BaseController;
import com.koko.dto.CommonResult;
import com.koko.pojo.LoginUser;
import com.koko.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 13629
 * @create 2021/2/18 21:19
 */
@Slf4j
@RestController
public class UserController extends BaseController {

    @GetMapping("/getUserInfo")
    public CommonResult getUserInfo() {
        LoginUser loginUser = getLoginUser();
        Map<String, Object> map = new HashMap<>();
        map.put("user", loginUser.getUser());
        map.put("authorities", loginUser.getAuthorities());
        return CommonResult.ok(map);
    }
}
