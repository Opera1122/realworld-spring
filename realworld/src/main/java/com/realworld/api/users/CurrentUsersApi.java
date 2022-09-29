package com.realworld.api.users;

/**
 * @author Taewoo
 */


import com.realworld.domain.user.User;
import com.realworld.dto.user.login.LoginRequestDto;
import com.realworld.exception.InvalidAuthenticationException;
import com.realworld.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
@Slf4j
public class CurrentUsersApi {

    private final UserService userService;

    @PostMapping("/users/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        log.info("로그인 요청: " + loginRequestDto);
        var res = userService.loginUser(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(
            @AuthenticationPrincipal User user,
            @RequestHeader("Authorization") String authorization,
            HttpServletRequest request) {

        log.info(request.getRequestURI());

        log.info("authorization: " + authorization);
        log.info("user: " + user.toString());

        if (authorization == null || (!authorization.startsWith("Bearer ")))
            throw new InvalidAuthenticationException();

        return ResponseEntity.ok(userService.getUserWithToken(authorization));
    }
}
















