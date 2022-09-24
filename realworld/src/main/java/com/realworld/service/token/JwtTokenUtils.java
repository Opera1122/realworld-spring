package com.realworld.service.token;

/**
 * @author Taewoo
 */


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class JwtTokenUtils {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    public String buildToken(String claims) {
        return JWT.create()
                .withSubject(claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .withClaim("username", claims)
                .sign(Algorithm.HMAC256(secret.getBytes()));
    }
}
