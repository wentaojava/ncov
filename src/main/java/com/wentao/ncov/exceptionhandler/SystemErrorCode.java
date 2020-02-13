package com.wentao.ncov.exceptionhandler;


import lombok.Getter;

/**
 * 系统错误码定义
 *
 * @time: 2018年11月08日
 * @author: wentao
 * @copyright: Wuxi Yazuo ,Ltd.copyright 2015-2025
 */

public enum SystemErrorCode {
    /**
     * 系统错误
     */
    SYSTEM_ERROR_CODE("1111", "系统错误"),


    /**
     * 参数不合法
     */
    PARAM_NOT_ALLOW("10004", "参数不合法");

    @Getter
    private String code;
    @Getter
    private String message;

    SystemErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
