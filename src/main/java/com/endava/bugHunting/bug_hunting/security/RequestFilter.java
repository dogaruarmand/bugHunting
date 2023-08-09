package com.endava.bugHunting.bug_hunting.security;

import com.endava.bugHunting.bug_hunting.exceptions.ApiError;
import com.endava.bugHunting.bug_hunting.exceptions.errors.NotLoggedException;
import com.endava.bugHunting.bug_hunting.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@Configuration
public class RequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String email = request.getHeader("email");
            if (userService.userAlreadyLogged(email)) {
                filterChain.doFilter(request, response);
            } else {
                String msg = String.format("User '%s' not logged in!", email);
                throw new NotLoggedException(msg);
            }
        } catch (NotLoggedException e) {
            ApiError errorResponse = new ApiError(HttpStatus.NOT_FOUND, e.getMessage());
            response.setContentType("application/json");
            response.setStatus(errorResponse.getStatus().value());
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.contains("/login") || path.contains("/register");
    }
}
