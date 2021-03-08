package com.koko.controller.statistics;

import com.koko.dto.CommonResult;
import com.koko.service.OrderService;
import com.koko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 13629
 * @create 2021/3/7 17:16
 */
@RestController
public class StatisticsController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/getRevenue")
    public CommonResult getRevenue(String year){
        Object resultMap = orderService.queryRevenue(year);
        return CommonResult.ok(resultMap);
    }

    @GetMapping("/getUserDistribution")
    public CommonResult getUserDistribution(){
        Object userCity = userService.getUserCity();
        return CommonResult.ok(userCity);
    }

}
