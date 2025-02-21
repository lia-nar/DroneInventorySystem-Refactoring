package com.digitalojt.web.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.digitalojt.web.consts.LogMessage;

import jakarta.servlet.http.HttpServletRequest;

/**
 * グローバル例外ハンドラー
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 入力値不正例外のハンドリング
     */
    @ExceptionHandler(InvalidInputException.class)
    public String handleInvalidInputException(InvalidInputException ex, RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
        return handleException(ex, redirectAttributes, request);
    }

    /**
     * 重複登録例外のハンドリング
     */
    @ExceptionHandler(DuplicateEntryException.class)
    public String handleDuplicateEntryException(DuplicateEntryException ex, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        return handleException(ex, redirectAttributes, request);
    }

    /**
     * 共通のエラーハンドリング
     */
    private String handleException(Exception ex, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        String errorMessage = ex.getMessage();
        redirectAttributes.addFlashAttribute(LogMessage.FLASH_ATTRIBUTE_ERROR, errorMessage);

        // リファラーの取得
        String referer = request.getHeader("Referer");
        referer = referer != null ? referer.replaceAll("^(https?://[^/]+)", "") : "";

        return "redirect:" + referer;
    }
}
