package com.example.diet.common.define;

import java.util.ArrayList;
import java.util.List;

public class ApiDefine {

    public static final String API_BASE = "/api/";
    public static final String JP_COMMA = "、";

    public static class RequestItem {

        public static enum Meal {
            MEAL_TYPE("meal_type", "食事種別"),
            CALORIE("calorie", "カロリー"),
            COMMENT("comment", "コメント"),
            MEAL_IMAGE_FILE("meal_image_file", "食事画像");

            private final String schema;
            private final String itemName;

            private Meal(String schema, String itemName) {
                this.schema = schema;
                this.itemName = itemName;
            }

            public String getSchema() {
                return schema;
            }

            public String getItemName() {
                return itemName;
            }
        }
    }

    public static enum ValidateErrRet {
        REQUIRED,
        TYPE,
        MAX_SIZE,
        MIN_SIZE,
        FORMAT,
        DUPLICATE;
    }

    public static enum ParamSize {

        CALORIE(9999, 0),
        MEAL_COMMENT(100, 0),
        MEAL_IMAGE_NUM(10, 0);

        private final Integer maxValue;
        private final Integer minValue;

        private ParamSize(Integer maxValue, Integer minValue) {
            this.maxValue = maxValue;
            this.minValue = minValue;
        }

        public Integer getMaxValue() {
            return maxValue;
        }

        public Integer getMinValue() {
            return minValue;
        }
    }

    public static enum MealType {
        BREAKFAST(1, "朝食"),
        LUNCH(2, "昼食"),
        DINNER(3, "夕食"),
        OTHER(4, "その他");

        private final Integer value;
        private final String label;

        private MealType(Integer value, String label) {
            this.value = value;
            this.label = label;
        }

        public Integer getValue() {
            return value;
        }

        public String getLabel() {
            return label;
        }

        public static MealType fromValue(Integer value) {
            for (MealType type : MealType.values()) {
                if (type.value == value) {
                    return type;
                }
            }
            return null;
        }

        public static List<String> getLabels() {
            MealType[] types = MealType.values();
            List<String> labels = new ArrayList<>();
            for (MealType type : types) {
                labels.add(type.getLabel());
            }
            return labels;
        }
    }

    public static enum MealImageExt {
        JPG(".jpg"),
        JPEG(".jpeg"),
        PNG(".png");

        private final String value;

        private MealImageExt(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static MealImageExt fromValue(String value) {
            for (MealImageExt ext : MealImageExt.values()) {
                if (value.endsWith(ext.value)) {
                    return ext;
                }
            }
            return null;
        }
    }
}
