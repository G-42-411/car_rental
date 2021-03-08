package com.koko.dao;

import com.koko.dto.CarDto;
import com.koko.pojo.Car;
import com.koko.pojo.Storefront;

import java.util.List;

public interface CarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);

    List<Car> selectAll();

    List<Car> selectByStorefront(Storefront storefront);

    List<Car> selectByCondition(CarDto car);
}