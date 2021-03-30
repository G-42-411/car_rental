package com.koko.util;

import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author 13629
 * @create 2021/3/8 17:36
 */
public class OrderUtils {

    private static final String ORDER = "OD";
    private static final String CHECKLIST = "JC";

    private static String generatorNumber(){
        Date now = new Date();
        String pattern = "yyyyMMddHHmmssSSS";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String time = format.format(now);
        return DigestUtils.md5DigestAsHex(time.getBytes());
    }

    public static String orderNumber(){
        return ORDER +"-" + generatorNumber();
    }

    public static String checkListNumber(){
        return CHECKLIST +"-" + generatorNumber();
    }
}
