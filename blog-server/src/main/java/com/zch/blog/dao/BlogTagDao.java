package com.zch.blog.dao;

import com.zch.blog.entity.BlogTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (BlogTag)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-20 10:37:41
 */
@Mapper
public interface BlogTagDao {

    /**
     * 通过ID查询单条数据
     *
     * @param 主键
     * @return 实例对象
     */
    BlogTag queryById();

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<BlogTag> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param blogTag 实例对象
     * @return 对象列表
     */
    List<BlogTag> queryAll(BlogTag blogTag);

    /**
     * 新增数据
     *
     * @param blogTag 实例对象
     * @return 影响行数
     */
    int insert(BlogTag blogTag);

    /**
     * 修改数据
     *
     * @param blogTag 实例对象
     * @return 影响行数
     */
    int update(BlogTag blogTag);

    /**
     * 通过主键删除数据
     *
     * @param 主键
     * @return 影响行数
     */
    int deleteById(Long blogId);

   /**
    *批量插入
    * */

    int insertBlogTags(@Param("blogId") Long blofId,@Param("tagIds") List<Integer> tagIds);


    /**
     * 根据blog查询tagids
     * */
    List<Integer> selectTagIds(Long blogId);

    /**
     * 查询tagId下的博文个数
     * */
     int selectBlogIdsIntagId(Integer tarId);

     int deleteBytagIdBlogId(@Param("blogId") Long blogId,@Param("tagId") Integer tagId);
}