package com.realworld.service;

/**
 * @author Taewoo
 */


import com.realworld.domain.user.User;
import com.realworld.domain.user.UserRepository;
import com.realworld.dto.user.UserResponseDto;
import com.realworld.exception.InvalidAuthenticationException;
import com.realworld.service.token.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;

    public UserResponseDto loginUser(String email, String password) {
        var optionalUser = userRepository.findUserByEmail(email);

        if (!optionalUser.isPresent()) {
            log.warn("이메일이 틀렸습니다.");
            throw new InvalidAuthenticationException();
        }

        if (passwordEncoder.matches(password, optionalUser.get().getPassword())) {
            var user = optionalUser.get();
            return UserResponseDto.builder().email(user.getEmail()).username(user.getUsername()).bio(user.getBio()).token(jwtTokenUtils.buildToken(user.getUsername())).image(user.getImage()).build();
        }

        log.info("비밀번호가 틀렸습니다.");
        throw new InvalidAuthenticationException();
    }

    public UserResponseDto registerUser(String username, String email, String password) {

        var user = userRepository.save(User.makeUser(email, username, passwordEncoder.encode(password)));

        return UserResponseDto.builder()
                .email(user.getEmail())
                .token(jwtTokenUtils.buildToken(user.getUsername()))
                .username(user.getUsername())
                .bio(user.getBio())
                .image(user.getImage())
                .build();
    }

    public UserResponseDto getUserWithToken(String token) {
        return jwtTokenUtils.getUserWithToken(token);
    }
}










