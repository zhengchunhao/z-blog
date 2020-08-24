package com.zch.systerm.service;

import com.zch.systerm.entity.Role;

import java.util.List;
import java.util.Set;

/**
 * (Role)表服务接口
 *
 * @author makejava
 * @since 2020-08-08 23:37:43
 */
public interface RoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Role queryById(Long id);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<Role> queryAll(Role role);

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    int insert(Role role);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    int update(Role role);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    int deleteByIds(Long[] ids);

    /**
     * 查询某个用户的角色集合
     */
    Set<String> queryRolePermission(Long id);


    /**
     * 角色键值验重
     */
    public boolean checkRoleKeyUnique(String roleKey);

    /**
     * 角色授权
     * */
    int authorize(Long[] ids,Long roleId);

    /**
     * 用户的所有角色
     * */
    List<Role> userRoles(Long userId);
}