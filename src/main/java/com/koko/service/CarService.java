package com.koko.service;

import com.alibaba.fastjson.JSONArray;
import com.koko.dto.CarDto;
import com.koko.pojo.Car;
import com.koko.pojo.Storefront;

import java.util.List;

/**
 * @author 13629
 * @create 2021/2/20 15:45
 */
public interface CarService {

    /**
     * 获取门店中的车辆
     */
    List<Car> getCarByStorefront(Storefront storefront);

    /**
     * 获取符合条件的车辆
     * @param car
     */
    JSONArray getCarByCondition(Car car);

    /**
     * 新增车辆
     */
    int addCar(Car car);


    /**
     *更新车辆指定信息
     */
    int updateCarInfoByCondition(Car car);

    int deleteByCarId(Integer id);

}
