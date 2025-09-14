package com.example.diet.model.meal.register;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MealRegisterResponseModel {

    @JsonProperty("meal_id")
    private Integer mealId;
}
