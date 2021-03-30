package com.koko.controller.order;

import com.alibaba.fastjson.JSONObject;
import com.koko.dto.CommonResult;
import com.koko.dto.OrderDto;
import com.koko.pojo.Order;
import com.koko.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 13629
 * @create 2021/2/27 12:30
 */
@Slf4j
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/getOrderList")
    public CommonResult getOrderList(@RequestBody OrderDto orderDto){
        List<Order> orders = orderService.queryByCondition(orderDto);
        return CommonResult.ok(orders);
    }


    @PostMapping("/getOrderDetails")
    public CommonResult getOrderDetails(@RequestBody Order order){
        JSONObject jsonObject = orderService.queryOrderDetails(order);
        return CommonResult.ok(jsonObject);
    }

    @PostMapping("/updateOrder")
    public CommonResult updateOrder(@RequestBody Order order){
        orderService.updateByCondition(order);
        return CommonResult.ok("订单确认成功");
    }
}
