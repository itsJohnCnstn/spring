package com.johncnstn.spring.crosscutting_concerns.utils;

import com.johncnstn.spring.crosscutting_concerns.enums.AdviceType;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public abstract class LoggingUtils {

    private LoggingUtils() {
    }

    public static void startOfCustomLogBlock(String className) {
        System.out.println("\n===%s: %s===".formatted(className, AdviceType.BEFORE));
    }

    public static void logCrossCuttingConcernCallerMetadata(Object object) {
        Instant now = Instant.now().truncatedTo(ChronoUnit.MILLIS);
        System.out.println(now);
        System.out.println("---Cross-cutting concern caller metadata---");
        System.out.println("Class: " + object.getClass().getName());
        System.out.println("Instance: " + object);
    }

    public static void endOfCustomLogBlock(String endOfCustomLog) {
        System.out.println("---Target class metadata---");
        System.out.print(endOfCustomLog);
        System.out.println("==============================\n");
    }

}
