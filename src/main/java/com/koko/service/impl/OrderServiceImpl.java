package com.koko.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.koko.dao.OrderMapper;
import com.koko.dto.CarDto;
import com.koko.pojo.*;
import com.koko.service.*;
import com.koko.util.ObjectUtils;
import com.koko.util.ServletTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author 13629
 * @create 2021/2/27 12:26
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private StorefrontService storefrontService;

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private UserStorefrontService userStorefrontService;

    @Autowired
    private UserRoleService userRoleService;


    @Override
    public List<Order> queryByCondition(Order order) {
        return orderMapper.selectBySelective(order);
    }

    @Override
    public int add(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public int updateByCondition(Order order) {
        int i = orderMapper.updateByPrimaryKeySelective(order);
        Integer status = null;
        Car car = new Car();
        car.setNumber(order.getIdNumber());
        JSONArray jsonArray = carService.getCarByCondition(car);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        String id = jsonObject.getString("id");
        car.setId(Integer.valueOf(id));

        if (order.getStatus() == 2) {
            status = 1;
            car.setIsRenting(status);

            carService.updateCarInfoByCondition(car);
        } else if (order.getStatus() == 4) {
            status = 0;
            car.setIsRenting(status);
            carService.updateCarInfoByCondition(car);
        }

        return i;
    }

    @Override
    public int deleteByOrderId(Integer orderId) {
        return orderMapper.deleteByPrimaryKey(orderId);
    }

    @Override
    public JSONObject queryOrderDetails(Order order) {
        // 查询用户
        User user = new User();
        user.setIdNumber(order.getIdNumber());
        List<User> users = userService.getUserByCondition(user);
        log.info(users.toString());

        //查询汽车
        Car car = new Car();
        car.setNumber(order.getCarNumber());
        JSONArray cars = carService.getCarByCondition(car);
        log.info(cars.getJSONObject(0).toString());

        //查询取车门店
        Storefront pickUpStorefront = storefrontService.select(order.getPickUpStorefrontId());

        //查询还车门店
        Storefront returnStorefront = storefrontService.select(order.getReturnStorefrontId());

        //查询支付订单
        PayOrder payOrder = payOrderService.select(order.getPayOrderId());

        //组装
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userInfo", users.get(0));
        jsonObject.put("carInfo", cars.getJSONObject(0));
        jsonObject.put("pickUpStorefront", pickUpStorefront);
        jsonObject.put("returnStorefront", returnStorefront);
        jsonObject.put("payOrder", payOrder);


        return jsonObject;
    }

    @Override
    public Object queryRevenue(String year) {
        ArrayList<HashMap<String, Object>> storefrontList = new ArrayList<>();
        String username = (String) ServletTool.getRequest().getAttribute("username");
        User temp = new User();
        temp.setName(username);
        User user = userService.getUserByCondition(temp).get(0);
        UserRole userRole = userRoleService.queryByUserId(user.getId());
        if (userRole.getRoleId() == 1) {
            Storefront storefront = new Storefront();
            List<Storefront> storefronts = storefrontService.selectByCondition(storefront);
            storefronts.forEach(s -> {
                HashMap<String, Object> map = getRevenue(s, year);
                storefrontList.add(map);
            });
        } else {
            Integer storefrontId = userStorefrontService.queryStorefrontId(user.getId());
            Storefront temp_storefront = new Storefront();
            temp_storefront.setId(storefrontId);
            Storefront storefront = storefrontService.selectByCondition(temp_storefront).get(0);
            HashMap<String, Object> map = getRevenue(storefront, year);
            storefrontList.add(map);
        }

        return storefrontList;
    }

    HashMap<String, Object> getRevenue(Storefront storefront, String year) {
        //首先查出所有存在的收益和其对应的月份
        ArrayList<String> date = new ArrayList<>();
        ArrayList<Integer> money = new ArrayList<>();
        HashMap<String, Object> resultMap = new HashMap<>();
        List<Map<String, Object>> maps = orderMapper.selectRevenue(storefront.getId(), year);
        System.out.println(maps);
        maps.forEach(map -> {
            date.add((String) map.get("time"));
            money.add(Integer.valueOf(map.get("money").toString()));
        });
        //需要二次封装，因为查出来的可能是这样['2021-02','2021-04'],月份不连贯
        //前端展示的时候需要完整连贯的月份:['2021-01','2021-02','2021-03','2021-04']
        String s = date.get(date.size() - 1);
        int len = s.length();
        int month = Integer.parseInt(s.substring(len - 2, len));
        List< String> list1 = new ArrayList< String>(Collections.nCopies(month,""));
        List< Integer> list2 = new ArrayList< Integer>(Collections.nCopies(month,0));
        int i = date.size();
        int j = month;
        String temp_month = "";
        if (i != j) {
            while (i != 0 || j != 0){
                int i_value = 0;
                if (i != 0){
                    i_value = subStringDate(date.get(i - 1));
                }
                if (j < 10) {
                    temp_month = "0" + j;
                } else {
                    temp_month = "" + j;
                }
                if (j  == i_value){
                    i--;
                    list2.set(j-1, money.get(i));
                }
                list1.set(j - 1, s.substring(0, 5) + temp_month);
                j--;
            }
        }
        resultMap.put("date", list1);
        resultMap.put("money", list2);
        resultMap.put("name", storefront.getName());
        return resultMap;
    }

    public int subStringDate(String s){
        int len = s.length();
        return Integer.parseInt(s.substring(len - 2, len));
    }
}
