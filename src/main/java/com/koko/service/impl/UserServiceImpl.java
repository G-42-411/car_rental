package com.koko.service.impl;

import com.koko.dao.CommonMapper;
import com.koko.dao.UserMapper;
import com.koko.dao.UserRoleMapper;
import com.koko.enums.RoleID;
import com.koko.pojo.Car;
import com.koko.pojo.User;
import com.koko.pojo.UserRole;
import com.koko.service.UserRoleService;
import com.koko.service.UserService;
import com.koko.util.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 13629
 * @create 2021/2/20 14:15
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public Integer addUser(User user, Integer roleId) {
        String password = user.getPassword();
        user.setPassword(EncryptUtils.bCryptEncrypt(password));
        userMapper.insertSelective(user);
        Integer userId = commonMapper.getLastId();
        Integer add = userRoleService.add(new UserRole(userId, roleId));
        return userId;
    }

    @Override
    public List<User> getUserByCondition(User user) {
        return userMapper.selectByCondition(user);
    }

    @Override
    public int updateUserInfo(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteUser(Integer userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public List<User> queryAll() {
        List<User> users = userMapper.selectAll();
        ArrayList<User> arrayList = new ArrayList<>();
        for (User user : users) {
            user.setPassword("");
            arrayList.add(user);
        }
        return arrayList;
    }

    public Object getUserCity(){
        List<User> users = userMapper.selectAll();
        HashMap<String, HashMap<String, Object>> container = new HashMap<>();
        users.forEach(user -> {
            HashMap<String, Object> map_city = new HashMap<>();
            String city = splitCity(user.getAddress());
            if(!container.containsKey(city)){
                map_city.put("name", city);
                map_city.put("value", 1);
                container.put(city, map_city);

            }else{
                HashMap<String, Object> map = container.get(city);
                Integer value = (Integer)map.get("value");
                map.put("value", value + 1);
            }
        });
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        container.forEach((key, value) ->{
            list.add(value);
        });
        return list;
    }

    public String splitCity(String s){
        int i = s.indexOf("省") + 1;
        return s.substring(0,i);
    }


}
