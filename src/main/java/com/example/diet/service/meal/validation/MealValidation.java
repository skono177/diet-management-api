package com.example.diet.service.meal.validation;

import com.example.diet.common.define.ApiDefine.MealImageExt;
import com.example.diet.common.define.ApiDefine.MealType;
import com.example.diet.common.define.ApiDefine.ParamSize;
import com.example.diet.common.define.ApiDefine.ValidateErrRet;
import com.example.diet.common.utils.ValidationUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.web.multipart.MultipartFile;

public class MealValidation {

    public static ValidateErrRet checkMealType(String mealType,
        boolean required) {
        if (StringUtils.isEmpty(mealType)) {
            if (required) {
                return ValidateErrRet.REQUIRED;
            } else {
                return null;
            }
        }

        Integer mealTypeInt = ValidationUtils.checkInteger(mealType);
        if (mealTypeInt == null) {
            return ValidateErrRet.TYPE;
        }

        if (MealType.fromValue(mealTypeInt) == null) {
            return ValidateErrRet.FORMAT;
        }

        return null;
    }

    public static ValidateErrRet checkCalorie(String calorie,
        boolean required) {
        if (StringUtils.isEmpty(calorie)) {
            if (required) {
                return ValidateErrRet.REQUIRED;
            } else {
                return null;
            }
        }

        Integer calorieInt = ValidationUtils.checkInteger(calorie);
        if (calorieInt == null) {
            return ValidateErrRet.TYPE;
        }

        if (calorieInt > ParamSize.CALORIE.getMaxValue()) {
            return ValidateErrRet.MAX_SIZE;
        }

        if (calorieInt < ParamSize.CALORIE.getMinValue()) {
            return ValidateErrRet.MIN_SIZE;
        }

        return null;
    }

    public static ValidateErrRet checkComment(String comment) {
        if (StringUtils.isEmpty(comment)) {
            return null;
        }

        if (comment.length() > ParamSize.MEAL_COMMENT.getMaxValue()) {
            return ValidateErrRet.MAX_SIZE;
        }

        return null;
    }

    public static Triple<ValidateErrRet, String, Integer> checkMealImage(
        List<MultipartFile> mealImageFiles) {
        if (mealImageFiles == null || mealImageFiles.isEmpty()) {
            return null;
        }

        Set<String> fileNames = new HashSet<>();
        Integer fileIdx = 0;
        for (MultipartFile file : mealImageFiles) {
            String lowerName = file.getOriginalFilename()
                .toLowerCase(Locale.ROOT);
            if (MealImageExt.fromValue(lowerName) == null) {
                return Triple.of(ValidateErrRet.FORMAT, lowerName, fileIdx);
            }

            if (!fileNames.add(lowerName)) {
                return Triple.of(ValidateErrRet.DUPLICATE, lowerName, fileIdx);
            }
            fileIdx++;
        }
        return null;
    }
}
