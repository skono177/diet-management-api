package com.example.diet.common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.example.diet.common.define.ApiDefine.ValidateErrRet;

import io.micrometer.common.util.StringUtils;

public class ValidationUtils {

    public static Integer checkInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Long checkLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static LocalDate checkDate(String value) {
        try {
            return CommonUtils.StrToDateTime(value);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static ValidateErrRet checkCompareFromTo(String fromStr,
        String toStr) {
        if (StringUtils.isEmpty(fromStr) || StringUtils.isEmpty(toStr)) {
            return null;
        }

        LocalDate from = CommonUtils.StrToDateTime(fromStr);
        LocalDate to = CommonUtils.StrToDateTime(toStr);

        if (from.isAfter(to)) {
            return ValidateErrRet.CORRELATION;
        }
        return null;
    }
}
