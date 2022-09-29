package com.realworld.exception.valid;

/**
 * @author Taewoo
 */


import com.realworld.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class DuplicatedEmailValidator implements ConstraintValidator<DuplicatedEmailConstraint, String> {
    private final UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return (value == null || value.isEmpty() || !userRepository.existsUserByEmail(value));
    }
}
