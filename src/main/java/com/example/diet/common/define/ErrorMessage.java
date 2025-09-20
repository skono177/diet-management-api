package com.example.diet.common.define;

import java.util.Map;

import com.example.diet.common.define.ApiDefine.MealImageExt;
import com.example.diet.common.define.ApiDefine.ParamSize;
import com.example.diet.common.define.ApiDefine.RequestItem;
import com.example.diet.common.define.ApiDefine.ValidateErrRet;
import com.example.diet.common.utils.CommonUtils;

public class ErrorMessage {

    public static class MealValidationErrMsg {

        private static final String REQUIRED_MSG = "%sは必須です。";
        private static final String NUMBER_SIZE_MSG = "%sには%d～%dの整数を設定してください。";
        private static final String STRING_MAX_LENGS_MSG = "%sは%d文字以下で入力してください。";
        private static final String FORMAT_MSG = "%sは%sの中から設定してください。";
        private static final String UNKNOWN_ERR_MSG = "不明なエラーです。";

        public static class MealType {

            private static final String ITEM_NAME = RequestItem.Meal.MEAL_TYPE
                .getItemName();

            private static final Map<ValidateErrRet, String> messages = Map.of(
                ValidateErrRet.REQUIRED, String.format(REQUIRED_MSG, ITEM_NAME),
                ValidateErrRet.TYPE,
                String.format(FORMAT_MSG, ITEM_NAME,
                    CommonUtils.connectStr(ApiDefine.JP_COMMA,
                        ApiDefine.MealType.getLabels())),
                ValidateErrRet.FORMAT,
                String.format(FORMAT_MSG, ITEM_NAME,
                    CommonUtils.connectStr(ApiDefine.JP_COMMA,
                        ApiDefine.MealType.getLabels())));

            public static String getMessage(ValidateErrRet err) {
                return messages.getOrDefault(err, UNKNOWN_ERR_MSG);
            }
        }

        public static class Calorie {

            private static final String ITEM_NAME = RequestItem.Meal.CALORIE
                .getItemName();

            private static final Map<ValidateErrRet, String> messages = Map.of(
                ValidateErrRet.REQUIRED, String.format(REQUIRED_MSG, ITEM_NAME),
                ValidateErrRet.TYPE,
                String.format(NUMBER_SIZE_MSG, ITEM_NAME,
                    ParamSize.CALORIE.getMinValue(),
                    ParamSize.CALORIE.getMaxValue()),
                ValidateErrRet.MAX_SIZE,
                String.format(NUMBER_SIZE_MSG, ITEM_NAME,
                    ParamSize.CALORIE.getMinValue(),
                    ParamSize.CALORIE.getMaxValue()),
                ValidateErrRet.MIN_SIZE,
                String.format(NUMBER_SIZE_MSG, ITEM_NAME,
                    ParamSize.CALORIE.getMinValue(),
                    ParamSize.CALORIE.getMaxValue()));

            public static String getMessage(ValidateErrRet err) {
                return messages.getOrDefault(err, UNKNOWN_ERR_MSG);
            }
        }

        public static class Comment {

            private static final String ITEM_NAME = RequestItem.Meal.COMMENT
                .getItemName();

            private static final Map<ValidateErrRet, String> messages = Map.of(
                ValidateErrRet.MAX_SIZE,
                String.format(STRING_MAX_LENGS_MSG, ITEM_NAME,
                    ParamSize.CALORIE.getMinValue()));

            public static String getMessage(ValidateErrRet err) {
                return messages.getOrDefault(err, UNKNOWN_ERR_MSG);
            }
        }

        public static class MealImage {

            private static final String ITEM_NAME = RequestItem.Meal.MEAL_IMAGE_FILE
                .getItemName();

            private static final Map<ValidateErrRet, String> messages = Map.of(
                ValidateErrRet.FORMAT,
                String.format(ITEM_NAME + "のファイル形式は%s、%s、%sの何れかになります。",
                    MealImageExt.JPEG.getValue(),
                    MealImageExt.JPG.getValue(),
                    MealImageExt.PNG.getValue()),
                ValidateErrRet.DUPLICATE, ITEM_NAME + "のファイル名が重複しています。");

            public static String getMessage(ValidateErrRet err) {
                return messages.getOrDefault(err, UNKNOWN_ERR_MSG);
            }
        }
    }
}
