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
import com.koko.service.*;
import com.koko.util.ObjectUtils;
import com.koko.util.ServletTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private StorefrontService storefrontService;

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

    @Override
    public JSONObject queryBySelective(JSONObject jsonObject) {
        JSONObject resultObj = new JSONObject();
        String storefrontName = jsonObject.getString("storefrontName");
        List<String> brands =  jsonObject.getObject("brands", List.class);
        Map<String, Integer> prices = jsonObject.getObject("prices", Map.class);
        List<Integer> seats = jsonObject.getObject("seats", List.class);
        List<Integer> doors = jsonObject.getObject("doors", List.class);
        List<Integer> gears = jsonObject.getObject("gears", List.class);
        String order = jsonObject.getString("order");
        String orderType = jsonObject.getString("orderType");

        List<Car> cars = carMapper.selectByOptions(storefrontName, brands, prices, seats, doors, gears, orderType, order);
        Storefront temp_storefront = new Storefront();
        temp_storefront.setName(storefrontName);
        Storefront storefront = storefrontService.selectByCondition(temp_storefront).get(0);
        resultObj.put("cars", cars);
        resultObj.put("storefront", storefront);
        return resultObj;
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
