package com.zch.blog.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Tag)实体类
 *
 * @author makejava
 * @since 2020-08-20 12:28:05
 */
public class Tag implements Serializable {
    private static final long serialVersionUID = -53644854890759912L;
    /**
     * 唯一标签id--主键
     */
    private Long tagId;
    /**
     * 标签名
     */
    private String tagName;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 标签介绍
     */
    private String tagIntroduce;
    /**
     * 图片地址id
     */
    private Long imageId;

    /**
     * 博文数量
     * */
    private Integer blogCount;

    private String imagePath;

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    private String createBy;

    private String editBy;

    private Date createTime;

    private Date editTime;




    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTagIntroduce() {
        return tagIntroduce;
    }

    public void setTagIntroduce(String tagIntroduce) {
        this.tagIntroduce = tagIntroduce;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

}