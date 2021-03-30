package com.koko.service.impl;

import com.koko.dao.CommentMapper;
import com.koko.dao.CommonMapper;
import com.koko.dto.CommentDto;
import com.koko.pojo.Comment;
import com.koko.pojo.CommentImg;
import com.koko.pojo.User;
import com.koko.service.CommentImgService;
import com.koko.service.CommentService;
import com.koko.service.UserService;
import com.koko.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 13629
 * @create 2021/3/5 10:02
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentImgService commentImgService;

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private UserService userService;


    @Override
    public int add(CommentDto commentDto) {
        Comment comment = new Comment();
        List<String> imgList = commentDto.getImgList();
        ObjectUtils.cloneBean(comment, commentDto);
        commentMapper.insert(comment);
        Integer commonId = commonMapper.getLastId();
        for (String imgUrl : imgList) {
            commentImgService.add(new CommentImg(commonId, imgUrl));
        }
        return 0;
    }

    @Override
    public int delete(Integer id) {
        commentMapper.deleteByPrimaryKey(id);
        commentImgService.delete(id);
        return 0;
    }

    @Override
    public List<CommentDto> query(Integer id) {
        return null;
    }

    @Override
    public List<CommentDto> queryAll() {
        ArrayList<CommentDto> commentDtoList = new ArrayList<>();
        List<Comment> comments = commentMapper.selectAll();
        for (Comment comment : comments) {
            CommentDto commentDto = new CommentDto();
            User temp_user = new User();
            temp_user.setName(comment.getUsername());
            User user = userService.getUserByCondition(temp_user).get(0);
            List<String> imgList = commentImgService.query(comment.getId());
            ObjectUtils.cloneBean(commentDto, comment);
            commentDto.setImgList(imgList);
            commentDto.setAvatar(user.getAvatar());
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }

    @Override
    public int update(Comment comment) {
        commentMapper.updateByPrimaryKeySelective(comment);
        return 0;
    }
}
