package com.example.diet.service.meal.register;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Triple;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.example.diet.common.define.FormatDefine;
import com.example.diet.common.define.ApiDefine.RequestItem;
import com.example.diet.common.define.ApiDefine.ValidateErrRet;
import com.example.diet.common.define.ErrorMessage.MealValidationErrMsg;
import com.example.diet.common.utils.CommonUtils;
import com.example.diet.entity.meal.MealEntity;
import com.example.diet.entity.meal.MealImageEntity;
import com.example.diet.model.meal.MealImageParam;
import com.example.diet.model.meal.MealParam;
import com.example.diet.model.meal.register.MealRegisterRequest;
import com.example.diet.model.meal.register.MealRegisterResponse;
import com.example.diet.model.validation.ValidationErrResponse;
import com.example.diet.repository.file.FileRepository;
import com.example.diet.repository.meal.MealImageRepository;
import com.example.diet.repository.meal.MealRepository;
import com.example.diet.service.BaseService;
import com.example.diet.service.meal.validation.MealValidation;

@Service
public class MealRegisterService implements
    BaseService<MealRegisterRequest, MealRegisterResponse, List<ValidationErrResponse>> {

    private final MealRepository mealRepository;

    private final MealImageRepository mealImageRepository;

    private final FileRepository fileRepository;

    private final PlatformTransactionManager tManager;

    private final Logger logger = LogManager
        .getLogger(MealRegisterService.class);

    public MealRegisterService(
        MealRepository mealRepository,
        MealImageRepository mealImageRepository,
        @Qualifier("fileRepositoryLocalImpl") FileRepository fileRepository,
        PlatformTransactionManager tManager) {
        this.mealRepository = mealRepository;
        this.mealImageRepository = mealImageRepository;
        this.fileRepository = fileRepository;
        this.tManager = tManager;
    }

    @Override
    public List<ValidationErrResponse> validation(MealRegisterRequest value) {
        List<ValidationErrResponse> errorInfos = new ArrayList<>();

        ValidateErrRet errRet = MealValidation
            .checkMealType(value.getMealType(), true);
        if (errRet != null) {
            ValidationErrResponse errorInfo = new ValidationErrResponse();
            errorInfo
                .setMessage(MealValidationErrMsg.MealType.getMessage(errRet));
            errorInfo.setSchema(RequestItem.Meal.MEAL_TYPE.getSchema());
            errorInfos.add(errorInfo);
        }

        errRet = MealValidation.checkCalorie(value.getCalorie(), true);
        if (errRet != null) {
            ValidationErrResponse errorInfo = new ValidationErrResponse();
            errorInfo
                .setMessage(MealValidationErrMsg.Calorie.getMessage(errRet));
            errorInfo.setSchema(RequestItem.Meal.CALORIE.getSchema());
            errorInfos.add(errorInfo);
        }

        errRet = MealValidation.checkComment(value.getComment());
        if (errRet != null) {
            ValidationErrResponse errorInfo = new ValidationErrResponse();
            errorInfo
                .setMessage(MealValidationErrMsg.Comment.getMessage(errRet));
            errorInfo.setSchema(RequestItem.Meal.COMMENT.getSchema());
            errorInfos.add(errorInfo);
        }

        Triple<ValidateErrRet, String, Integer> mealImageCheckRet = MealValidation
            .checkMealImage(value.getMealImageFiles());
        if (mealImageCheckRet != null) {
            errRet = mealImageCheckRet.getLeft();
            String fileName = mealImageCheckRet.getMiddle();
            Integer fileIdx = mealImageCheckRet.getRight();
            ValidationErrResponse errorInfo = new ValidationErrResponse();
            errorInfo
                .setMessage(MealValidationErrMsg.MealImage.getMessage(errRet)
                    + "\nファイル名：" + fileName);
            errorInfo.setSchema(RequestItem.Meal.MEAL_IMAGE_FILE.getSchema()
                + String.format(FormatDefine.LIST_INDEX_FORMAT, fileIdx));
            errorInfos.add(errorInfo);
        }

        return errorInfos;
    }

    @Override
    public MealRegisterResponse execute(String userId,
        MealRegisterRequest value) throws Exception {

        MealParam param = createParam(userId, value);
        LocalDateTime now = LocalDateTime.now();

        Integer mealId = createMeal(param, now);

        MealRegisterResponse response = new MealRegisterResponse();
        response.setMealId(mealId);

        return response;
    }

    private MealParam createParam(String userId, MealRegisterRequest value) {

        MealParam param = new MealParam();

        param.setMealType(Integer.parseInt(value.getMealType()));
        param.setCalorie(Integer.parseInt(value.getCalorie()));
        param.setComment(value.getComment());
        param.setUserId(userId);

        if (value.getMealImageFiles() != null) {
            List<MealImageParam> mealImageParams = new ArrayList<MealImageParam>();
            value.getMealImageFiles().forEach(mImgFile -> {
                MealImageParam mealImageParam = new MealImageParam();
                mealImageParam.setMealImageFile(mImgFile);
                mealImageParam
                    .setMealImageFileName(mImgFile.getOriginalFilename());
                mealImageParam.setUserId(userId);
                mealImageParams.add(mealImageParam);
            });
            param.setMealImageParams(mealImageParams);
        }

        return param;
    }

    private Integer createMeal(
        MealParam param,
        LocalDateTime now) throws Exception {
        // トランザクション定義
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = tManager.getTransaction(def);

        try {
            MealEntity meal = createMealEntity(param, now);
            MealEntity savedMeal = mealRepository.save(meal);

            if (param.getMealImageParams() != null) {
                createMealImage(
                    param.getMealImageParams(),
                    savedMeal.getMealId(),
                    now);
            }

            // 正常終了 → コミット
            tManager.commit(status);
            return savedMeal.getMealId();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            // エラー発生 → ロールバック
            tManager.rollback(status);
            throw ex;
        }
    }

    private MealEntity createMealEntity(
        MealParam param,
        LocalDateTime now) {
        MealEntity entity = new MealEntity(
            null,
            param.getUserId(),
            now.toLocalDate(),
            param.getMealType(),
            param.getCalorie(),
            param.getComment(),
            param.getUserId(),
            now,
            param.getUserId(),
            now,
            0);
        return entity;

    }

    private void createMealImage(
        List<MealImageParam> mealImageParams,
        Integer mealId,
        LocalDateTime now) throws Exception {

        for (MealImageParam imageParam : mealImageParams) {
            MealImageEntity mealImage = createMealImageEntity(
                imageParam, mealId, now);
            MealImageEntity savedMealImage = mealImageRepository
                .save(mealImage);

            String filePath = CommonUtils.createMealImagePath(mealId,
                savedMealImage.getMealImageId());
            fileRepository.upload(imageParam.getMealImageFile(), filePath);

            savedMealImage.setFilePath(filePath);
            savedMealImage.setFileName(imageParam.getMealImageFileName());
            mealImageRepository.save(savedMealImage);
        }

    }

    private MealImageEntity createMealImageEntity(
        MealImageParam param,
        Integer mealId,
        LocalDateTime now) {
        MealImageEntity entity = new MealImageEntity(
            null,
            mealId,
            null,
            null,
            param.getUserId(),
            now,
            param.getUserId(),
            now,
            0);
        return entity;

    }
}
