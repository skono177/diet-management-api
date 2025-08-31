package com.example.diet.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.diet.common.define.ApiDefine;
import com.example.diet.common.utils.UserUtils;
import com.example.diet.model.meal.registerMeal.RegisterMealParamModel;
import com.example.diet.model.meal.registerMeal.RegisterMealRequestModel;
import com.example.diet.model.meal.registerMeal.RegisterMealResponseModel;
import com.example.diet.service.BaseService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(ApiDefine.API_BASE + "meal")
public class MealController {

    private final Logger logger = LogManager.getLogger(MealController.class);

    private final BaseService<RegisterMealRequestModel, RegisterMealParamModel, RegisterMealResponseModel> registerMealService;

    public MealController(
            BaseService<RegisterMealRequestModel, RegisterMealParamModel, RegisterMealResponseModel> registerMealService) {
        this.registerMealService = registerMealService;

    }

    @PostMapping
    public RegisterMealResponseModel registerMeal(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(name = "meal_type", required = false) Integer mealType,
            @RequestParam(required = false) Float calorie,
            @RequestParam(required = false) String comment,
            @RequestParam(name = "meal_image_file", required = false) MultipartFile mealImageFile) {

        RegisterMealRequestModel requestModel = new RegisterMealRequestModel();
        if (registerMealService.validation(requestModel) == false) {

        }

        String userId = UserUtils.getUserId("");
        RegisterMealParamModel paramModel = registerMealService.createParam(requestModel);

        RegisterMealResponseModel responseModel = registerMealService.execute(userId, paramModel);

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
