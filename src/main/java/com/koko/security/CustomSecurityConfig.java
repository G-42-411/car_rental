package com.koko.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author 13629
 * @create 2020/12/17 14:41
 */
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    //配置用户认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }


    //配置静态资源
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    //配置访问控制
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
