package com.zch.blog.service;

import com.zch.blog.entity.Blog;

import java.util.List;

/**
 * (Blog)表服务接口
 *
 * @author makejava
 * @since 2020-08-20 10:38:05
 */
public interface BlogService {

    /**
     * 通过ID查询单条数据
     *
     * @param blogId 主键
     * @return 实例对象
     */
    Blog queryById(Long blogId);

    /**
     * 查询多条数据
     * @return 对象列表
     */
    List<Blog> queryAll(Blog blog,Long tagId);

    /**
     * 新增数据
     *
     * @param blog 实例对象
     * @return 实例对象
     */
    int insert(Blog blog);



    /**
     * 修改数据
     *
     * @param blog 实例对象
     * @return 实例对象
     */
    int update(Blog blog);

    /**
     * 通过主键删除数据
     *
     * @param blogId 主键
     * @return 是否成功
     */
    int deleteById(Long blogId,Integer tagId);

}