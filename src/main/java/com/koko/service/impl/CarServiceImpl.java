package com.koko.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.koko.dao.CarMapper;
import com.koko.dao.CarStorefrontMapper;
import com.koko.dao.CommonMapper;
import com.koko.dao.UserStorefrontMapper;
import com.koko.dto.CarDto;
import com.koko.pojo.Car;
import com.koko.pojo.Storefront;
import com.koko.pojo.User;
import com.koko.service.CarImgService;
import com.koko.service.CarService;
import com.koko.service.UserService;
import com.koko.service.UserStorefrontService;
import com.koko.util.ObjectUtils;
import com.koko.util.ServletTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 13629
 * @create 2021/2/20 15:46
 */
@Slf4j
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private CarImgService carImgService;

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CarStorefrontMapper carStorefrontMapper;

    @Autowired
    private UserStorefrontService userStorefrontService;

    @Override
    public List<Car> getCarByStorefront(Storefront storefront) {
        return carMapper.selectByStorefront(storefront);
    }

    @Override
    public JSONArray getCarByCondition(Car car) {
        CarDto carDto = new CarDto();
        String username = (String)ServletTool.getRequest().getAttribute("username");
        Integer storefrontId = getStorefrontId(username);
        ObjectUtils.cloneBean(carDto, car);
        carDto.setStorefrontId(storefrontId);
        List<Car> cars = carMapper.selectByCondition(carDto);
        JSONArray resultJSON = new JSONArray();
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(cars));
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String carId = jsonObject.getString("id");
            List<String> imgList = carImgService.queryBycarId(Integer.valueOf(carId));
            jsonObject.put("imgList", imgList);
            resultJSON.add(jsonObject);
        }
        return resultJSON;
    }

    @Override
    public int addCar(Car car) {
        carMapper.insert(car);
        return commonMapper.getLastId();
    }

    @Override
    public int updateCarInfoByCondition(Car car) {
        carMapper.updateByPrimaryKeySelective(car);
        return carImgService.delectByCarId(car.getId());
    }

    @Override
    public int deleteByCarId(Integer id) {
        carMapper.deleteByPrimaryKey(id);
        carImgService.delectByCarId(id);
        return 0;
    }

    public Car parseString(String carInfo){
        Car car = new Car();
        JSONObject jsonObject = JSONObject.parseObject(carInfo);
        car.setId(jsonObject.containsKey("id") ? Integer.valueOf(jsonObject.getString("id")) : null);
        car.setBrand(jsonObject.getString("brand"));
        car.setColour(jsonObject.getString("colour"));
        car.setDeposit(Integer.valueOf(jsonObject.getString("deposit")));
        car.setDescription(jsonObject.getString("description"));
        car.setIsRenting(Integer.valueOf(jsonObject.getString("isRenting")));
        car.setName(jsonObject.getString("name"));
        car.setNumber(jsonObject.getString("number"));
        car.setPrice(Integer.valueOf(jsonObject.getString("price")));
        car.setRentPrice(Integer.valueOf(jsonObject.getString("rentPrice")));
        car.setType(jsonObject.getString("type"));
        return car;
    }

    public Integer getStorefrontId(String username){
        User temp = new User();
        temp.setName(username);
        User user = userService.getUserByCondition(temp).get(0);
        return userStorefrontService.queryStorefrontId(user.getId());
    }
}
