package com.koko.service;

import com.koko.dto.CommentDto;
import com.koko.pojo.Comment;

import java.util.List;

/**
 * @author 13629
 * @create 2021/3/5 10:02
 */
public interface CommentService {

    int add(CommentDto commentDto);

    int delete(Integer id);

    List<CommentDto> query(Integer id);

    List<CommentDto> queryAll();

    int update(Comment comment);
}
