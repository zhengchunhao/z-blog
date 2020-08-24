package com.zch.systerm.service;

import com.zch.systerm.entity.RolesMenus;

import java.util.List;

/**
 * (RolesMenus)表服务接口
 *
 * @author makejava
 * @since 2020-08-08 23:49:06
 */
public interface RolesMenusService {

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    RolesMenus queryById(Long menuId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<RolesMenus> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param rolesMenus 实例对象
     * @return 实例对象
     */
    RolesMenus insert(RolesMenus rolesMenus);

    /**
     * 修改数据
     *
     * @param rolesMenus 实例对象
     * @return 实例对象
     */
    RolesMenus update(RolesMenus rolesMenus);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    boolean deleteById(Long menuId);

}