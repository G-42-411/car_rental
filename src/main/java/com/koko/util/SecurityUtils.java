package com.koko.util;

import com.koko.pojo.LoginUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author 13629
 * @create 2021/3/6 19:29
 */
public class SecurityUtils {

    public static String getUsername(){
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof LoginUser){
            username = ((LoginUser) principal).getUsername();
        }else{
            username = principal.toString();
        }
        return username;
    }
}
