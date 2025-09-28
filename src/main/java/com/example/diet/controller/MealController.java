package com.example.diet.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.diet.common.define.ApiDefine;
import com.example.diet.common.utils.UserUtils;
import com.example.diet.model.list.BaseListResponse;
import com.example.diet.model.meal.get.MealGetResponse;
import com.example.diet.model.meal.get.detail.MealDetailGetRequest;
import com.example.diet.model.meal.get.list.MealListGetRequest;
import com.example.diet.model.meal.register.MealRegisterRequest;
import com.example.diet.model.meal.register.MealRegisterResponse;
import com.example.diet.model.validation.ValidationErrResponse;
import com.example.diet.service.BaseService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

// @formatter:off
@RestController
@RequestMapping(ApiDefine.API_BASE + "meal")
// @formatter:on
public class MealController {

    private final Logger logger = LogManager.getLogger(MealController.class);

    private final BaseService<MealRegisterRequest, MealRegisterResponse, List<ValidationErrResponse>> registerMealService;

    private final BaseService<MealListGetRequest, BaseListResponse<MealGetResponse>, List<ValidationErrResponse>> getListMealService;

    private final BaseService<MealDetailGetRequest, Pair<Integer, MealGetResponse>, List<ValidationErrResponse>> getDetailMealService;

    public MealController(
        BaseService<MealRegisterRequest, MealRegisterResponse, List<ValidationErrResponse>> registerMealService,
        BaseService<MealListGetRequest, BaseListResponse<MealGetResponse>, List<ValidationErrResponse>> getListMealService,
        BaseService<MealDetailGetRequest, Pair<Integer, MealGetResponse>, List<ValidationErrResponse>> getDetailMealService) {
        this.registerMealService = registerMealService;
        this.getListMealService = getListMealService;
        this.getDetailMealService = getDetailMealService;
    }

    @PostMapping
    public MealRegisterResponse registerMeal(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestParam(name = "meal_type", required = false) String mealType,
        @RequestParam(required = false) String calorie,
        @RequestParam(required = false) String comment,
        @RequestParam(name = "meal_image_file", required = false) List<MultipartFile> mealImageFiles) {

        MealRegisterRequest requestModel = new MealRegisterRequest(
            mealType,
            calorie,
            comment,
            mealImageFiles);

        MealRegisterResponse responseModel = new MealRegisterResponse();
        List<ValidationErrResponse> validationRets = registerMealService
            .validation(requestModel);
        if (validationRets.size() > 0) {
            response.setStatus(400);
            responseModel.setErrors(validationRets);
            return responseModel;
        }

        String userId = UserUtils.getUserId("12345");

        try {
            responseModel = registerMealService.execute(userId, requestModel);
        } catch (Exception e) {
            response.setStatus(500);
            return null;
        }

        logger.info(calorie);
        return responseModel;
    }

    @GetMapping
    public BaseListResponse<MealGetResponse> getMealList(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestParam(name = "meal_type", required = false) String mealType,
        @RequestParam(name = "register_date_from", required = false) String registerDateFrom,
        @RequestParam(name = "register_date_to", required = false) String registerDateTo,
        @RequestParam(name = "page_size", required = false) String pageSize,
        @RequestParam(name = "page_number", required = false) String pageNumber) {

        MealListGetRequest requestModel = new MealListGetRequest();
        requestModel.setMealType(mealType);
        requestModel.setRegisterDateFrom(registerDateFrom);
        requestModel.setRegisterDateTo(registerDateTo);
        requestModel.setPageSize(pageSize);
        requestModel.setPageNumber(pageNumber);

        BaseListResponse<MealGetResponse> responseModel = new BaseListResponse<>();
        List<ValidationErrResponse> validationRets = getListMealService
            .validation(requestModel);
        if (validationRets.size() > 0) {
            response.setStatus(400);
            responseModel.setErrors(validationRets);
            return responseModel;
        }
        String userId = UserUtils.getUserId("12345");

        try {
            responseModel = getListMealService.execute(userId, requestModel);
            if (responseModel == null) {
                response.setStatus(500);
                return null;
            }
        } catch (Exception e) {
            response.setStatus(500);
            return null;
        }
        return responseModel;
    }

    @GetMapping("/{meal_id}")
    public MealGetResponse getMealDetail(
        HttpServletRequest request,
        HttpServletResponse response,
        @PathVariable("meal_id") String mealId) {

        MealDetailGetRequest requestModel = new MealDetailGetRequest();
        requestModel.setMealId(mealId);

        MealGetResponse responseModel = new MealGetResponse();
        List<ValidationErrResponse> validationRets = getDetailMealService
            .validation(requestModel);
        if (validationRets.size() > 0) {
            response.setStatus(400);
            responseModel.setErrors(validationRets);
            return responseModel;
        }
        String userId = UserUtils.getUserId("12345");

        try {
            Pair<Integer, MealGetResponse> ret = getDetailMealService
                .execute(userId, requestModel);
            Integer statusCode = ret.getLeft();
            responseModel = ret.getRight();
            if (!statusCode.equals(200)) {
                response.setStatus(statusCode);
                return null;
            }
            return responseModel;
        } catch (Exception e) {
            response.setStatus(500);
            return null;
        }
    }

}
