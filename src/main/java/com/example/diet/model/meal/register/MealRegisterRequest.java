package com.example.diet.model.meal.register;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;

// @formatter:off
@Data
@AllArgsConstructor
// @formatter:on
public class MealRegisterRequest {

    private Integer mealType;

    private Float calorie;

    private String comment;

    private List<MultipartFile> mealImageFiles;
}
