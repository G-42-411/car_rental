package com.koko.service;

import com.alibaba.fastjson.JSONObject;
import com.koko.dto.OrderDto;
import com.koko.pojo.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 13629
 * @create 2021/2/27 12:25
 */
public interface OrderService {

    List<Order> queryByCondition(OrderDto orderDto);

    int add(Order order);

    int updateByCondition(Order order);

    int deleteByOrderId(Integer orderId);

    JSONObject queryOrderDetails(Order order);

    Object queryRevenue(String year);

}
