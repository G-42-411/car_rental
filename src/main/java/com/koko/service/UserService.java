package com.koko.service;

import com.koko.pojo.User;

import java.util.List;

/**
 * @author 13629
 * @create 2021/2/20 14:15
 */
public interface UserService {
    /**
     * 新增用户
     */
    Integer addUser(User user, Integer roleId);


    /**
     *查询所有用户
     */
    List<User> queryAll();

    /**
     *通过指定条件获取用户
     */
    List<User> getUserByCondition(User user);

    /**
     * 更新用户信息
     */
    int updateUserInfo(User user);

    int deleteUser(Integer userId);

    public Object getUserCity();


}
