package com.realworld.exception.global;

/**
 * @author Taewoo
 */


import com.realworld.exception.EmailDuplicatedException;
import com.realworld.exception.InvalidAuthenticationException;
import com.realworld.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<?> illegalArgs() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<?> userNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("res", "유저를 찾을 수 없습니다."));
    }

    @ExceptionHandler(InvalidAuthenticationException.class)
    public ResponseEntity<?> invalidAuth() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(EmailDuplicatedException.class)
    public ResponseEntity<?> duplicatedEmail() {
        return ResponseEntity.status(UNPROCESSABLE_ENTITY).body("이메일이 중복되었습니다.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandling() {
        return ResponseEntity.badRequest().body("");
    }

}
