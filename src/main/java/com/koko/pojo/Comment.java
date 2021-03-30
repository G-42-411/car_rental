package com.koko.pojo;

import java.util.Date;

public class Comment {
    private Integer id;

    private String content;

    private Date createTime;

    private Date modificationTime;

    private String username;

    private Integer status;

    private Integer storefrontId;

    private Integer hardwareLevel;

    private Integer cleanLevel;

    private Integer shuttleLevel;

    private Integer serveLevel;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStorefrontId() {
        return storefrontId;
    }

    public void setStorefrontId(Integer storefrontId) {
        this.storefrontId = storefrontId;
    }

    public Integer getHardwareLevel() {
        return hardwareLevel;
    }

    public void setHardwareLevel(Integer hardwareLevel) {
        this.hardwareLevel = hardwareLevel;
    }

    public Integer getCleanLevel() {
        return cleanLevel;
    }

    public void setCleanLevel(Integer cleanLevel) {
        this.cleanLevel = cleanLevel;
    }

    public Integer getShuttleLevel() {
        return shuttleLevel;
    }

    public void setShuttleLevel(Integer shuttleLevel) {
        this.shuttleLevel = shuttleLevel;
    }

    public Integer getServeLevel() {
        return serveLevel;
    }

    public void setServeLevel(Integer serveLevel) {
        this.serveLevel = serveLevel;
    }
}