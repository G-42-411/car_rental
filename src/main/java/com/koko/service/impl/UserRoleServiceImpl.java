package com.koko.service.impl;

import com.koko.dao.UserRoleMapper;
import com.koko.pojo.UserRole;
import com.koko.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 13629
 * @create 2021/2/26 14:32
 */
@Slf4j
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public Integer deleteByUserId(Integer userId) {
        return userRoleMapper.delete(userId);
    }

    @Override
    public Integer add(UserRole userRole) {
        return userRoleMapper.insert(userRole);
    }

    @Override
    public UserRole queryByUserId(Integer userId) {
        return userRoleMapper.selectByUserId(userId);
    }

    @Override
    public Integer updateByUserId(UserRole userRole) {
        return userRoleMapper.update(userRole);
    }
}
