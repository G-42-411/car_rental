package com.koko.service.impl;

import com.koko.dao.StorefrontMapper;
import com.koko.pojo.Storefront;
import com.koko.service.StorefrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 13629
 * @create 2021/2/27 20:22
 */
@Service
public class StorefrontServiceImpl implements StorefrontService {

    @Autowired
    private StorefrontMapper storefrontMapper;

    @Override
    public int add(Storefront storefront) {
        return storefrontMapper.insert(storefront);
    }

    @Override
    public int delete(Integer id) {
        return storefrontMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Storefront storefront) {
        return storefrontMapper.updateByPrimaryKeySelective(storefront);
    }

    @Override
    public List<Storefront> selectByCondition(Storefront storefront) {
        return storefrontMapper.selectBySelective(storefront);
    }

    @Override
    public Storefront select(Integer id) {
        return storefrontMapper.selectByPrimaryKey(id);
    }
}
