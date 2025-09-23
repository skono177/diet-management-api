package com.example.diet.model.meal.register;

import java.util.List;

import com.example.diet.model.validation.ValidationErrResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

// @formatter:off
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
// @formatter:on
public class MealRegisterResponse {

    @JsonProperty("meal_id")
    private Integer mealId;

    @JsonProperty("errors")
    private List<ValidationErrResponse> errors;
}
