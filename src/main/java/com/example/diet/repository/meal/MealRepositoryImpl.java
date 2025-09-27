package com.example.diet.repository.meal;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.example.diet.common.utils.SearchUtils;
import com.example.diet.entity.meal.MealEntity;
import com.example.diet.entity.meal.QMealEntity;
import com.example.diet.model.meal.MealSearchParam;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

public class MealRepositoryImpl implements MealRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MealRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MealEntity> searchMeals(MealSearchParam searchParam) {

        Pair<QMealEntity, BooleanBuilder> query = createMealsQuery(searchParam);

        QMealEntity meal = query.getLeft();
        BooleanBuilder builder = query.getRight();

        return queryFactory
            .selectFrom(meal)
            .where(builder)
            .fetch();
    }

    @Override
    public Long countMeals(MealSearchParam searchParam) {

        Pair<QMealEntity, BooleanBuilder> query = createMealsQuery(searchParam);

        QMealEntity meal = query.getLeft();
        BooleanBuilder builder = query.getRight();

        return queryFactory
            .select(meal.count())
            .from(meal)
            .where(builder)
            .offset(SearchUtils.convertSearchOffset(
                searchParam.getPageSize(),
                searchParam.getPageNumber()))
            .limit(searchParam.getPageSize())
            .fetchOne();
    }

    private Pair<QMealEntity, BooleanBuilder> createMealsQuery(
        MealSearchParam searchParam) {

        QMealEntity meal = QMealEntity.mealEntity;
        BooleanBuilder builder = new BooleanBuilder();

        LocalDate registerDateFrom = searchParam.getRegisterDateFrom();
        if (registerDateFrom != null) {
            builder
                .and(meal.registerDate.goe(registerDateFrom));
        }

        LocalDate registerDateTo = searchParam.getRegisterDateTo();
        if (registerDateTo != null) {
            builder
                .and(meal.registerDate.loe(registerDateTo));
        }

        List<Integer> mealTipes = searchParam.getMealTypes();
        if (mealTipes != null) {
            builder.and(meal.mealType.in(mealTipes));
        }

        builder.and(meal.registerBy.eq(searchParam.getUserId()));

        return Pair.of(meal, builder);
    }

}
