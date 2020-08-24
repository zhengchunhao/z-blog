package com.zch.blog.dao;

import com.zch.blog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Tag)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-20 10:36:22
 */
@Mapper
public interface TagDao {

    /**
     * 通过ID查询单条数据
     *
     * @param tagId 主键
     * @return 实例对象
     */
    Tag queryById(Integer tagId);




    /**
     * 通过实体作为筛选条件查询
     *
     * @param tag 实例对象
     * @return 对象列表
     */
    List<Tag> queryAll(Tag tag);

    /**
     * 新增数据
     *
     * @param tag 实例对象
     * @return 影响行数
     */
    int insert(Tag tag);

    /**
     * 修改数据
     *
     * @param tag 实例对象
     * @return 影响行数
     */
    int update(Tag tag);

    /**
     * 通过主键删除数据
     *
     * @param tagId 主键
     * @return 影响行数
     */
    int deleteById(Integer tagId);

}