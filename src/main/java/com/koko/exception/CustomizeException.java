package com.koko.exception;

import com.koko.enums.ResultStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 13629
 * @create 2021/2/19 16:53
 */

public class CustomizeException extends RuntimeException {
    private Integer code;
    private String message;

    public CustomizeException(ResultStatus resultStatus) {
        this.code = resultStatus.getCode();
        this.message = resultStatus.getMsg();
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
