package com.example.diet.repository.meal;

import java.util.List;

import com.example.diet.entity.meal.MealEntity;
import com.example.diet.model.meal.MealSearchParam;

public interface MealRepositoryCustom {

    List<MealEntity> searchMeals(MealSearchParam searchParam);

    Long countMeals(MealSearchParam searchParam);
}
