package com.digitalojt.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.digitalojt.web.consts.UrlConsts;

/**
 * エラーコントローラー
 * 
 * @author dotlife
 */
@Controller
public class ErrorController extends AbstractErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    /**
     * エラーコントローラー
     * FlashAttribute で渡されたエラーメッセージを受け取りログ出力する。
     * 
     * @param errorMsg GlobalExceptionHandler から渡されたエラーメッセージ
     * @return エラーページ（error.html）
     */
	@GetMapping(UrlConsts.ERROR)
    public String handleError(@ModelAttribute("errorMsg") String errorMsg) {  

		logger.error("エラー内容: {}", errorMsg);
		
		return UrlConsts.ERROR_VIEW;
	}
}
