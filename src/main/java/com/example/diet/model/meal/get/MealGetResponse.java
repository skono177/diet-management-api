package com.example.diet.model.meal.get;

import java.util.List;

import com.example.diet.model.validation.ValidationErrResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

// @formatter:off
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
// @formatter:on
public class MealGetResponse {

    @JsonProperty("meal_id")
    private Integer mealId;

    @JsonProperty("register_date")
    private String registerDate;

    @JsonProperty("meal_type")
    private Integer mealType;

    @JsonProperty("calorie")
    private Integer calorie;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("errors")
    private List<ValidationErrResponse> errors;
}
