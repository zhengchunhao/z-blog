package com.zch.blog.entity;

import java.io.Serializable;

/**
 * (BlogTag)实体类
 *
 * @author makejava
 * @since 2020-08-20 10:37:41
 */
public class BlogTag implements Serializable {
    private static final long serialVersionUID = -29982376381005121L;
    /**
     * 博文id
     */
    private Integer blogId;
    /**
     * 标签id
     */
    private Integer tagId;


    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

}