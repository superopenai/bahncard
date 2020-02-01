package me.superning.luntan.domain;

import lombok.Data;

/**
 * 错误码定义
 * @author superning
 */

public enum ErrorCode {
    /** 变量定义*/
    SUCCESS(1,"成功"),
    DUPLICATE_NAME(2,"名称重复"),
    EMPTY_LOGO(3,"LOGO为空"),
    EMPTY_BUSINESS_LICENSE(4,"营业执照为空"),
    ERROR_PHONE(5,"联系电话输入错误"),
    ERROR_ADDRESS(6,"地址输入错误"),
    MERCHANTS_NOT_EXIST(7,"商户不存在");

    private Integer code;

    private String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

