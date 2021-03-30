package com.koko.controller.user;

import com.koko.dto.CommonResult;
import com.koko.dto.UserRoleDto;
import com.koko.pojo.Role;
import com.koko.pojo.User;
import com.koko.service.PermissionService;
import com.koko.service.RoleService;
import com.koko.service.UserService;
import com.koko.service.UserStorefrontService;
import com.koko.util.ServletTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 13629
 * @create 2021/2/18 21:19
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserStorefrontService userStorefrontService;


    /**
     * 不能直接通过loginUser.getAuthorities()拿权限，否则会在返回序列化时将数组内的元素变成一个个map对象,前端在取值的时候会比较麻烦
     * 原因：权限数组List<GrantedAuthority> authorities中的值为GrantedAuthority类型
     * GrantedAuthority是一个接口，它有一个方法：String getAuthority()， 返回时会被序列化成一个属性
     *
     */
    @GetMapping("/getUserInfo")
    public CommonResult getUserInfo() {
        String username = (String)ServletTool.getRequest().getAttribute("username");
        User temp = new User();
        temp.setName(username);
        User user = userService.getUserByCondition(temp).get(0);

        Integer storefrontId = userStorefrontService.queryStorefrontId(user.getId());
//        List<String> authorities = permissionService.getRolePermissionByUser(user);
        Role role = roleService.getRole(user.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("authorities", role.getType());
        map.put("storefrontId", storefrontId);
        return CommonResult.ok(map);
    }

    @PostMapping("/register")
    public CommonResult register(String username, String password){
        return CommonResult.error("注册失败");
    }

    @PostMapping("/updateUserInfo")
    public CommonResult updateUserInfo(@RequestBody User user){
        int isUpdated = userService.updateUserInfo(user);
        if (isUpdated == 1){
            return CommonResult.ok("更新成功");
        }
        return CommonResult.error("更新失败");
    }

    @PostMapping("/getUserListByCondition")
    public CommonResult getUserListByCondition(@RequestBody User user) {
        List<User> userList = userService.getUserByCondition(user);
        ArrayList<User> users = new ArrayList<>();
        for (User temp : userList) {
            temp.setPassword("");
            users.add(temp);
        }
        return CommonResult.ok(users);
    }

    @GetMapping("/getUserList")
    public CommonResult getUserList(){
        List<User> users = userService.queryAll();
        return CommonResult.ok(users);
    }

    @PostMapping("/addUser")
    public CommonResult addUser(@RequestBody UserRoleDto userRole){
        Integer userId = userService.addUser(userRole.getUser(), userRole.getRoleId());
        return CommonResult.ok(userId);
    }

    @PostMapping("/updateUser")
    public CommonResult updateUser(){
        return CommonResult.ok(null);
    }

    @GetMapping("/delUser")
    public CommonResult delUser(Integer userId){
        userService.deleteUser(userId);
        return CommonResult.ok(null);
    }
}
