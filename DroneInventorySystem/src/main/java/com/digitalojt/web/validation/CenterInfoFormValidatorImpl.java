package com.digitalojt.web.validation;

import org.springframework.util.StringUtils;

import com.digitalojt.web.consts.ErrorMessage;
import com.digitalojt.web.exception.InvalidInputException;
import com.digitalojt.web.form.CenterInfoForm;
import com.digitalojt.web.util.InputValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 在庫センター情報画面のバリデーションチェック 実装クラス
 * 
 * @author dotlife
 */
public class CenterInfoFormValidatorImpl implements ConstraintValidator<CenterInfoFormValidator, CenterInfoForm> {

    @Override
    public boolean isValid(CenterInfoForm form, ConstraintValidatorContext context) {

        // すべてのフィールドが空かをチェック
        if (isAllFieldsEmpty(form)) {
            addErrorMessage(context, ErrorMessage.ALL_FIELDS_EMPTY_ERROR_MESSAGE);
            throw new InvalidInputException(ErrorMessage.ALL_FIELDS_EMPTY_ERROR_MESSAGE);
        }

        // センター名のバリデーション
        if (!isValidCenterName(form.getCenterName(), context)) {
        	throw new InvalidInputException("invalid.input");
        }

        // 都道府県のバリデーション
        if (!isValidRegion(form.getRegion(), context)) {
        	throw new InvalidInputException("invalid.input");
        }

        return true;
    }

    /**
     * センター名のバリデーション
     * 
     * @param centerName センター名
     * @param context バリデーションコンテキスト
     * @return センター名が有効かどうか
     */
    private boolean isValidCenterName(String centerName, ConstraintValidatorContext context) {
        if (!InputValidator.isValid(centerName)) {
            addErrorMessage(context, ErrorMessage.INVALID_INPUT_ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * 都道府県のバリデーション
     * 
     * @param region 都道府県
     * @param context バリデーションコンテキスト
     * @return 都道府県が有効かどうか
     */
    private boolean isValidRegion(String region, ConstraintValidatorContext context) {
        if (!InputValidator.isValid(region)) {
            addErrorMessage(context, ErrorMessage.INVALID_INPUT_ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    
    /**
     * すべてのフィールドが空かをチェック
     * 
     * @param form フォーム
     * @return フィールドが空かどうか
     */
    private boolean isAllFieldsEmpty(CenterInfoForm form) {
        return StringUtils.isEmpty(form.getCenterName()) && StringUtils.isEmpty(form.getRegion());
    }

    /**
     * エラーメッセージをコンテキストに追加
     * 
     * @param context バリデーションコンテキスト
     * @param message エラーメッセージ
     */
    private void addErrorMessage(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
               .addConstraintViolation();
    }
}
