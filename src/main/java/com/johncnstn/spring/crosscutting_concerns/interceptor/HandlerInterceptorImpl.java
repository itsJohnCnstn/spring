package com.johncnstn.spring.crosscutting_concerns.interceptor;

import com.johncnstn.spring.crosscutting_concerns.enums.AdviceType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.johncnstn.spring.crosscutting_concerns.utils.LoggingUtils.endOfCustomLogBlock;
import static com.johncnstn.spring.crosscutting_concerns.utils.LoggingUtils.logCrossCuttingConcernCallerMetadata;
import static com.johncnstn.spring.crosscutting_concerns.utils.LoggingUtils.startOfCustomLogBlock;

@Component
public class HandlerInterceptorImpl implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String className = this.getClass().getSimpleName();
        startOfCustomLogBlock(className, AdviceType.BEFORE);

        HandlerInterceptorImpl interceptor = this;
        logCrossCuttingConcernCallerMetadata(interceptor);

        StringBuilder stringBuilder = new StringBuilder("");
        if (handler instanceof HandlerMethod handlerMethod) {

            // TargetController isn’t being advised by any AOP pointcut, so Spring has no reason to wrap it
            // It was a CGLib proxy when there was a pointcut pointed
            String beanClassName = handlerMethod.getBean().getClass().getName();
            String beanTypeSimpleName = handlerMethod.getBeanType().getSimpleName();
            String methodName = handlerMethod.getMethod().getName();

            stringBuilder.append("""
                    beanClassName: %s
                    beanTypeSimpleName: %s
                    methodName: %s
                    """.formatted(beanClassName, beanTypeSimpleName, methodName));
        }

        endOfCustomLogBlock(stringBuilder.toString());
        return true;
    }

}
