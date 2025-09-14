package com.example.diet.model.meal.register;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MealRegisterParamModel {

    private Integer mealType;

    private Float calorie;

    private String comment;

    private MultipartFile mealImageFile;
}
