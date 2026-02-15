package com.johncnstn.spring.crosscutting_concerns.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.stereotype.Component;

import static com.johncnstn.spring.crosscutting_concerns.utils.LoggingUtils.endOfCustomLogBlock;
import static com.johncnstn.spring.crosscutting_concerns.utils.LoggingUtils.logCrossCuttingConcernCallerMetadata;
import static com.johncnstn.spring.crosscutting_concerns.utils.LoggingUtils.startOfCustomLogBlock;

// Aspect -> This class groups cross-cutting logic (logging) that can run around business methods.
@Aspect
@Component // Aspect must be a Spring Managed bean
/*
    Another way to make it a managed Bean:

    @Configuration
    public class AopConfig {

        @Bean
        public DeclarativeAspect loggingAspect() {
            return new DeclarativeAspect();
        }
    }
 */
public class DeclarativeAspect {

    // Pointcut -> "execution(* com.johncnstn.spring.crosscutting_concerns.service.*.*(..))" selects join points:
    // any method in classes directly under package com.johncnstn.spring.

    // Advice -> @Before means this advice runs before the matched method executes.
    // JoinPoint -> Each matched method execution is a join point where this advice is applied.
    @Before("execution(* com.johncnstn.spring.crosscutting_concerns.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String className = this.getClass().getSimpleName();
        startOfCustomLogBlock(className);

        Object aspect = this;
        logCrossCuttingConcernCallerMetadata(aspect);

        Object targetObject = joinPoint.getThis();
        String runtimeJoinPointClassName = targetObject.getClass().getName();
        String ultimateTargetClassName = AopProxyUtils.ultimateTargetClass(targetObject).getName();
        String endOfCustomLog = """
                runtimeJoinPointClassName: %s
                ultimateTargetClassName: " %s
                """.formatted(runtimeJoinPointClassName, ultimateTargetClassName);
        endOfCustomLogBlock(endOfCustomLog);
    }

}
