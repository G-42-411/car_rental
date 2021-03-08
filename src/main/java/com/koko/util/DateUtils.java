package com.koko.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 13629
 * @create 2021/3/1 16:57
 */
public class DateUtils {

    public static Date transferFormat(Date beforDate){
        SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat s2 = new SimpleDateFormat("yyyy-MM-dd");
        Date lastDate = new Date();
        try {
            lastDate = s2.parse(s1.format(beforDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lastDate;
    }
}
