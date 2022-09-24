package com.realworld.dto.user.login;

/**
 * @author Taewoo
 */


import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@JsonRootName("user")
@AllArgsConstructor
@Builder
public class LoginResponseDto {

    private String email;
    private String token;
    private String username;
    private String bio;
    private String image;

}
