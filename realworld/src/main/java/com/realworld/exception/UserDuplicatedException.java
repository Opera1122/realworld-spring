package com.realworld.exception;

/**
 * @author Taewoo
 */


import java.util.*;

public class UserDuplicatedException extends RuntimeException {

    public UserDuplicatedException() {
        super("이메일이나 이름이 중복되었습니다.");
    }
}
