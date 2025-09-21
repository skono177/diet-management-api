package com.example.diet.repository.meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.diet.entity.meal.MealImageEntity;

@Repository
public interface MealImageRepository
    extends JpaRepository<MealImageEntity, Integer> {
}