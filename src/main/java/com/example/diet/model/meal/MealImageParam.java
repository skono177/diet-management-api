package com.example.diet.model.meal;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MealImageParam {

    private Integer mealImageId;

    private MultipartFile mealImageFile;

    private String mealImageFileName;

    private String userId;
}
