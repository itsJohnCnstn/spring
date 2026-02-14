package com.johncnstn.spring.crosscutting_concerns.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.johncnstn.spring.crosscutting_concerns.LoggingUtils.logObjectMetadata;

@Component
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        LoggingFilter filter = this;

        System.out.println("\n===Filter:===");
        System.out.println("Before request");
        logObjectMetadata(filter);
        System.out.println("===endFilter===\n");

        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("\n===Filter:===");
        System.out.println("After request");
        logObjectMetadata(filter);
        System.out.println("===endFilter===\n");

    }

}
