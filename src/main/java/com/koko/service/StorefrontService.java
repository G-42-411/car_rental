package com.koko.service;

import com.koko.pojo.Storefront;

import java.util.List;

/**
 * @author 13629
 * @create 2021/2/27 20:22
 */
public interface StorefrontService {

    int add(Storefront storefront);

    int delete(Integer id);

    int update(Storefront storefront);

    List<Storefront> selectByCondition(Storefront storefront);

    Storefront select(Integer id);
}
