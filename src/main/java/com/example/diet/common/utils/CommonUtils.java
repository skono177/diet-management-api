package com.example.diet.common.utils;

import com.example.diet.common.define.CommonDefine;

public class CommonUtils {

    public static String createMealImagePath(
        Integer mealId,
        Integer mealImageId) {

        return String.format(CommonDefine.MEAL_IMAGE_PATH_FORMAT, mealId,
            mealImageId);
    }
}
