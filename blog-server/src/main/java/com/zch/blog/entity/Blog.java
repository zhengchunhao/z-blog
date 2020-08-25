package com.zch.blog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (Blog)实体类
 *
 * @author makejava
 * @since 2020-08-20 12:27:40
 */
public class Blog implements Serializable {
    private static final long serialVersionUID = -55696398905540834L;
    /**
     * 唯一博文id--主键
     */
    private Long blogId;
    /**
     * 博文标题
     */
    private String blogTitle;
    /**
     * 博文内容
     */
    private String blogBody;
    /**
     * 博文评论数
     */
    private Integer blogDiscusscount;
    /**
     * 博文浏览数
     */
    private Integer blogBlogviews;
    /**
     * 博文发布时间
     */
    private Date blogTime;
    /**
     * 博文状态--0 删除 1已发布 2未完成
     */
    private Integer blogState;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 图片id
     * */
    private  Long imageId;

    /**
     * 博文点赞总数
     */
    private Integer likeCount;
    /**
     * markdown文章id
     */
    private Long markdowId;

    private String createBy;

    private String editBy;

    private Date createTime;

    private Date editTime;

    private  String introduce;

    private  Integer current;

    private List<Integer> tagIds;

    public List<Integer> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Integer> tagIds) {
        this.tagIds = tagIds;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogBody() {
        return blogBody;
    }

    public void setBlogBody(String blogBody) {
        this.blogBody = blogBody;
    }

    public Integer getBlogDiscusscount() {
        return blogDiscusscount;
    }

    public void setBlogDiscusscount(Integer blogDiscusscount) {
        this.blogDiscusscount = blogDiscusscount;
    }

    public Integer getBlogBlogviews() {
        return blogBlogviews;
    }

    public void setBlogBlogviews(Integer blogBlogviews) {
        this.blogBlogviews = blogBlogviews;
    }

    public Date getBlogTime() {
        return blogTime;
    }

    public void setBlogTime(Date blogTime) {
        this.blogTime = blogTime;
    }

    public Integer getBlogState() {
        return blogState;
    }

    public void setBlogState(Integer blogState) {
        this.blogState = blogState;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Long getMarkdowId() {
        return markdowId;
    }

    public void setMarkdowId(Long markdowId) {
        this.markdowId = markdowId;
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

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }
}