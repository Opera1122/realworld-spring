package com.realworld.service.token;

/**
 * @author Taewoo
 */


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.realworld.domain.user.User;
import com.realworld.domain.user.UserRepository;
import com.realworld.dto.user.UserResponseDto;
import com.realworld.exception.InvalidAuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtTokenUtils {

    private final UserRepository userRepository;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    public String buildToken(String claims) {
        return "Bearer " + JWT.create().withSubject(claims).withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY)).withClaim("username", claims).sign(Algorithm.HMAC256(secret.getBytes()));
    }

    public UserResponseDto getUserWithToken(String token) {
        var username = JWT.decode(token).getClaim("username").asString();

        var optionalUser = userRepository.findUserByUsername(username);

        if (!optionalUser.isPresent())
            throw new InvalidAuthenticationException();

        var user = optionalUser.get();

        return UserResponseDto.builder().email(user.getEmail()).token(this.buildToken(user.getUsername())).username(user.getUsername()).bio(user.getBio()).image(user.getImage()).build();
    }

}






