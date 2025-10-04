package com.example.diet.model.meal;

import java.time.LocalDate;
import java.util.List;

import com.example.diet.model.list.BaseListParam;

import lombok.Data;
import lombok.EqualsAndHashCode;

// @formatter:off
@Data
@EqualsAndHashCode(callSuper = true)
// @formatter:on
public class MealSearchParam extends BaseListParam {

    private Integer mealId;

    private LocalDate registerDateFrom;

    private LocalDate registerDateTo;

    private List<Integer> mealTypes;

    private String userId;
}
