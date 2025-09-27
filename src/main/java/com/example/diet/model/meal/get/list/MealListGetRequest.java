package com.example.diet.model.meal.get.list;

import lombok.Data;

@Data
public class MealListGetRequest {

    private String registerDateFrom;

    private String registerDateTo;

    private String mealType;

    private String pageSize;

    private String pageNumber;
}
