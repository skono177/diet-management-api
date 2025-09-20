package com.example.diet.repository.meal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.diet.entity.meal.MealEntity;

@Repository
public interface MealRepository extends JpaRepository<MealEntity, Integer> {

    List<MealEntity> findByCommentContaining(String keyword);
}
