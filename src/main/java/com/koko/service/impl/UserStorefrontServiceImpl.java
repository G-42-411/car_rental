package com.koko.service.impl;

import com.koko.dao.UserStorefrontMapper;
import com.koko.service.UserStorefrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 13629
 * @create 2021/3/6 21:50
 */
@Service
public class UserStorefrontServiceImpl implements UserStorefrontService {

    @Autowired
    private UserStorefrontMapper userStorefrontMapper;

    @Override
    public Integer queryStorefrontId(Integer userId) {
        return userStorefrontMapper.selectStorefrontIdByUserId(userId);
    }
}
