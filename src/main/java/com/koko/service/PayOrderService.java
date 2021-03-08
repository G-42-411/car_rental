package com.koko.service;

import com.koko.pojo.PayOrder;

import java.util.List;

/**
 * @author 13629
 * @create 2021/2/27 21:00
 */
public interface PayOrderService {

    int add(PayOrder payOrder);

    PayOrder select(Integer id);
}
