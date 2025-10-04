package com.example.diet.service.meal.get.detail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.diet.common.define.ApiDefine.RequestItem;
import com.example.diet.common.define.ApiDefine.ValidateErrRet;
import com.example.diet.common.define.ErrorMessage.MealValidationErrMsg;
import com.example.diet.common.utils.CommonUtils;
import com.example.diet.entity.meal.MealEntity;
import com.example.diet.model.meal.MealSearchParam;
import com.example.diet.model.meal.get.MealGetResponse;
import com.example.diet.model.meal.get.detail.MealDetailGetRequest;
import com.example.diet.model.validation.ValidationErrResponse;
import com.example.diet.repository.meal.MealRepository;
import com.example.diet.service.BaseService;
import com.example.diet.service.meal.validation.MealValidation;

@Service
public class MealDetailGetService implements
    BaseService<MealDetailGetRequest, Pair<Integer, MealGetResponse>, List<ValidationErrResponse>> {

    private final Logger logger = LogManager
        .getLogger(MealDetailGetService.class);

    private final MealRepository mealRepository;

    public MealDetailGetService(
        MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public List<ValidationErrResponse> validation(MealDetailGetRequest value) {
        List<ValidationErrResponse> errorInfos = new ArrayList<>();

        ValidateErrRet errRet = MealValidation
            .checkMealId(value.getMealId(), true);
        if (errRet != null) {
            ValidationErrResponse errorInfo = new ValidationErrResponse();
            errorInfo
                .setMessage(
                    MealValidationErrMsg.MealId.getMessage(errRet));
            errorInfo
                .setSchema(RequestItem.Meal.MEAL_ID.getSchema());
            errorInfos.add(errorInfo);
        }

        return errorInfos;
    }

    @Override
    public Pair<Integer, MealGetResponse> execute(String userId,
        MealDetailGetRequest value)
        throws Exception {

        MealSearchParam param = createParam(userId, value);

        try {
            Optional<MealEntity> entityOpt = mealRepository
                .findById(param.getMealId());
            if (!entityOpt.isPresent()) {
                return Pair.of(404, null);
            }

            MealEntity entity = entityOpt.get();
            if (!entity.getRegisterBy().equals(param.getUserId())) {
                return Pair.of(403, null);
            }

            return Pair.of(200, createResponse(entity));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Pair.of(500, null);
        }
    }

    private MealSearchParam createParam(String userId,
        MealDetailGetRequest value) {

        MealSearchParam param = new MealSearchParam();

        param.setMealId(Integer.parseInt(value.getMealId()));
        param.setUserId(userId);

        return param;
    }

    private MealGetResponse createResponse(MealEntity entity) {

        MealGetResponse response = new MealGetResponse();

        response.setMealId(entity.getMealId());
        response.setRegisterDate(
            CommonUtils.DateTimeToStr(entity.getRegisterDate()));
        response.setMealType(entity.getMealType());
        response.setCalorie(entity.getCalorie());
        response.setComment(entity.getComment());

        return response;
    }
}
