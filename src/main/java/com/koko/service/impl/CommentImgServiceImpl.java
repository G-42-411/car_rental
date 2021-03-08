package com.koko.service.impl;

import com.koko.dao.CommentImgMapper;
import com.koko.dao.CommentMapper;
import com.koko.pojo.CommentImg;
import com.koko.service.CommentImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 13629
 * @create 2021/3/5 9:52
 */
@Service
public class CommentImgServiceImpl implements CommentImgService {

    @Autowired
    private CommentImgMapper commentImgMapper;

    @Override
    public int add(CommentImg commentImg) {
        return commentImgMapper.insert(commentImg);
    }

    @Override
    public List<String> query(Integer id) {
        return commentImgMapper.selectById(id);
    }

    @Override
    public int delete(Integer id) {
        return commentImgMapper.deleteById(id);
    }
}
