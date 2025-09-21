package com.example.diet.common.utils;

public class ValidationUtils {

    public static Integer checkInteger(String value) {

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
