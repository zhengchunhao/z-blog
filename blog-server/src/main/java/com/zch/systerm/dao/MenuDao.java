package com.zch.systerm.dao;

import com.zch.systerm.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * (Menu)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-08 23:47:43
 */
@Mapper
public interface MenuDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Menu queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Menu> queryAllOrderBy();


    /**
     * 通过实体作为筛选条件查询
     *
     * @param menu 实例对象
     * @return 对象列表
     */
    List<Menu> queryAll(Menu menu);

    /**
     * 新增数据
     *
     * @param menu 实例对象
     * @return 影响行数
     */
    int insert(Menu menu);

    /**
     * 修改数据
     *
     * @param menu 实例对象
     * @return 影响行数
     */
    int update(Menu menu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据用户id查询菜单权限
     * */

    List<String> queryPermsByUserId(Long userId);

    /**
     * 查询该用户菜单列表
     * */

    List<Menu> queryMenuByUserId(Long userId);

    /**
     * 根据角色id查询菜单
     * */
   List<Menu> queryMenusByRoleId(Long roleId);


}