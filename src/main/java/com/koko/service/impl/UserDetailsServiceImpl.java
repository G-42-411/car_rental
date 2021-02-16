package com.koko.service.impl;

import com.koko.dao.PermissionMapper;
import com.koko.dao.UserMapper;
import com.koko.dao.UserRoleMapper;
import com.koko.pojo.LoginUser;
import com.koko.pojo.User;
import com.koko.pojo.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户验证处理
 *
 * @author 13629
 * @create 2020/12/18 15:11
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public LoginUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByUsername(username);
        log.info(user.toString());
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<String> permissions = permissionMapper.selectRolePermissionByUser(user);
        log.info(permissions.toString());
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }
        return new LoginUser(user, authorities);
    }

}
