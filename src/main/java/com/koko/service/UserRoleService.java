package com.koko.service;

import com.koko.pojo.UserRole;

/**
 * @author 13629
 * @create 2021/2/26 14:32
 */
public interface UserRoleService {

    Integer deleteByUserId(Integer userId);

    Integer add(UserRole userRole);

    UserRole queryByUserId(Integer userId);

    Integer updateByUserId(UserRole userRole);

}
