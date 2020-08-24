package com.zch.systerm.dao;

import com.zch.systerm.entity.Image;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.ManagedBean;
import java.util.List;

/**
 * (Image)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-21 08:34:54
 */
@Mapper
public interface ImageDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Image queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Image> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param image 实例对象
     * @return 对象列表
     */
    List<Image> queryAll(Image image);

    /**
     * 新增数据
     *
     * @param image 实例对象
     * @return 影响行数
     */
    int insert(Image image);

    /**
     * 修改数据
     *
     * @param image 实例对象
     * @return 影响行数
     */
    int update(Image image);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}