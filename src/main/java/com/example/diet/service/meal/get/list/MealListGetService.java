package com.example.diet.service.meal.get.list;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.diet.common.define.ApiDefine;
import com.example.diet.common.define.ApiDefine.RequestItem;
import com.example.diet.common.define.ApiDefine.ValidateErrRet;
import com.example.diet.common.define.ErrorMessage.MealValidationErrMsg;
import com.example.diet.common.utils.CommonUtils;
import com.example.diet.common.utils.SearchUtils;
import com.example.diet.common.utils.ValidationUtils;
import com.example.diet.entity.meal.MealEntity;
import com.example.diet.model.list.BaseListResponse;
import com.example.diet.model.meal.MealSearchParam;
import com.example.diet.model.meal.get.list.MealListGetRequest;
import com.example.diet.model.meal.get.list.MealListGetResponse;
import com.example.diet.model.validation.ValidationErrResponse;
import com.example.diet.repository.meal.MealRepository;
import com.example.diet.service.BaseService;
import com.example.diet.service.meal.validation.MealValidation;

@Service
public class MealListGetService implements
    BaseService<MealListGetRequest, BaseListResponse<MealListGetResponse>, List<ValidationErrResponse>> {

    private final MealRepository mealRepository;

    private final Logger logger = LogManager
        .getLogger(MealListGetService.class);

    public MealListGetService(
        MealRepository mealRepository,
        PlatformTransactionManager tManager) {
        this.mealRepository = mealRepository;
    }

    @Override
    public List<ValidationErrResponse> validation(MealListGetRequest value) {
        List<ValidationErrResponse> errorInfos = new ArrayList<>();

        ValidateErrRet errRet = MealValidation
            .checkRegisterDate(value.getRegisterDateFrom());
        Boolean registerDateFromRet = true;
        if (errRet != null) {
            ValidationErrResponse errorInfo = new ValidationErrResponse();
            errorInfo
                .setMessage(MealValidationErrMsg.MealType.getMessage(errRet));
            errorInfo
                .setSchema(RequestItem.Meal.REGISTER_DATE_FROM.getSchema());
            errorInfos.add(errorInfo);
            registerDateFromRet = false;
        }

        errRet = MealValidation
            .checkRegisterDate(value.getRegisterDateTo());
        Boolean registerDateToRet = true;
        if (errRet != null) {
            ValidationErrResponse errorInfo = new ValidationErrResponse();
            errorInfo
                .setMessage(MealValidationErrMsg.MealType.getMessage(errRet));
            errorInfo
                .setSchema(RequestItem.Meal.REGISTER_DATE_TO.getSchema());
            errorInfos.add(errorInfo);
            registerDateToRet = false;
        }

        if (registerDateFromRet && registerDateToRet) {
            errRet = ValidationUtils
                .checkCompareFromTo(value.getRegisterDateTo(),
                    value.getRegisterDateTo());
            if (errRet != null) {
                ValidationErrResponse errorInfo = new ValidationErrResponse();
                errorInfo
                    .setMessage(
                        MealValidationErrMsg.MealType.getMessage(errRet));
                errorInfo
                    .setSchema(RequestItem.Meal.REGISTER_DATE_FROM.getSchema());
                errorInfos.add(errorInfo);
            }
        }

        errRet = MealValidation.checkMealTypeList(value.getMealType());
        if (errRet != null) {
            ValidationErrResponse errorInfo = new ValidationErrResponse();
            errorInfo
                .setMessage(MealValidationErrMsg.MealType.getMessage(errRet));
            errorInfo.setSchema(RequestItem.Meal.MEAL_TYPE.getSchema());
            errorInfos.add(errorInfo);
        }

        errorInfos.addAll(SearchUtils.checkSearchParam(
            value.getPageSize(),
            value.getPageNumber()));

        return errorInfos;
    }

    @Override
    public BaseListResponse<MealListGetResponse> execute(String userId,
        MealListGetRequest value)
        throws Exception {

        MealSearchParam param = createParam(userId, value);

        try {
            List<MealEntity> entities = mealRepository.searchMeals(param);
            Long count = mealRepository.countMeals(param);
            return createResponse(entities, count, param);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    private MealSearchParam createParam(String userId,
        MealListGetRequest value) {

        MealSearchParam param = new MealSearchParam();

        String registerDateFrom = value.getRegisterDateFrom();
        if (!StringUtils.isEmpty(registerDateFrom)) {
            param.setRegisterDateFrom(
                CommonUtils.StrToDateTime(registerDateFrom));
        }

        String registerDateTo = value.getRegisterDateTo();
        if (!StringUtils.isEmpty(registerDateTo)) {
            param.setRegisterDateTo(
                CommonUtils.StrToDateTime(registerDateTo));
        }

        String mealTypeStr = value.getMealType();
        if (!StringUtils.isEmpty(mealTypeStr)) {
            List<Integer> mealTypes = new ArrayList<>();
            String[] mealTypeArr = mealTypeStr
                .split(ApiDefine.SEARCH_PARAM_SPLIT_KEYWORD);
            for (String mealType : mealTypeArr) {
                mealTypes.add(Integer.parseInt(mealType));
            }
            param.setMealTypes(mealTypes);
        }

        String pageSizeStr = value.getPageSize();
        if (!StringUtils.isEmpty(pageSizeStr)) {
            param.setPageSize(Long.parseLong(pageSizeStr));
        } else {
            param.setPageSize(100L);
        }

        String pageNumberStr = value.getPageNumber();
        if (!StringUtils.isEmpty(pageNumberStr)) {
            param.setPageNumber(Long.parseLong(pageNumberStr));
        } else {
            param.setPageNumber(0L);
        }

        param.setUserId(userId);

        return param;
    }

    private BaseListResponse<MealListGetResponse> createResponse(
        List<MealEntity> entities,
        Long count, MealSearchParam param) {

        BaseListResponse<MealListGetResponse> response = SearchUtils
            .createBaseListResponse(param, count);

        List<MealListGetResponse> mealList = new ArrayList<>();
        entities.forEach(e -> {
            MealListGetResponse mealResponse = new MealListGetResponse();
            mealResponse.setMealId(e.getMealId());
            mealResponse.setRegisterDate(
                CommonUtils.DateTimeToStr(e.getRegisterDate()));
            mealResponse.setMealType(e.getMealType());
            mealResponse.setCalorie(e.getCalorie());
            mealResponse.setComment(e.getComment());
            mealList.add(mealResponse);
        });
        response.setItems(mealList);

        return response;
    }
}
