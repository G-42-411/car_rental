package com.koko.pojo;

import java.util.Date;

public class Comment {
    private Integer id;

    private String content;

    private Date creationTime;

    private Date modificationTime;

    private Integer userId;

    private Integer level;

    private Integer parentId;

    private Integer topThumb;

    private Integer revertNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getTopThumb() {
        return topThumb;
    }

    public void setTopThumb(Integer topThumb) {
        this.topThumb = topThumb;
    }

    public Integer getRevertNumber() {
        return revertNumber;
    }

    public void setRevertNumber(Integer revertNumber) {
        this.revertNumber = revertNumber;
    }
}