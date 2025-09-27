package com.example.diet.model.meal.get.list;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MealListGetRequest {

    @JsonProperty("register_date_from")
    private String registerDateFrom;

    @JsonProperty("register_date_to")
    private String registerDateTo;

    @JsonProperty("meal_type")
    private String mealType;

    @JsonProperty("page_size")
    private String pageSize;

    @JsonProperty("page_number")
    private String pageNumber;
}
