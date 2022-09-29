package com.realworld.exception.global;

/**
 * @author Taewoo
 */


import com.realworld.exception.ErrorResponse;
import com.realworld.exception.InvalidAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e) {

        List<ErrorResponse> errorResources =
                e.getBindingResult().getFieldErrors().stream()
                        .map(
                                fieldError ->
                                        new ErrorResponse(
                                                fieldError.getObjectName(),
                                                fieldError.getField(),
                                                fieldError.getCode(),
                                                fieldError.getDefaultMessage()))
                        .collect(Collectors.toList());

        return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(errorResources);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandling(Exception e) {
        log.info(e.getLocalizedMessage());
        return ResponseEntity.badRequest().body("...");
    }

}
