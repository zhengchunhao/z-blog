package com.zch.systerm.service.impl;

import com.zch.systerm.dao.RoleDao;
import com.zch.systerm.dao.RolesMenusDao;
import com.zch.systerm.entity.Role;
import com.zch.systerm.entity.User;
import com.zch.systerm.service.RoleService;
import com.zch.utils.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * (Role)表服务实现类
 *
 * @author makejava
 * @since 2020-08-08 23:37:43
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;
    @Autowired
    private RolesMenusDao rolesMenusDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Role queryById(Long id) {
        return this.roleDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Role> queryAll(Role role) {
        return this.roleDao.queryAll(role);
    }

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Role role) {
        User user=new SecurityUtils().getLoinUser().getUser();
        role.setCreateTime(new Date());
        role.setCreateBy(user.getUsername());
        return this.roleDao.insert(role);
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Role role) {
        User user=new SecurityUtils().getLoinUser().getUser();
        role.setEditTime(new Date());
        role.setCreateBy(user.getUsername());
        return this.roleDao.update(role);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteByIds(Long[] ids) {
        return this.roleDao.deleteByIds(ids);
    }

    /**
     * 查询某个用户的角色集合
     */
    public Set<String> queryRolePermission(Long id) {
        List<String> roleList = roleDao.queryRolePermission(id);
        Set<String> set = new HashSet<>();
        for (String perms : roleList) {
            if (StringUtils.isNotEmpty(perms)) {
                set.addAll(Arrays.asList(perms.trim().split(",")));
            }
        }
        return set;
    }

    /**
     * 角色键值验重
     */
    public boolean checkRoleKeyUnique(String roleKey) {
        return roleDao.selectCountByRoleKey(roleKey) > 0 ? true : false;
    }

    /**
     * 角色授权
     * */

    @Transactional
  public   int authorize(Long[] ids,Long roleId){
             rolesMenusDao.deleteByRoleId(roleId);
      return  rolesMenusDao.insertPermsForMenu(ids,roleId);
  }
    /**
     * 用户的所有角色
     * */
   public List<Role> userRoles(Long userId){
     return  roleDao.selectRolesByUserId(userId);
   }

}