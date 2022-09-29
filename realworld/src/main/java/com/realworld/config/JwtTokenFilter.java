package com.realworld.config;

/**
 * @author Taewoo
 */


import com.realworld.domain.user.UserRepository;
import com.realworld.exception.InvalidAuthenticationException;
import com.realworld.service.token.JwtService;
import com.realworld.service.token.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    private String[] ignoringPath = {
            "/api/users/login" ,
            "/api/user"
    };

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        if (Arrays.stream(ignoringPath).anyMatch(x ->
                request.getRequestURI().equals(x))) {
            filterChain.doFilter(request, response);
        } else {
            log.info("JwtTokenFilter");
        }
        filterChain.doFilter(request, response);
    }
}











