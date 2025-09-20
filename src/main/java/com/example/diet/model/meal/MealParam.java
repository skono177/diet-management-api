package com.example.diet.model.meal;

import java.util.List;

import lombok.Data;

@Data
public class MealParam {

    private Integer mealId;

    private Integer mealType;

    private Integer calorie;

    private String comment;

    private List<MealImageParam> mealImageParams;

    private String userId;
}
