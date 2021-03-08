package com.koko.controller.comment;

import com.koko.dto.CommentDto;
import com.koko.dto.CommonResult;
import com.koko.pojo.Comment;
import com.koko.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 13629
 * @create 2021/3/5 10:00
 */
@Slf4j
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/addComment")
    public CommonResult addComment(@RequestBody CommentDto commentDto){
        commentService.add(commentDto);
        return CommonResult.ok();
    }

    @GetMapping("/queryCommentList")
    public CommonResult queryCommentList(){
        List<CommentDto> commentDtoList = commentService.queryAll();
        return CommonResult.ok(commentDtoList);
    }

    @GetMapping("/deleteComment")
    public CommonResult deleteComment(Integer id){
        commentService.delete(id);
        return CommonResult.ok();
    }

    @PostMapping("/updateComment")
    public CommonResult updateComment(@RequestBody Comment comment){
        commentService.update(comment);
        return CommonResult.ok();
    }

}
