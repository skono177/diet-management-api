package com.example.diet.service.meal.get.detail;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import com.example.diet.model.meal.get.MealGetResponse;
import com.example.diet.model.meal.get.detail.MealDetailGetRequest;
import com.example.diet.model.validation.ValidationErrResponse;
import com.example.diet.service.BaseService;

@Service
public class MealDetailGetService implements
    BaseService<MealDetailGetRequest, Pair<Integer, MealGetResponse>, List<ValidationErrResponse>> {

    @Override
    public List<ValidationErrResponse> validation(MealDetailGetRequest value) {
        List<ValidationErrResponse> errorInfos = new ArrayList<>();

        return errorInfos;
    }

    @Override
    public Pair<Integer, MealGetResponse> execute(String userId,
        MealDetailGetRequest value)
        throws Exception {
        MealGetResponse response = new MealGetResponse();
        return Pair.of(200, response);
    }

}
