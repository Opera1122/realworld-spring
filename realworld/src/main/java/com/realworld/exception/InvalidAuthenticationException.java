package com.realworld.exception;

/**
 * @author Taewoo
 */


import java.util.*;

public class InvalidAuthenticationException extends RuntimeException {

    public InvalidAuthenticationException() {
        super("invalid email or password");
    }
}
