package com.zch.systerm.service;

import com.zch.systerm.entity.Menu;
import com.zch.systerm.entity.TreeEntity;

import java.util.List;
import java.util.Set;

/**
 * (Menu)表服务接口
 *
 * @author makejava
 * @since 2020-08-08 23:47:43
 */
public interface MenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Menu queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Menu> queryAll();

    /**
     * 查询全部
     */
    List<Menu> queryAll(Menu menu);

    /**
     * 新增数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    int insert(Menu menu);

    /**
     * 修改数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    int update(Menu menu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    int deleteById(Long id);


    /**
     * 根据用户id查询菜单权限
     */
    Set<String> queryPermsByUserId(Long userId);

    /**
     * 获取单个用户菜单
     */
    List<Menu> queryMenuByUserId(Long userId);


    /**
     * 获取整个菜单权限树形结构
     */
    List<TreeEntity> menuPermsTrees();

    /**
     * 该权限的菜单
     * */
    List<Long> roleMenuPermsTrees(Long roleId);

}