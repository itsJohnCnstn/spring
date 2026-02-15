package com.johncnstn.spring.crosscutting_concerns.filter;

import com.johncnstn.spring.crosscutting_concerns.enums.AdviceType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.johncnstn.spring.crosscutting_concerns.utils.LoggingUtils.endOfCustomLogBlock;
import static com.johncnstn.spring.crosscutting_concerns.utils.LoggingUtils.logCrossCuttingConcernCallerMetadata;
import static com.johncnstn.spring.crosscutting_concerns.utils.LoggingUtils.startOfCustomLogBlock;

@Component
public class FilterImpl implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        String className = this.getClass().getSimpleName();
        startOfCustomLogBlock(className, AdviceType.BEFORE);

        FilterImpl filter = this;
        logCrossCuttingConcernCallerMetadata(filter);

        String endOfCustomLog = "";
        endOfCustomLogBlock(endOfCustomLog);

        filterChain.doFilter(servletRequest, servletResponse);

        startOfCustomLogBlock(className, AdviceType.AFTER);
        logCrossCuttingConcernCallerMetadata(filter);
        endOfCustomLogBlock(endOfCustomLog);
    }

}
