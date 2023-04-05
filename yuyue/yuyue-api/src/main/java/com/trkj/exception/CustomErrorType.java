package com.trkj.exception;

public enum CustomErrorType {
    USER_INPUT_ERROR(400,"参数异常"),
    SYSTEM_ERROR (500,"系统异常"),
    ACCOUNT_ERROR(403,"账户异常"),
    TOKEN_REFRESH(403,"TOKEN刷新"),
    TOKEN_ERROR(403,"TOKEN异常"),
    FILE_UPLOAD_NOFILES_ERROR(500,"没有指定文件上传"),
    FILE_UPLOAD_ERROR(500,"文件上传异常"),
    RESOURCE_NOT_FOUND_ERROR(404,"无法找到对应的资源"),
    LOG_ERROR(500,"日志异常"),
    OTHER_ERROR(999,"其他未知异常");
    CustomErrorType(int code, String typeDesc) {
        this.code = code;
        this.typeDesc = typeDesc;
    }
    //异常类型中文描述
    private String typeDesc;
    //异常code
    private int code;

    public String getTypeDesc() {
        return typeDesc;
    }

    public int getCode() {
        return code;
    }
}