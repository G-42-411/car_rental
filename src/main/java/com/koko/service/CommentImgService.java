package com.koko.service;

import com.koko.pojo.CommentImg;

import java.util.List;

/**
 * @author 13629
 * @create 2021/3/5 9:50
 */
public interface CommentImgService {

    int add(CommentImg commentImg);

    List<String> query(Integer id);

    int delete(Integer id);
}
