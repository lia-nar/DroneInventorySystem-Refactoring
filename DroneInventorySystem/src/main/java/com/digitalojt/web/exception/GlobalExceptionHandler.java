package com.digitalojt.web.exception;

import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.digitalojt.web.consts.LogMessage;
import com.digitalojt.web.consts.UrlConsts;


/**
 * グローバル例外ハンドラー
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

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
    
    /**
     * DBエラーのハンドリング
     * @return error.html
     */
    @ExceptionHandler(DataAccessException.class)
    public String handleDatabaseException(DataAccessException ex, Model model) {
        logger.error("DBエラーが発生しました: {}", ex.getMessage(), ex);
        return UrlConsts.ERROR_VIEW;
    }
    
    /**
     * SQLエラーのハンドリング
     */
    @ExceptionHandler(SQLException.class)
    public String handleSQLException(SQLException ex, Model model) {
        logger.error("SQLエラーが発生しました: {}", ex.getMessage(), ex);
        return UrlConsts.ERROR_VIEW;
    }
    
    /**
     * 予期せぬシステムエラーのハンドリング
     */
    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, Model model) {
        logger.error("予期せぬエラーが発生しました: {}", ex.getMessage(), ex);
        return UrlConsts.ERROR_VIEW;
    }

}
