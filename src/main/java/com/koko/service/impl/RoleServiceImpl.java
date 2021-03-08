package com.koko.service.impl;

import com.koko.dao.RoleMapper;
import com.koko.pojo.Role;
import com.koko.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 13629
 * @create 2021/3/5 20:36
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public Role getRole(Integer userId){
        return roleMapper.selectByUserId(userId);
    }
}
