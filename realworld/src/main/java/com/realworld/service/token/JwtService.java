package com.realworld.service.token;

import com.realworld.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Taewoo
 */

@Service
public interface JwtService {
    String toToken(User user);

    Optional<Long> getSubFromToken(String token);
}