package com.koko.controller.car;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.koko.dto.CarDto;
import com.koko.dto.CommonResult;
import com.koko.dto.PageResult;
import com.koko.pojo.Car;
import com.koko.pojo.Storefront;
import com.koko.service.CarImgService;
import com.koko.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 13629
 * @create 2021/2/20 15:45
 */
@Slf4j
@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarImgService carImgService;

    @GetMapping("/getCarList")
    public CommonResult getCarList() {
        Car car = new Car();
        JSONArray jsonArray = carService.getCarByCondition(car);
        return CommonResult.ok(jsonArray);
    }

    @PostMapping("/getCarListByCondition")
    public CommonResult getCarListByCondition(@RequestBody Car car) {
        JSONArray carList = carService.getCarByCondition(car);
        return CommonResult.ok(carList);
    }

    @GetMapping("/getStorefrontCar")
    public CommonResult getStorefrontCar(Storefront storefront) {
        List<Car> carList = carService.getCarByStorefront(storefront);
        return CommonResult.ok(carList);
    }

    @PostMapping("/updateCar")
    public CommonResult updateCar(@RequestBody JSONObject jsonObject) {
        String filePathList = jsonObject.getString("filePathList");
        String[] imgList = StringUtils.tokenizeToStringArray(filePathList, "[,]");
        jsonObject.remove("filePathList");
        Car car = jsonObject.toJavaObject(Car.class);
        carService.updateCarInfoByCondition(car);
        for (String path : imgList) {
            carImgService.addCarImg(car.getId(), path);
        }
        return CommonResult.ok("更新信息成功");
    }

    @PostMapping("/addCar")
    public CommonResult addCar(@RequestBody JSONObject jsonObject) {
        String filePathList = jsonObject.getString("filePathList");
        String[] imgList = StringUtils.tokenizeToStringArray(filePathList, "[,]");
        jsonObject.remove("filePathList");
        Car car = jsonObject.toJavaObject(Car.class);
        int carId = carService.addCar(car);
        for (String path : imgList) {
            carImgService.addCarImg(carId, path);
        }
        return CommonResult.ok("新增信息成功");
    }

    @GetMapping("/deleteCar")
    public CommonResult deleteCar(Integer carId){
        carService.deleteByCarId(carId);
        return CommonResult.ok("删除成功");
    }

    @PostMapping("/queryBySelective")
    public CommonResult queryBySelective(@RequestBody JSONObject jsonObject){
        JSONObject retultObj = carService.queryBySelective(jsonObject);
        return CommonResult.ok(retultObj);
    }

}
