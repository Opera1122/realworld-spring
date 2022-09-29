package com.realworld.exception;

/**
 * @author Taewoo
 */


import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonRootName("response")
public class ErrorResponse {

    private String objectName;
    private String field;
    private String code;
    private String message;

}
