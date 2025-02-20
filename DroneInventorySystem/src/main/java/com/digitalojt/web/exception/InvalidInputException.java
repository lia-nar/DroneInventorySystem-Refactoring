package com.digitalojt.web.exception;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 入力値不正例外
 * 
 * @author dotlife
 *
 */
public class InvalidInputException extends RuntimeException {

    private static final ResourceBundle messages = ResourceBundle.getBundle("messages");

    public InvalidInputException(String messageKey) {
        super(getMessage(messageKey));
    }

    // メッセージを取得するメソッドを追加
    private static String getMessage(String messageKey) {
        try {
            return messages.getString(messageKey);
        } catch (MissingResourceException e) {
            // メッセージが存在しない場合はデフォルトメッセージを返す
            return "エラーが発生しました。";
        }
    }
}
