package com.realworld.exception.valid;

/**
 * @author Taewoo
 */


import com.realworld.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class DuplicatedUsernameValidator implements ConstraintValidator<DuplicatedUsernameConstraint, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return (value == null || value.isEmpty() || !userRepository.existsUserByUsername(value));
    }
}
