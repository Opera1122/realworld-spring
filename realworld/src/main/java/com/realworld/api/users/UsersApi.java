package com.realworld.api.users;

/**
 * @author Taewoo
 */


import com.fasterxml.jackson.annotation.JsonProperty;
import com.realworld.dto.user.login.LoginRequestDto;
import com.realworld.dto.user.registry.UserRegisterDto;
import com.realworld.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/users")
public class UsersApi {

    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        log.info("회원가입 요청: " + userRegisterDto);
        var res = userService.registerUser(userRegisterDto.getUsername(), userRegisterDto.getEmail(), userRegisterDto.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        log.info("로그인 요청: " + loginRequestDto);

        var res = userService.loginUser(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
