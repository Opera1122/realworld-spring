package com.realworld.service.token;

/**
 * @author Taewoo
 */


import com.realworld.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DefaultJwtService implements JwtService {

    private final JwtTokenUtils jwtTokenUtils;


    @Override
    public String toToken(User user) {
        return null;
    }

    @Override
    public Optional<Long> getSubFromToken(String token) {
        return null;
    }
}
