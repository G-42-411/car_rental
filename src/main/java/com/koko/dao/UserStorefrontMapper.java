package com.koko.dao;

import com.koko.pojo.UserStorefront;

public interface UserStorefrontMapper {
    int insert(UserStorefront record);

    int insertSelective(UserStorefront record);

    Integer selectStorefrontIdByUserId(Integer userId);
}