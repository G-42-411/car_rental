package com.koko.dao;

import com.koko.pojo.CarImg;

import java.util.List;

public interface CarImgMapper {
    int insert(CarImg record);

    int insertSelective(CarImg record);

    int addByCarId(Integer carId, String imgUrl);

    List<String> selectByCarId(Integer carId);

    int deleteByCarId(Integer carId);
}