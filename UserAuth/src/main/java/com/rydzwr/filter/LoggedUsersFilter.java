package com.rydzwr.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Enumeration;

public class LoggedUsersFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(LoggedUsersFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOG.info("In Filter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Enumeration<String> headerNames = httpRequest.getHeaderNames();

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                System.out.println("Header: " + httpRequest.getHeader(headerNames.nextElement()));
            }
        }
        chain.doFilter(httpRequest, response);
    }
}
