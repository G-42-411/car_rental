package com.koko.controller;

import com.koko.dto.CommonResult;
import com.koko.pojo.Menu;
import com.koko.service.MenuService;
import com.koko.util.ServletTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 13629
 * @create 2021/3/5 23:17
 */
@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/menu")
    public CommonResult menu(){
        String username = (String) ServletTool.getRequest().getAttribute("username");
        List<Menu> menus = menuService.getMenuByCurrentUser(username);
        return CommonResult.ok(menus);
    }
}
