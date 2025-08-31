package com.example.diet.model.meal.registerMeal;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RegisterMealRequestModel {

    private Integer mealType;

    private Float calorie;

    private String comment;

    private MultipartFile mealImageFile;
}
