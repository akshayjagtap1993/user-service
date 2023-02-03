package com.ecommerce.userservice.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class SimpleFilter implements Filter {
    @Override public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if ("invalidToken".equalsIgnoreCase(httpServletRequest.getHeader("authToken"))) {
            throw new RuntimeException("Invalid authToken");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
