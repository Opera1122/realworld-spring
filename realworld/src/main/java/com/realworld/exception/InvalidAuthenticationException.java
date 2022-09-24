package com.realworld.exception;

/**
 * @author Taewoo
 */


import java.util.*;

public class InvalidAuthenticationException extends RuntimeException {
    public InvalidAuthenticationException() {
        super("이메일 혹은 비밀번호가 다릅니다.");
    }
}
