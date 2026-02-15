package com.johncnstn.spring.crosscutting_concerns.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.johncnstn.spring.crosscutting_concerns.utils.LoggingUtils.logObjectMetadata;

@Component
public class FilterImpl implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        FilterImpl filter = this;

        System.out.println("\n===Filter:===");
        System.out.println("I'm just a filter. I don't have a target class.");
        System.out.println("Before request");
        logObjectMetadata(filter);
        System.out.println("===endFilter===\n");

        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("\n===Filter:===");
        System.out.println("I'm just a filter. I don't have a target class.");
        System.out.println("After request");
        logObjectMetadata(filter);
        System.out.println("===endFilter===\n");

    }

}
