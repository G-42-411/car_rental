package com.koko.dto;

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
public class CommonResult implements Serializable {
    private int code;
    private String msg;
    private Object data;

    public static CommonResult ok(Object data) {
        return new CommonResult(200, "success", data);
    }

    public static CommonResult error(Integer code, String msg) {
        return new CommonResult().setData(null).setCode(code).setMsg(msg);
    }

    public static CommonResult error(String msg) {
        return new CommonResult().setData(null).setCode(500).setMsg(msg);
    }
}
