package com.koko.service;

import com.koko.pojo.User;

import java.util.List;

/**
 * @author 13629
 * @create 2021/2/19 22:25
 */
public interface PermissionService {

    public List<String> getRolePermissionByUser(User user);
}
