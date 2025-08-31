package com.example.diet.dto.meal.register;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RegisterMealResponseDto {

    @JsonProperty("meal_id")
    private Integer mealId;

}
