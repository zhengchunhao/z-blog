package com.zch.systerm.dao;

import com.zch.systerm.entity.RolesMenus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (RolesMenus)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-08 23:49:06
 */
@Mapper
public interface RolesMenusDao {

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    RolesMenus queryById(Long menuId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<RolesMenus> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param rolesMenus 实例对象
     * @return 对象列表
     */
    List<RolesMenus> queryAll(RolesMenus rolesMenus);

    /**
     * 新增数据
     *
     * @param rolesMenus 实例对象
     * @return 影响行数
     */
    int insert(RolesMenus rolesMenus);

    /**
     * 修改数据
     *
     * @param rolesMenus 实例对象
     * @return 影响行数
     */
    int update(RolesMenus rolesMenus);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 影响行数
     */
    int deleteById(Long menuId);


    /**
     *  添加角色与菜单关联
     * */
    int  insertPermsForMenu(@Param("menuIds") Long ids[],@Param("roleId") Long roleId);

    /**
     * 根据角色id删除
     * */
    int deleteByRoleId(Long roleId);

}