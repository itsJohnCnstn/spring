package com.johncnstn.spring.crosscutting_concerns.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.johncnstn.spring.crosscutting_concerns.utils.LoggingUtils.logCrossCuttingConcernCallerMetadata;

@Component
public class HandlerInterceptorImpl implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerInterceptorImpl interceptor = this;
        System.out.println("===Interceptor:===");
        logCrossCuttingConcernCallerMetadata(interceptor);

        if (handler instanceof HandlerMethod handlerMethod) {

            // TargetController isn’t being advised by any AOP pointcut, so Spring has no reason to wrap it
            // It was a CGLib proxy when there was a pointcut pointed
            Object beanClass = handlerMethod.getBean().getClass();
            String simpleName = handlerMethod.getBeanType().getSimpleName();
            String methodName = handlerMethod.getMethod().getName();
            System.out.println("### Target class:");
            System.out.println("beanClass: " + beanClass);
            System.out.println("beanSimpleName: " + simpleName);
            System.out.println("methodName: " + methodName);
            System.out.println("###");
        }

        System.out.println("===endInterceptor===");
        return true;
    }

}
