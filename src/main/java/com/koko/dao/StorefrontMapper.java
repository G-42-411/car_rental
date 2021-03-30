package com.koko.dao;

import com.koko.pojo.Storefront;

import java.util.List;

public interface StorefrontMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Storefront record);

    int insertSelective(Storefront record);

    Storefront selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Storefront record);

    int updateByPrimaryKey(Storefront record);

    List<Storefront> selectBySelective(Storefront record);

    List<Storefront> selectAll();

    List<String> selectStorefrontAddress();

    List<String> selectLikeAddress(String address);
}