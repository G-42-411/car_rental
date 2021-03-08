package com.koko.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 13629
 * @create 2021/3/5 10:12
 */
@Slf4j
public class ObjectUtils {

    public static void cloneBean(Object tagert, Object source){
        Class<?> aClass = tagert.getClass();
        Class<?> bClass = source.getClass();
        Field[] aFields = aClass.getDeclaredFields();
        Field[] bFields = bClass.getDeclaredFields();
        Field[] fields = aFields.length < bFields.length ? aFields : bFields;
        Class<?> type = null;
        for (Field field : fields) {
            log.info(field.getName());
            try {
                type = field.getType();

                Method methodB = bClass.getMethod("get" + StringUtils.capitalize(field.getName()));
                Method methodA = aClass.getMethod("set" + StringUtils.capitalize(field.getName()), type);
                Object o = new Object();
                Object objB = methodB.invoke(source);
                methodA.invoke(tagert, objB);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }
}
