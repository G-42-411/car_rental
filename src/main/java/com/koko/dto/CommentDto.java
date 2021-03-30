package com.koko.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author 13629
 * @create 2021/3/5 9:56
 */
@Data
public class CommentDto {
    private Integer id;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone = "GMT+8")
    private Date modificationTime;

    private String username;

    private Integer status;

    private List<String> imgList;

    private String avatar;

}
