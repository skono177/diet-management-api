package com.example.diet.dto.meal.register;

import lombok.Data;

@Data
public class RegisterMealRequestDto {

    private Integer mealType;

    private Float calorie;

    private String comment;

    private String mealImageFileName;

    private byte[] mealImageFile;
}
