package com.realworld.exception;

/**
 * @author Taewoo
 */


import java.util.*;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("유저를 찾을 수 없습니다.");
    }

}
