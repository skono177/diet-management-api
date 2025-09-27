package com.example.diet.model.validation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ValidationErrResponse {

    @JsonProperty("message")
    private String message;

    @JsonProperty("schema")
    private String schema;
}
