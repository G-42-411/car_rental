package com.koko.service;

import com.koko.pojo.Menu;

import java.util.List;

/**
 * @author 13629
 * @create 2021/3/5 22:46
 */
public interface MenuService {

    List<Menu> queryByRoleId(Integer roleId);

    List<Menu> queryByParentId(Integer id);

    List<Menu> getMenuByCurrentUser(String username);
}
