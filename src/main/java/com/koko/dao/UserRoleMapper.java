package com.koko.dao;

import com.koko.pojo.UserRole;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);

    int delete(Integer userId);

    int update(UserRole record);

    UserRole selectByUserId(Integer userId);
}