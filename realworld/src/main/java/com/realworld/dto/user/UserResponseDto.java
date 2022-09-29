package com.realworld.dto.user;

/**
 * @author Taewoo
 */


import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@JsonRootName("user")
public class UserResponseDto {

    private String email;
    private String token;
    private String username;
    private String bio;
    private String image;

}
