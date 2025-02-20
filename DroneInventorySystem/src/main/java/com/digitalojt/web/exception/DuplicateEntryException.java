package com.digitalojt.web.exception;

import java.util.ResourceBundle;

/**
 * 登録重複例外
 * 
 * @author dotlife
 *
 */
public class DuplicateEntryException extends RuntimeException {

    // ResourceBundleは一度だけ読み込む
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("messages");

    // メッセージコードを受け取るコンストラクタ
    public DuplicateEntryException(String messageCode) {
        super(getMessage(messageCode));
    }

    // メッセージコードに基づくメッセージを取得するヘルパーメソッド
    private static String getMessage(String messageCode) {
        // メッセージが見つからなかった場合、デフォルトメッセージを返す
        return MESSAGES.containsKey(messageCode) 
                ? MESSAGES.getString(messageCode) 
                : "Unknown error: " + messageCode;
    }
}
