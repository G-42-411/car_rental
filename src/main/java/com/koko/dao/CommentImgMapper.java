package com.koko.dao;

import com.koko.pojo.CommentImg;

import java.util.List;

public interface CommentImgMapper {
    int insert(CommentImg record);

    int insertSelective(CommentImg record);

    List<String> selectById(Integer id);

    int deleteById(Integer id);
}