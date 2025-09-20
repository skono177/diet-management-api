package com.example.diet.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.diet.common.define.ApiDefine;
import com.example.diet.common.utils.UserUtils;
import com.example.diet.dto.validation.ValidationErrResponse;
import com.example.diet.model.meal.register.MealRegisterRequest;
import com.example.diet.model.meal.register.MealRegisterResponse;
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

    public MealController(
        BaseService<MealRegisterRequest, MealRegisterResponse, List<ValidationErrResponse>> registerMealService) {
        this.registerMealService = registerMealService;

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

        String userId = UserUtils.getUserId("");

        try {
            responseModel = registerMealService.execute(userId, requestModel);
        } catch (Exception e) {
            response.setStatus(500);
            return null;
        }

        logger.info(calorie);
        return responseModel;
    }

    @GetMapping("list")
    public String getMealList(@RequestParam(required = false) String param) {
        logger.info("12345");
        return new String("meal_list");
    }

    @GetMapping("detail")
    public String getMealDetail(@RequestParam(required = false) String param) {
        logger.info("12345");
        return new String("meal_detail");
    }

}
