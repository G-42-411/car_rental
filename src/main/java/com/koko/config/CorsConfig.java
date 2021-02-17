package com.koko.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author 13629
 * @create 2021/2/17 18:22
 */
@Configuration
public class CorsConfig extends WebMvcConfigurationSupport {

    /**
     * 跨域处理
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(3000);
    }
}
