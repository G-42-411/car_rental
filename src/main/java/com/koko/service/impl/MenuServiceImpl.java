package com.koko.service.impl;

import com.koko.dao.MenuMapper;
import com.koko.pojo.Menu;
import com.koko.pojo.Role;
import com.koko.pojo.User;
import com.koko.service.MenuService;
import com.koko.service.RoleService;
import com.koko.service.UserService;
import com.koko.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 13629
 * @create 2021/3/5 22:46
 */

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Override
    public List<Menu> queryByRoleId(Integer roleId) {
        return menuMapper.selectByRoleId(roleId);
    }

    @Override
    public List<Menu> queryByParentId(Integer id) {
        return menuMapper.selectByParentId(id);
    }

    @Override
    public List<Menu> getMenuByCurrentUser(String username) {
        System.out.println("菜单查询");
        User user = new User();
        user.setName(username);
        System.out.println(user);
        User user1 = userService.getUserByCondition(user).get(0);
        Role role = roleService.getRole(user1.getId());
        System.out.println(role.getId());

        List<Menu> menus = menuMapper.selectByRoleId(role.getId());
        System.out.println(menus);

        handleMenus(menus);
        return menus;
    }

    private void handleMenus(List<Menu> menus) {
        menus.forEach(menu -> {
            List<Menu> children = queryByParentId(menus, menu.getId());
            menu.setChildren(children);
        });
        menus.removeIf(menu -> menu.getParentId() != 0);
    }

    private List<Menu> queryByParentId(List<Menu> menus, Integer id){
        ArrayList<Menu> menuList = new ArrayList<>();
        for (Menu menu : menus) {
            if (menu.getParentId().equals(id)){
                menuList.add(menu);
            }
        }
        return menuList;
    }
}
