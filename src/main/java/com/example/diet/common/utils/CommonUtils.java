package com.example.diet.common.utils;

import java.util.List;

import com.example.diet.common.define.FormatDefine;

public class CommonUtils {

    public static String createMealImagePath(
        Integer mealId,
        Integer mealImageId) {

        return String.format(FormatDefine.MEAL_IMAGE_PATH_FORMAT, mealId,
            mealImageId);
    }

    public static String connectStr(String connectChar,
        List<String> paramList) {
        StringBuffer ret = new StringBuffer();

        Integer paramIdx = 1;
        for (String str : paramList) {
            ret.append(str);
            if (paramList.size() > paramIdx) {
                ret.append(connectChar);
                paramIdx++;
            }
        }
        return ret.toString();
    }
}
