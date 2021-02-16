package com.koko.service.impl;

import com.koko.dao.PermissionMapper;
import com.koko.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 13629
 * @create 2021/1/14 11:10
 */
@Service
public class PermissionServiceImpl {

    @Resource
    private PermissionMapper permissionMapper;

    /**
     * 获取角色权限
     * @param user
     * @return
     */
    public Set<String> getRolePermissionByUser(User user) {
        Set<String> permissions = new HashSet<>();
        permissions.addAll(permissionMapper.selectRolePermissionByUser(user));
        return permissions;
    }

}
