package com.bx.common.enums;

public enum ResultCodeEnum {
    SUCCESS("200", "成功"),

    PARAM_ERROR("400", "参数异常"),
    TOKEN_INVALID_ERROR("401", "无效的token"),
    TOKEN_CHECK_ERROR("401", "token验证失败，请重新登录"),
    PARAM_LOST_ERROR("4001", "参数缺失"),

    SYSTEM_ERROR("500", "系统异常"),
    USER_EXIST_ERROR("5001", "用户名已存在"),
    USER_REGISTER_ERROR("5002", "用户注册失败，请重试"),
    USER_NOT_LOGIN("5003", "用户未登录"),
    USER_ACCOUNT_ERROR("5004", "账号或密码错误"),
    USER_NOT_EXIST_ERROR("5005", "用户不存在"),
    PARAM_PASSWORD_ERROR("5006", "原密码输入错误"),
    ACTIVITY_SIGN_ERROR("5007", "活动已报名"),
    ;

    public String code;
    public String msg;

    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

