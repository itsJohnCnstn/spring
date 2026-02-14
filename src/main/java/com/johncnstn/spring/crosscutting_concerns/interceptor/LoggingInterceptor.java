package com.johncnstn.spring.crosscutting_concerns.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.johncnstn.spring.crosscutting_concerns.LoggingUtils.logObjectMetadata;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LoggingInterceptor interceptor = this;
        System.out.println("\n===Interceptor:===");
        logObjectMetadata(interceptor);
        System.out.println("===endInterceptor===\n");
        return true;
    }

}
