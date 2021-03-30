package com.koko.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.koko.dao.StorefrontMapper;
import com.koko.pojo.Storefront;
import com.koko.service.StorefrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    @Override
    public List<Storefront> selectAll() {
        return storefrontMapper.selectAll();
    }

    @Override
    public List<String> selectStorefrontAddress() {
        return storefrontMapper.selectStorefrontAddress();
    }

    public JSONArray selectDistinctAddress(){
        JSONArray result = new JSONArray();
        HashMap<String, Integer> map = new HashMap<>();
        List<String> address = selectStorefrontAddress();
        address.forEach(s -> {
            String substring = s.substring(s.indexOf("省") + 1, s.indexOf("市"));
            if (map.containsKey(substring)){
                map.put(substring, map.get(substring) + 1);
            } else {
                map.put(substring, 1);
            }
        });
        for (String s : map.keySet()){
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("name", s);
            jsonObj.put("count", map.get(s));
            List<String> addressList = storefrontMapper.selectLikeAddress(s);
            jsonObj.put("addressList", addressList);
            result.add(jsonObj);
        }
        return result;
    }
}
