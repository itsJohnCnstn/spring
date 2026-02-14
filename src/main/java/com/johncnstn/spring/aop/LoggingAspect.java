package com.johncnstn.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// Aspect -> This class groups cross-cutting logic (logging) that can run around business methods.
@Aspect
@Component // Aspect must be a Spring Managed bean
/*
    Another way to make it a managed Bean:

    @Configuration
    public class AopConfig {

        @Bean
        public LoggingAspect loggingAspect() {
            return new LoggingAspect();
        }
    }
 */
public class LoggingAspect {
    // Pointcut -> "execution(* com.johncnstn.spring.*.*(..))" selects join points:
    // any method in classes directly under package com.johncnstn.spring.

    // Advice -> @Before means this advice runs before the matched method executes.
    // JoinPoint -> Each matched method execution is a join point where this advice is applied.
    @Before("execution(* com.johncnstn.spring..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();

        System.out.println("Calling: " + className + "." + methodName);
    }

}
