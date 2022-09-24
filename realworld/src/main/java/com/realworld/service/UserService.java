package com.realworld.service;

/**
 * @author Taewoo
 */


import com.realworld.domain.user.User;
import com.realworld.domain.user.UserRepository;
import com.realworld.dto.user.login.LoginResponseDto;
import com.realworld.exception.EmailDuplicatedException;
import com.realworld.exception.InvalidAuthenticationException;
import com.realworld.exception.UserDuplicatedException;
import com.realworld.exception.UserNotFoundException;
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

    public LoginResponseDto loginUser(String email, String password) {
        var optionalUser = userRepository.findUserByEmail(email);

        if (!optionalUser.isPresent()) {
            log.warn("이메일이 틀렸습니다.");
            throw new UserNotFoundException();
        }

        if (passwordEncoder.matches(password, optionalUser.get().getPassword())) {
            var user = optionalUser.get();
            return LoginResponseDto.builder().email(user.getEmail()).username(user.getUsername()).bio(user.getBio()).token(jwtTokenUtils.buildToken(user.getUsername())).image(user.getImage()).build();
        }

        log.info("비밀번호가 틀렸습니다.");
        throw new InvalidAuthenticationException();
    }

    public LoginResponseDto registerUser(String username, String email, String password) {
        if (userRepository.existsUserByEmail(email)) {
            log.warn("중복된 이메일입니다." + email);
            throw new EmailDuplicatedException();
        }

        if (userRepository.existsUserByUsername(username)) {
            log.warn("중복된 이름입니다." + username);
            throw new UserDuplicatedException();
        }

        var user = userRepository.save(User.makeUser(email, username, password));
        return LoginResponseDto.builder()
                .email(user.getEmail())
                .token(jwtTokenUtils.buildToken(user.getUsername()))
                .username(user.getUsername())
                .bio(user.getBio())
                .image(user.getImage())
                .build();
    }
}










