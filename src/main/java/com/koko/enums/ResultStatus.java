package com.koko.enums;

/**
 * @author 13629
 * @create 2020/12/17 11:45
 */
public enum ResultStatus {
    SUCCESS(200, "Success"),
    REQUEST_ERROR(400, "Bad Request"),
    LOGIN_ERROR(401, "Unauthorized"),
    ERROR(500, "Server Error");


    private Integer code;
    private String msg;

    ResultStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
