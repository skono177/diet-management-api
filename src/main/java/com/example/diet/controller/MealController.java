package com.example.diet.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.diet.common.define.ApiDefine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(ApiDefine.API_BASE + "meal")
public class MealController {

    private final Logger logger = LogManager.getLogger(MealController.class);

    @PostMapping
    public String registerMeal(
            @RequestParam(name = "meal_type", required = false) Integer mealType,
            @RequestParam(required = false) Float calorie,
            @RequestParam(required = false) String comment,
            @RequestParam(name = "meal_image_file", required = false) MultipartFile mealImageFile) {

        logger.info(calorie);
        return "entity";
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
