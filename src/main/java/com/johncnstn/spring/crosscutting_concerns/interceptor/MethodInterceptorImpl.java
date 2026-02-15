package com.johncnstn.spring.crosscutting_concerns.interceptor;

import com.johncnstn.spring.crosscutting_concerns.enums.AdviceType;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jspecify.annotations.Nullable;

import static com.johncnstn.spring.crosscutting_concerns.utils.LoggingUtils.endOfCustomLogBlock;
import static com.johncnstn.spring.crosscutting_concerns.utils.LoggingUtils.logCrossCuttingConcernCallerMetadata;
import static com.johncnstn.spring.crosscutting_concerns.utils.LoggingUtils.startOfCustomLogBlock;

public class MethodInterceptorImpl implements MethodInterceptor {
    @Override
    public @Nullable Object invoke(MethodInvocation invocation) throws Throwable {
        MethodInterceptorImpl methodInterceptor = this;
        String className = methodInterceptor.getClass().getSimpleName();
        startOfCustomLogBlock(className, AdviceType.BEFORE);

        logCrossCuttingConcernCallerMetadata(methodInterceptor);

        var method = invocation.getMethod();
        var target = invocation.getThis();
        String targetClassName = target.getClass().getName();
        String methodName = method.getName();
        String endOfLog = """
                target: %s
                targetClassName: %s
                methodName: %s
                """.formatted(target, targetClassName, methodName);
        endOfCustomLogBlock(endOfLog);

        try {
            Object result = invocation.proceed();

            startOfCustomLogBlock(className, AdviceType.AFTER);
            logCrossCuttingConcernCallerMetadata(methodInterceptor);
            String endOfLogAfter = new StringBuilder(endOfLog)
                    .append("invocation.proceed() result: ").append(result)
                    .append("\n")
                    .toString();
            endOfCustomLogBlock(endOfLogAfter);
            return result;
        } catch (Throwable t) {
            System.out.println("===MethodInterceptor: ERROR===");
            System.out.println("ex: " + t);
            System.out.println("==============================");
            throw t;
        }
    }
}
