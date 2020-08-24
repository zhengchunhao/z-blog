package com.zch.blog.service.impl;

import com.zch.blog.dao.BlogDao;
import com.zch.blog.dao.BlogTagDao;
import com.zch.blog.dao.TagDao;
import com.zch.blog.entity.Blog;
import com.zch.blog.entity.BlogTag;
import com.zch.blog.entity.Tag;
import com.zch.blog.service.BlogService;
import com.zch.framework.security.LoginUser;
import com.zch.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.Date;
import java.util.List;

/**
 * (Blog)表服务实现类
 *
 * @author makejava
 * @since 2020-08-20 10:38:05
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogDao blogDao;
    @Autowired
    private BlogTagDao blogTagDao;
    @Autowired
    private TagDao tagDao;

    /**
     * 通过ID查询单条数据
     *
     * @param blogId 主键
     * @return 实例对象
     */
    @Override
    public Blog queryById(Long blogId) {
      Blog blog= this.blogDao.queryById(blogId);
      List<Integer> list=blogTagDao.selectTagIds(blogId);
      blog.setTagIds(list);
       return  blog;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Blog> queryAllByLimit(int offset, int limit) {
        return this.blogDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param blog 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public int insert(Blog blog) {
        LoginUser loginUser= SecurityUtils.getLoinUser();
        blog.setUserId(loginUser.getUser().getId());
        blog.setCreateTime(new Date());
        this.blogDao.insert(blog);
        List<Integer> list=blog.getTagIds();
        for (Integer tarId:list){
            Tag tag=tagDao.queryById(tarId);
            tag.setBlogCount(tag.getBlogCount()+1);
            tagDao.update(tag);
        }
        return blogTagDao.insertBlogTags(blog.getBlogId(),blog.getTagIds());
    }

    /**
     * 修改数据
     *
     * @param blog 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public int update(Blog blog) {
        blogTagDao.deleteById(blog.getBlogId());
        blogTagDao.insertBlogTags(blog.getBlogId(),blog.getTagIds());
        return this.blogDao.update(blog);
    }

    /**
     * 通过主键删除数据
     *
     * @param blogId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long blogId) {
        return this.blogDao.deleteById(blogId) > 0;
    }
}