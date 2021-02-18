package com.koko.dao;

import com.koko.pojo.Permission;
import com.koko.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    /**
     * 查询用户所属角色的权限
     * @param user
     * @return
     */
    Set<String> selectRolePermissionByUser(User user);
}