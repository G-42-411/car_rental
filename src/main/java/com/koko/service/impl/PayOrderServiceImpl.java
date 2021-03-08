package com.koko.service.impl;

import com.koko.dao.PayOrderMapper;
import com.koko.pojo.PayOrder;
import com.koko.service.PayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 13629
 * @create 2021/2/27 21:02
 */
@Service
public class PayOrderServiceImpl implements PayOrderService {

    @Autowired
    PayOrderMapper payOrderMapper;

    @Override
    public int add(PayOrder payOrder) {
        return payOrderMapper.insert(payOrder);
    }

    @Override
    public PayOrder select(Integer id) {
        return payOrderMapper.selectByPrimaryKey(id);
    }
}
