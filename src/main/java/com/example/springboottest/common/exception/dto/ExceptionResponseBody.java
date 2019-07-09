package com.example.springboottest.common.exception.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExceptionResponseBody {

    @JsonProperty("error_code")
    private  int errorCode;

    @JsonProperty("error_message")
    private String errorMessage;

}
