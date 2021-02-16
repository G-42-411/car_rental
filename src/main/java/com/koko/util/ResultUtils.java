package com.koko.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 13629
 * @create 2020/12/17 11:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResultUtils implements Serializable {
    private int code;
    private String msg;
    private Object data;

    public static ResultUtils ok(Object data) {
        return new ResultUtils(200, "success", data);
    }

    public static ResultUtils error(Integer code, String msg) {
        return new ResultUtils().setData(null).setCode(code).setMsg(msg);
    }

    public static ResultUtils error(String msg) {
        return new ResultUtils().setData(null).setCode(500).setMsg(msg);
    }
}
