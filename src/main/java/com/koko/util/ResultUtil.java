package com.koko.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 13629
 * @create 2020/12/17 11:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResultUtil {
    private int code;
    private String msg;
    private Object data;

    public static ResultUtil ok(Object data) {
        return new ResultUtil(200, "success", data);
    }

    public static ResultUtil error(Integer code, String msg) {
        return new ResultUtil().setData(null).setCode(code).setMsg(msg);
    }

    public static ResultUtil error(String msg) {
        return new ResultUtil().setData(null).setCode(500).setMsg(msg);
    }
}