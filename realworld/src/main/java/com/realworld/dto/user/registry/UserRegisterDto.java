package com.realworld.dto.user.registry;

/**
 * @author Taewoo
 */


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.realworld.exception.valid.DuplicatedEmailConstraint;
import com.realworld.exception.valid.DuplicatedUsernameConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName("user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterDto {

    @Email(message = "이메일 포맷이 아닙니다.")
    @NotBlank(message = "이메일은 필수 값입니다.")
    @DuplicatedEmailConstraint
    private String email;

    @NotBlank(message = "비밀번호는 필수 값입니다.")
    private String password;

    @DuplicatedUsernameConstraint
    @NotBlank(message = "이름은 필수 값입니다.")
    private String username;
}
