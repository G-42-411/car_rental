package com.koko.controller.storefront;

import com.alibaba.fastjson.JSONArray;
import com.koko.dto.CommonResult;
import com.koko.pojo.Storefront;
import com.koko.service.StorefrontService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 13629
 * @create 2021/2/27 20:32
 */
@Slf4j
@RestController
public class StorefrontController {

    @Autowired
    StorefrontService storefrontService;

    @PostMapping("/getStorefront")
    public CommonResult getStorefront(@RequestBody Storefront storefront){
        List<Storefront> storefronts = storefrontService.selectByCondition(storefront);
        return CommonResult.ok(storefronts);
    }

    @PostMapping("/updateStorefront")
    public CommonResult updateStorefront(@RequestBody Storefront storefront){
        storefrontService.update(storefront);
        return CommonResult.ok("更新成功");
    }

    @PostMapping("/addStorefront")
    public CommonResult addStorefront(@RequestBody Storefront storefront){
        storefrontService.add(storefront);
        return CommonResult.ok("添加成功");
    }

    @GetMapping("/deleteStorefront")
    public CommonResult deleteStorefront(Integer id){
        storefrontService.delete(id);
        return CommonResult.ok("删除成功");
    }

    @GetMapping("/getStorefrontInfoList")
    public CommonResult getStorefrontInfoList(){
        JSONArray result = storefrontService.selectDistinctAddress();
        return CommonResult.ok(result);
    }

}
