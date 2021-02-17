package com.koko.security;

import com.koko.security.filter.JwtAuthenticationTokenFilter;
import com.koko.security.handler.AjaxAuthenticationEntryPoint;
import com.koko.security.handler.AjaxAuthenticationFailureHandler;
import com.koko.security.handler.AjaxAuthenticationSuccessHandler;
import com.koko.security.handler.AjaxLogoutSuccessHandler;
import com.koko.service.impl.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author 13629
 * @create 2020/12/17 14:41
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AjaxAuthenticationEntryPoint ajaxAuthenticationEntryPoint;

    @Autowired
    private AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;

    @Autowired
    private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;

    @Autowired
    private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    /**
     * 配置用户认证
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getBCryptPasswordEncoder());
    }

    /**
     * 加密方式
     */
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 配置静态资源
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    /**
     * 配置访问控制
     * <p>
     * anyRequest          |   匹配所有请求路径
     * access              |   SpringEl表达式结果为true时可以访问
     * anonymous           |   匿名可以访问
     * denyAll             |   用户不能访问
     * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
     * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
     * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
     * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
     * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
     * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
     * permitAll           |   用户可以任意访问
     * rememberMe          |   允许通过remember-me登录的用户访问
     * authenticated       |   用户登录后可访问
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //关闭session
                .and()

                .httpBasic().authenticationEntryPoint(ajaxAuthenticationEntryPoint)

                .and()
                .authorizeRequests()    //所有请求都需要认证

                .and()
                .formLogin()    //开启登录
//                .loginPage("")
//                .loginProcessingUrl("")
                .successHandler(ajaxAuthenticationSuccessHandler)   //登录成功
                .failureHandler(ajaxAuthenticationFailureHandler)   //登录失败
                .permitAll()

                .and()
                .logout()
//                .logoutUrl("")
                .logoutSuccessHandler(ajaxLogoutSuccessHandler)     //退出成功
                .permitAll();

        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class); //token过滤器
    }
}
