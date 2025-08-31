package com.example.diet.service.meal.registerMeal;

import org.springframework.stereotype.Service;

import com.example.diet.model.meal.registerMeal.RegisterMealParamModel;
import com.example.diet.model.meal.registerMeal.RegisterMealRequestModel;
import com.example.diet.model.meal.registerMeal.RegisterMealResponseModel;
import com.example.diet.service.BaseService;

@Service
public class RegisterMealService
        implements BaseService<RegisterMealRequestModel, RegisterMealParamModel, RegisterMealResponseModel> {

    @Override
    public Boolean validation(RegisterMealRequestModel value) {
        return true;
    }

    @Override
    public RegisterMealParamModel createParam(RegisterMealRequestModel value) {

        RegisterMealParamModel param = new RegisterMealParamModel();

        param.setMealType(value.getMealType());
        param.setCalorie(value.getCalorie());
        param.setComment(value.getComment());
        param.setMealImageFile(value.getMealImageFile());

        return param;
    }

    @Override
    public RegisterMealResponseModel execute(String userId, RegisterMealParamModel param) {

        RegisterMealResponseModel response = new RegisterMealResponseModel();
        response.setMealId(1);

        return response;
    }

}
