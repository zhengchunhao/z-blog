package com.zch.blog.service.impl;

import com.zch.blog.dao.BlogTagDao;
import com.zch.blog.entity.BlogTag;
import com.zch.blog.service.BlogTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (BlogTag)表服务实现类
 *
 * @author makejava
 * @since 2020-08-20 10:37:41
 */
@Service("blogTagService")
public class BlogTagServiceImpl implements BlogTagService {
    @Resource
    private BlogTagDao blogTagDao;

    /**
     * 通过ID查询单条数据
     *
     * @param 主键
     * @return 实例对象
     */
    @Override
    public BlogTag queryById() {
        return this.blogTagDao.queryById();
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<BlogTag> queryAllByLimit(int offset, int limit) {
        return this.blogTagDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param blogTag 实例对象
     * @return 实例对象
     */
    @Override
    public BlogTag insert(BlogTag blogTag) {
        this.blogTagDao.insert(blogTag);
        return blogTag;
    }

    /**
     * 修改数据
     *
     * @param blogTag 实例对象
     * @return 实例对象
     */
    @Override
    public BlogTag update(BlogTag blogTag) {
        this.blogTagDao.update(blogTag);
        return this.queryById();
    }

    /**
     * 通过主键删除数据
     *
     * @param 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Long blogId) {
        return this.blogTagDao.deleteById(blogId) ;
    }
}