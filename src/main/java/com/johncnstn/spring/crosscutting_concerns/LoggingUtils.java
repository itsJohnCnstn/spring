package com.johncnstn.spring.crosscutting_concerns;

import org.springframework.aop.framework.AopProxyUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public abstract class LoggingUtils {

    private LoggingUtils() {
    }

    public static void logObjectMetadata(Object object) {
        Instant now = Instant.now().truncatedTo(ChronoUnit.MILLIS);
        System.out.println("When: " + now);
        System.out.println("Runtime class: " + object.getClass().getName());
        System.out.println("Ultimate target class: " + AopProxyUtils.ultimateTargetClass(object).getName());
        System.out.println("Instance: " + object);
    }

}
