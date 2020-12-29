package com.koko.dao;

import com.koko.pojo.CarStorefront;

public interface CarStorefrontMapper {
    int insert(CarStorefront record);

    int insertSelective(CarStorefront record);
}