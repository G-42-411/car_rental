package com.koko.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStringCommands.SetOption;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * Redis service 类 
 */
@Service
public class RedisUtils {
    public final static Logger logger = LoggerFactory.getLogger(RedisUtils.class);
 
    @SuppressWarnings("rawtypes")
	@Autowired
    private RedisTemplate redisTemplate;
    /**
     * 写入缓存
     * @param key
     * @param value
     * @return Boolean
     */
    @SuppressWarnings("unchecked")
	public boolean set(final String key , Object value){
        boolean result = false;
        try {
            ValueOperations<Serializable , Object> valueOperations = redisTemplate.opsForValue();
            valueOperations.set(key , value);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
 
    /**
     * 写入缓存设置时效时间
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    @SuppressWarnings("unchecked")
	public boolean set(final String key , Object value , Long expireTime){
        boolean result = false;
        try {
            ValueOperations<Serializable , Object> valueOperations = redisTemplate.opsForValue();
            valueOperations.set(key , value);
            redisTemplate.expire(key , expireTime , TimeUnit.MILLISECONDS);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
 
    /**
     * 批量删除对应的value
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }
 
    /**
     * 批量删除key
     * @param pattern
     */
    @SuppressWarnings("unchecked")
	public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }
    /**
     * 删除对应的value
     * @param key
     */
    @SuppressWarnings("unchecked")
	public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }
    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
	public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }
    /**
     * 读取缓存
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
	public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }
    /**
     * 哈希 添加
     * @param key
     * @param hashKey
     * @param value
     */
    @SuppressWarnings("unchecked")
	public void hmSet(String key, Object hashKey, Object value){
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key,hashKey,value);
    }
 
    /**
     * 哈希获取数据
     * @param key
     * @param hashKey
     * @return
     */
    @SuppressWarnings("unchecked")
	public Object hmGet(String key, Object hashKey){
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.get(key,hashKey);
    }
 
    /**
     * 列表添加
     * @param k
     * @param v
     */
    @SuppressWarnings("unchecked")
	public void lPush(String k,Object v){
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k,v);
    }
 
    /**
     * 列表获取
     * @param k
     * @param l
     * @param l1
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Object> lRange(String k, long l, long l1){
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k,l,l1);
    }
 
    /**
     * 集合添加
     * @param key
     * @param value
     */
    @SuppressWarnings("unchecked")
	public void add(String key,Object value){
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key,value);
    }
 
    /**
     * 集合获取
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
	public Set<Object> setMembers(String key){
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }
 
    /**
     * 有序集合添加
     * @param key
     * @param value
     * @param scoure
     */
    @SuppressWarnings("unchecked")
	public void zAdd(String key,Object value,double scoure){
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key,value,scoure);
    }
 
    /**
     * 有序集合获取
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    @SuppressWarnings("unchecked")
	public Set<Object> rangeByScore(String key,double scoure,double scoure1){
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }

    public Boolean setIfAbsent(String key, String value, long timeout, TimeUnit unit) {
        return getRedisConnection().set(
                key.getBytes(), value.getBytes(),
                Expiration.from(timeout, unit), SetOption.ifAbsent());
    }
    private RedisConnection getRedisConnection() {
        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
        Assert.state(factory != null, "No RedisConnectionFactory");
        return factory.getConnection();
    }
}