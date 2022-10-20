package com.rydzwr.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rydzwr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

@Service
@RequiredArgsConstructor
public class LoggedUsersFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final List<String> urls = asList("/api/v1/login", "/api/v1/logout");

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return (urls.contains(path));
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String headerName = "UserSessionCode";
        String sessionCode = request.getHeader(headerName);
        //logger.info(sessionCode);
        if (!userService.authoriseRequest(sessionCode)) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "Unauthorized");
        } else {
            chain.doFilter(request, response);
        }
    }
}
