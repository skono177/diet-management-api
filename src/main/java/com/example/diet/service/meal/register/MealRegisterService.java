package com.example.diet.service.meal.register;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.example.diet.entity.MealEntity;
import com.example.diet.model.meal.register.MealRegisterParamModel;
import com.example.diet.model.meal.register.MealRegisterRequestModel;
import com.example.diet.model.meal.register.MealRegisterResponseModel;
import com.example.diet.repository.MealRepository;
import com.example.diet.service.BaseService;

@Service
public class MealRegisterService implements
    BaseService<MealRegisterRequestModel, MealRegisterResponseModel> {

    private final MealRepository mealRepository;
    private final PlatformTransactionManager tManager;

    public MealRegisterService(MealRepository mealRepository,
        PlatformTransactionManager tManager) {
        this.mealRepository = mealRepository;
        this.tManager = tManager;
    }

    @Override
    public Boolean validation(MealRegisterRequestModel value) {
        return true;
    }

    @Override
    public MealRegisterResponseModel execute(String userId,
        MealRegisterRequestModel value) {

        MealRegisterParamModel param = createParam(value);
        LocalDateTime now = LocalDateTime.now();

        Integer mealId = createMeal(param, now);

        MealRegisterResponseModel response = new MealRegisterResponseModel();
        response.setMealId(mealId);

        return response;
    }

    private MealRegisterParamModel createParam(MealRegisterRequestModel value) {

        MealRegisterParamModel param = new MealRegisterParamModel();

        param.setMealType(value.getMealType());
        param.setCalorie(value.getCalorie());
        param.setComment(value.getComment());
        param.setMealImageFile(value.getMealImageFile());

        return param;
    }

    private Integer createMeal(
        MealRegisterParamModel param,
        LocalDateTime now) {
        // トランザクション定義
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = tManager.getTransaction(def);

        try {
            MealEntity meal = createMealEntity(param, now);
            MealEntity saved = mealRepository.save(meal);
            // 正常終了 → コミット
            tManager.commit(status);
            return saved.getMealId();
        } catch (Exception ex) {
            // エラー発生 → ロールバック
            tManager.rollback(status);
            throw ex;
        }
    }

    private MealEntity createMealEntity(
        MealRegisterParamModel param,
        LocalDateTime now) {
        MealEntity entity = new MealEntity(
            null,
            "null",
            now.toLocalDate(),
            param.getMealType(),
            param.getCalorie(),
            param.getComment(),
            "null",
            now,
            "null",
            now,
            0);
        return entity;

    }
}
