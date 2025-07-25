package com.bx.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements  WebMvcConfigurer {

    @Resource
    private JwtInterceptor jwtInterceptor;

    // 加自定义拦截器JwtInterceptor，设置拦截规则
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/login")
                .excludePathPatterns("/sign")
                .excludePathPatterns("/files/**")
                .excludePathPatterns("/blog/selectTop","/blog/selectById/*","/blog/selectAll","/blog/selectPage")
                .excludePathPatterns("/activity/selectById/*","/activity/selectAll","/activity/selectPage");
                
    }
}
