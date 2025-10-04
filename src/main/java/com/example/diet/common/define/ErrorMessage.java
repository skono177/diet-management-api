package com.example.diet.common.define;

import java.util.Map;

import com.example.diet.common.define.ApiDefine.MealImageExt;
import com.example.diet.common.define.ApiDefine.ParamSize;
import com.example.diet.common.define.ApiDefine.RequestItem;
import com.example.diet.common.define.ApiDefine.ValidateErrRet;
import com.example.diet.common.utils.CommonUtils;

public class ErrorMessage {

    public static class CommonValidationErrMsg {

        private static final String REQUIRED_MSG = "%sは必須です。";
        private static final String TYPE_NUMBER_MSG = "%sは整数で設定してください。";
        private static final String STRING_MAX_LENGS_MSG = "%sは%d文字以下で入力してください。";
        private static final String FORMAT_MSG = "%sは%sの中から設定してください。";
        private static final String DATE_FORMAT_MSG = "%sは"
            + FormatDefine.DATE_FORMAT + "の形式で設定してください。";
        private static final String DATE_CORRELATION_MSG = "%sは%s以前の日付で設定してください。";
        private static final String NUMBER_SIZE_MSG = "%sには%d～%dの整数を設定してください。";
        private static final String UNKNOWN_ERR_MSG = "不明なエラーです。";

    }

    public static class SearchValidationErrMsg {

        public static class PageSize {

            private static final String ITEM_NAME = RequestItem.SearchCommon.PAGE_SIZE
                .getItemName();

            private static final Map<ValidateErrRet, String> messages = Map.of(
                ValidateErrRet.TYPE,
                String.format(CommonValidationErrMsg.NUMBER_SIZE_MSG,
                    ITEM_NAME,
                    ParamSize.PAGE_SIZE.getMinValue(),
                    ParamSize.PAGE_SIZE.getMaxValue()),
                ValidateErrRet.MAX_SIZE,
                String.format(CommonValidationErrMsg.NUMBER_SIZE_MSG,
                    ITEM_NAME,
                    ParamSize.PAGE_SIZE.getMinValue(),
                    ParamSize.PAGE_SIZE.getMaxValue()),
                ValidateErrRet.MIN_SIZE,
                String.format(CommonValidationErrMsg.NUMBER_SIZE_MSG,
                    ITEM_NAME,
                    ParamSize.PAGE_SIZE.getMinValue(),
                    ParamSize.PAGE_SIZE.getMaxValue()));

            public static String getMessage(ValidateErrRet err) {
                return messages.getOrDefault(err,
                    CommonValidationErrMsg.UNKNOWN_ERR_MSG);
            }
        }

        public static class PageNumber {

            private static final String ITEM_NAME = RequestItem.SearchCommon.PAGE_NUMBER
                .getItemName();

            private static final Map<ValidateErrRet, String> messages = Map.of(
                ValidateErrRet.TYPE,
                String.format(CommonValidationErrMsg.NUMBER_SIZE_MSG,
                    ITEM_NAME,
                    ParamSize.PAGE_NUMBER.getMinValue(),
                    ParamSize.PAGE_NUMBER.getMaxValue()),
                ValidateErrRet.MAX_SIZE,
                String.format(CommonValidationErrMsg.NUMBER_SIZE_MSG,
                    ITEM_NAME,
                    ParamSize.PAGE_NUMBER.getMinValue(),
                    ParamSize.PAGE_NUMBER.getMaxValue()),
                ValidateErrRet.MIN_SIZE,
                String.format(CommonValidationErrMsg.NUMBER_SIZE_MSG,
                    ITEM_NAME,
                    ParamSize.PAGE_NUMBER.getMinValue(),
                    ParamSize.PAGE_NUMBER.getMaxValue()));

            public static String getMessage(ValidateErrRet err) {
                return messages.getOrDefault(err,
                    CommonValidationErrMsg.UNKNOWN_ERR_MSG);
            }
        }
    }

    public static class MealValidationErrMsg {

        public static class MealId {

            private static final String ITEM_NAME = RequestItem.Meal.MEAL_ID
                .getItemName();

            private static final Map<ValidateErrRet, String> messages = Map.of(
                ValidateErrRet.REQUIRED,
                String.format(CommonValidationErrMsg.REQUIRED_MSG,
                    ITEM_NAME),
                ValidateErrRet.TYPE,
                String.format(CommonValidationErrMsg.TYPE_NUMBER_MSG,
                    ITEM_NAME));

            public static String getMessage(ValidateErrRet err) {
                return messages.getOrDefault(err,
                    CommonValidationErrMsg.UNKNOWN_ERR_MSG);
            }
        }

        public static class MealType {

            private static final String ITEM_NAME = RequestItem.Meal.MEAL_TYPE
                .getItemName();

            private static final Map<ValidateErrRet, String> messages = Map.of(
                ValidateErrRet.REQUIRED,
                String.format(CommonValidationErrMsg.REQUIRED_MSG,
                    ITEM_NAME),
                ValidateErrRet.TYPE,
                String.format(CommonValidationErrMsg.FORMAT_MSG, ITEM_NAME,
                    CommonUtils.connectStr(ApiDefine.JP_COMMA,
                        ApiDefine.MealType.getLabels())),
                ValidateErrRet.FORMAT,
                String.format(CommonValidationErrMsg.FORMAT_MSG, ITEM_NAME,
                    CommonUtils.connectStr(ApiDefine.JP_COMMA,
                        ApiDefine.MealType.getLabels())));

            public static String getMessage(ValidateErrRet err) {
                return messages.getOrDefault(err,
                    CommonValidationErrMsg.UNKNOWN_ERR_MSG);
            }
        }

        public static class RegisterDateFrom {

            private static final String ITEM_NAME_FROM = RequestItem.Meal.REGISTER_DATE_FROM
                .getItemName();

            private static final Map<ValidateErrRet, String> messages = Map.of(
                ValidateErrRet.FORMAT,
                String.format(CommonValidationErrMsg.DATE_FORMAT_MSG,
                    ITEM_NAME_FROM),
                ValidateErrRet.CORRELATION,
                String.format(CommonValidationErrMsg.DATE_CORRELATION_MSG,
                    ITEM_NAME_FROM,
                    RequestItem.Meal.REGISTER_DATE_TO.getItemName()));

            public static String getMessage(ValidateErrRet err) {
                return messages.getOrDefault(err,
                    CommonValidationErrMsg.UNKNOWN_ERR_MSG);
            }
        }

        public static class RegisterDateTo {

            private static final String ITEM_NAME_TO = RequestItem.Meal.REGISTER_DATE_FROM
                .getItemName();

            private static final Map<ValidateErrRet, String> messages = Map.of(
                ValidateErrRet.FORMAT,
                String.format(CommonValidationErrMsg.DATE_FORMAT_MSG,
                    ITEM_NAME_TO));

            public static String getMessage(ValidateErrRet err) {
                return messages.getOrDefault(err,
                    CommonValidationErrMsg.UNKNOWN_ERR_MSG);
            }
        }

        public static class Calorie {

            private static final String ITEM_NAME = RequestItem.Meal.CALORIE
                .getItemName();

            private static final Map<ValidateErrRet, String> messages = Map.of(
                ValidateErrRet.REQUIRED,
                String.format(CommonValidationErrMsg.REQUIRED_MSG,
                    ITEM_NAME),
                ValidateErrRet.TYPE,
                String.format(CommonValidationErrMsg.NUMBER_SIZE_MSG,
                    ITEM_NAME,
                    ParamSize.CALORIE.getMinValue(),
                    ParamSize.CALORIE.getMaxValue()),
                ValidateErrRet.MAX_SIZE,
                String.format(CommonValidationErrMsg.NUMBER_SIZE_MSG,
                    ITEM_NAME,
                    ParamSize.CALORIE.getMinValue(),
                    ParamSize.CALORIE.getMaxValue()),
                ValidateErrRet.MIN_SIZE,
                String.format(CommonValidationErrMsg.NUMBER_SIZE_MSG,
                    ITEM_NAME,
                    ParamSize.CALORIE.getMinValue(),
                    ParamSize.CALORIE.getMaxValue()));

            public static String getMessage(ValidateErrRet err) {
                return messages.getOrDefault(err,
                    CommonValidationErrMsg.UNKNOWN_ERR_MSG);
            }
        }

        public static class Comment {

            private static final String ITEM_NAME = RequestItem.Meal.COMMENT
                .getItemName();

            private static final Map<ValidateErrRet, String> messages = Map.of(
                ValidateErrRet.MAX_SIZE,
                String.format(CommonValidationErrMsg.STRING_MAX_LENGS_MSG,
                    ITEM_NAME,
                    ParamSize.CALORIE.getMinValue()));

            public static String getMessage(ValidateErrRet err) {
                return messages.getOrDefault(err,
                    CommonValidationErrMsg.UNKNOWN_ERR_MSG);
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
                return messages.getOrDefault(err,
                    CommonValidationErrMsg.UNKNOWN_ERR_MSG);
            }
        }
    }
}
