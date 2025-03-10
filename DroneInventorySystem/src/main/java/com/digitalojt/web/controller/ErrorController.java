package com.digitalojt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.digitalojt.web.consts.LogMessage;
import com.digitalojt.web.consts.UrlConsts;

/**
 * エラーコントローラー
 * 
 * @author dotlife
 */
@Controller
public class ErrorController extends AbstractController {

    /**
     * エラーコントローラー
     * FlashAttribute で渡されたエラーメッセージを受け取りログ出力する。
     * 
     * @param errorMsg GlobalExceptionHandler から渡されたエラーメッセージ
     * @return エラーページ（error.html）
     */
	@GetMapping(UrlConsts.ERROR)
    public String handleError(@ModelAttribute("errorMsg") String errorMsg) {  

        logException(LogMessage.HTTP_GET, errorMsg != null ? errorMsg : "不明なエラー");
		
		return UrlConsts.ERROR_VIEW;
	}
}
