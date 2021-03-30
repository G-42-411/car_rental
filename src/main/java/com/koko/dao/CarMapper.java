package com.koko.dao;

import com.koko.dto.CarDto;
import com.koko.pojo.Car;
import com.koko.pojo.Storefront;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    List<Car> selectByOptions(@Param("storefrontName") String storefrontName, @Param("brands") List<String> brands,
                              @Param("prices") Map<String, Integer> prices, @Param("seats") List<Integer> seats,
                              @Param("doors") List<Integer> doors, @Param("gears") List<Integer> gears,
                              @Param("orderType") String orderType,@Param("order") String order);
}