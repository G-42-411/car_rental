package com.koko.dao;

import com.koko.pojo.Storefront;

public interface StorefrontMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Storefront record);

    int insertSelective(Storefront record);

    Storefront selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Storefront record);

    int updateByPrimaryKey(Storefront record);
}