package com.rydzwr.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rydzwr.exception.UnauthorizedCallException;
import com.rydzwr.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@Service
public class LoggedUsersFilter extends OncePerRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(LoggedUsersFilter.class);

    private final UserService userService;

    public LoggedUsersFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return ("/api/v1/login".equals(path) || "/api/v1/logout".equals(path));
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String sessionCode = request.getHeader("UserSessionCode");
        if (!userService.authoriseRequest(sessionCode))
            response.sendError(HttpStatus.FORBIDDEN.value(), "Unauthorized");
        else
            chain.doFilter(request, response);
    }
}
