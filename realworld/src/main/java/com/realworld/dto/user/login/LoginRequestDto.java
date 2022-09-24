package com.realworld.dto.user.login;

/**
 * @author Taewoo
 */


import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@JsonRootName("user")
@Data
@NoArgsConstructor
public class LoginRequestDto {
    @Email(message = "이메일 포맷이 아닙니다.")
    @NotBlank(message = "이메일은 필수 값입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 값입니다.")
    private String password;
}
