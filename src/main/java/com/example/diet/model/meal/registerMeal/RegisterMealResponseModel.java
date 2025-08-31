package com.example.diet.model.meal.registerMeal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RegisterMealResponseModel {

    @JsonProperty("meal_id")
    private Integer mealId;
}
