package com.koko.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 13629
 * @create 2020/12/21 15:15
 */
@Slf4j
@RestController
public class TestController {

    @GetMapping("/checkLogin")
    public String checkLogin() {
        return "login success";
    }
}
