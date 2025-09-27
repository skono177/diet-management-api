package com.example.diet.model.meal.get.list;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

// @formatter:off
@Data
// @formatter:on
public class MealListGetResponse {

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
}
