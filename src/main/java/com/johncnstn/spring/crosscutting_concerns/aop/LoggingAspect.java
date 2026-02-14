package com.johncnstn.spring.crosscutting_concerns.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import static com.johncnstn.spring.crosscutting_concerns.utils.LoggingUtils.logObjectMetadata;

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
    // Pointcut -> "execution(* com.johncnstn.spring..*(..))" selects join points:
    // any method in classes directly under package com.johncnstn.spring.

    // Advice -> @Before means this advice runs before the matched method executes.
    // JoinPoint -> Each matched method execution is a join point where this advice is applied.
    @Before("execution(* com.johncnstn.spring.crosscutting_concerns.api.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        Object aspect = this;
        System.out.println("\n===Aspect (actually it's advice):===");
        logObjectMetadata(aspect);

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        System.out.println("\nCalling: " + className + "." + methodName);
        System.out.println("===endAspect (actually it's advice)===\n");

    }

}
