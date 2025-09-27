package com.example.diet.common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public static String DateTimeToStr(LocalDate date) {
        return date
            .format(DateTimeFormatter.ofPattern(FormatDefine.DATE_FORMAT));
    }

    public static LocalDate StrToDateTime(String dateTime) {
        return LocalDate.parse(dateTime,
            DateTimeFormatter.ofPattern(FormatDefine.DATE_FORMAT));
    }
}
