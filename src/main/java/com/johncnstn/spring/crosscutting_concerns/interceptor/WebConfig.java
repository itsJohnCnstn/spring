package com.johncnstn.spring.crosscutting_concerns.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final HandlerInterceptorImpl handlerInterceptorImpl;

    public WebConfig(HandlerInterceptorImpl handlerInterceptorImpl) {
        this.handlerInterceptorImpl = handlerInterceptorImpl;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptorImpl);
    }
}
