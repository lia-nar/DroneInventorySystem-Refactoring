package com.digitalojt.web.exception;

import java.io.IOException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.digitalojt.web.consts.LogMessage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * グローバル例外ハンドラー
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 入力チェック例外のハンドリング
     * @throws IOException 
     * @throws ServletException 
     */
    @ExceptionHandler(InvalidInputException.class)
    public String  handleInvalidInputException(InvalidInputException ex, RedirectAttributes redirectAttributes, HttpServletRequest request) {
    	// エラーメッセージをフラッシュスコープに保存
        redirectAttributes.addFlashAttribute(LogMessage.FLASH_ATTRIBUTE_ERROR, ex.getMessage());

        // どの画面からリクエストが来たのかを取得
        String referer = request.getHeader("Referer");
        referer = referer.replaceAll("^(https?://[^/]+)", ""); // ドメイン部分を削除
        request.setAttribute("errorMessage", ex.getMessage());
        return "redirect:" + referer;
    }
}
