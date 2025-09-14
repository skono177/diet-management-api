package com.example.diet.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.diet.common.define.ApiDefine;
import com.example.diet.common.utils.UserUtils;
import com.example.diet.model.meal.register.MealRegisterRequestModel;
import com.example.diet.model.meal.register.MealRegisterResponseModel;
import com.example.diet.service.BaseService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

    private final BaseService<MealRegisterRequestModel, MealRegisterResponseModel> registerMealService;

    public MealController(
        BaseService<MealRegisterRequestModel, MealRegisterResponseModel> registerMealService) {
        this.registerMealService = registerMealService;

    }

    @PostMapping
    public MealRegisterResponseModel registerMeal(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestParam(name = "meal_type", required = false) Integer mealType,
        @RequestParam(required = false) Float calorie,
        @RequestParam(required = false) String comment,
        @RequestParam(name = "meal_image_file", required = false) MultipartFile mealImageFile) {

        MealRegisterRequestModel requestModel = new MealRegisterRequestModel(
            mealType,
            calorie,
            comment,
            mealImageFile);
        if (registerMealService.validation(requestModel) == false) {

        }

        String userId = UserUtils.getUserId("");

        MealRegisterResponseModel responseModel = registerMealService
            .execute(userId, requestModel);

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
