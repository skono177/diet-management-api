package com.example.diet.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.example.diet.common.define.ApiDefine.ParamSize;
import com.example.diet.common.define.ApiDefine.RequestItem;
import com.example.diet.common.define.ApiDefine.ValidateErrRet;
import com.example.diet.common.define.ErrorMessage.SearchValidationErrMsg;
import com.example.diet.model.list.BaseListParam;
import com.example.diet.model.list.BaseListResponse;
import com.example.diet.model.validation.ValidationErrResponse;

import io.micrometer.common.util.StringUtils;

public class SearchUtils {

    public static Long convertSearchOffset(Long pageSize, Long pageNumber) {
        return pageSize * pageNumber;
    }

    public static <T> BaseListResponse<T> createBaseListResponse(
        BaseListParam param, Long count) {

        BaseListResponse<T> response = new BaseListResponse<T>();

        response.setTotalElements(count);

        Long totalPage = 0L;
        if (count != 0) {
            totalPage = count / param.getPageSize();
        }
        response.setTotalPages(totalPage);

        response.setPageSize(param.getPageSize());
        response.setPageNumber(param.getPageNumber());

        return response;

    }

    public static List<ValidationErrResponse> checkSearchParam(
        String pageSizeStr,
        String pageNumberStr) {
        List<ValidationErrResponse> errorInfos = new ArrayList<>();
        ValidationErrResponse errorInfo = null;

        ValidateErrRet errRet = checkPageSize(pageSizeStr);
        if (errRet != null) {
            errorInfo = new ValidationErrResponse();
            errorInfo
                .setMessage(
                    SearchValidationErrMsg.PageSize
                        .getMessage(errRet));
            errorInfo
                .setSchema(RequestItem.SearchCommon.PAGE_SIZE.getSchema());
            errorInfos.add(errorInfo);
        }

        errRet = checkPageNumber(pageNumberStr);
        if (errRet != null) {
            errorInfo = new ValidationErrResponse();
            errorInfo
                .setMessage(
                    SearchValidationErrMsg.PageNumber
                        .getMessage(errRet));
            errorInfo
                .setSchema(RequestItem.SearchCommon.PAGE_NUMBER.getSchema());
            errorInfos.add(errorInfo);
        }

        return errorInfos;
    }

    private static ValidateErrRet checkPageSize(String pageSizeStr) {

        if (StringUtils.isEmpty(pageSizeStr)) {
            return null;
        }

        Long pageSize = ValidationUtils.checkLong(pageSizeStr);
        if (pageSize == null) {
            return ValidateErrRet.TYPE;
        }

        if (pageSize > ParamSize.PAGE_SIZE.getMaxValue()) {
            return ValidateErrRet.MAX_SIZE;
        }

        if (pageSize < ParamSize.PAGE_SIZE.getMinValue()) {
            return ValidateErrRet.MIN_SIZE;
        }

        return null;
    }

    private static ValidateErrRet checkPageNumber(String pageNumberStr) {

        if (StringUtils.isEmpty(pageNumberStr)) {
            return null;
        }

        Long pageNumber = ValidationUtils.checkLong(pageNumberStr);
        if (pageNumber == null) {
            return ValidateErrRet.TYPE;
        }

        if (pageNumber > ParamSize.PAGE_NUMBER.getMaxValue()) {
            return ValidateErrRet.MAX_SIZE;
        }

        if (pageNumber < ParamSize.PAGE_NUMBER.getMinValue()) {
            return ValidateErrRet.MIN_SIZE;
        }

        return null;
    }
}
