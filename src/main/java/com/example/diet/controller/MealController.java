package com.example.diet.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.diet.common.define.ApiDefine;
import com.example.diet.common.utils.UserUtils;
import com.example.diet.model.list.BaseListResponse;
import com.example.diet.model.meal.get.list.MealListGetRequest;
import com.example.diet.model.meal.get.list.MealListGetResponse;
import com.example.diet.model.meal.register.MealRegisterRequest;
import com.example.diet.model.meal.register.MealRegisterResponse;
import com.example.diet.model.validation.ValidationErrResponse;
import com.example.diet.service.BaseService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @formatter:off
@RestController
@RequestMapping(ApiDefine.API_BASE + "meal")
// @formatter:on
public class MealController {

    private final Logger logger = LogManager.getLogger(MealController.class);

    private final BaseService<MealRegisterRequest, MealRegisterResponse, List<ValidationErrResponse>> registerMealService;

    private final BaseService<MealListGetRequest, BaseListResponse<MealListGetResponse>, List<ValidationErrResponse>> getListMealService;

    public MealController(
        BaseService<MealRegisterRequest, MealRegisterResponse, List<ValidationErrResponse>> registerMealService,
        BaseService<MealListGetRequest, BaseListResponse<MealListGetResponse>, List<ValidationErrResponse>> getListMealService) {
        this.registerMealService = registerMealService;
        this.getListMealService = getListMealService;
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
    public BaseListResponse<MealListGetResponse> getMealList(
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

        BaseListResponse<MealListGetResponse> responseModel = new BaseListResponse<>();
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

    @GetMapping("detail")
    public String getMealDetail(@RequestParam(required = false) String param) {
        logger.info("12345");
        return new String("meal_detail");
    }

}
