package com.zch.systerm.service.impl;

import com.zch.systerm.dao.UserRoleDao;
import com.zch.systerm.entity.UserRole;
import com.zch.systerm.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserRole)表服务实现类
 *
 * @author makejava
 * @since 2020-08-08 23:48:34
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleDao userRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userRoleId 主键
     * @return 实例对象
     */
    @Override
    public UserRole queryById(Integer userRoleId) {
        return this.userRoleDao.queryById(userRoleId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<UserRole> queryAllByLimit(int offset, int limit) {
        return this.userRoleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    @Override
    public UserRole insert(UserRole userRole) {
        this.userRoleDao.insert(userRole);
        return userRole;
    }

    /**
     * 修改数据
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    @Override
    public UserRole update(UserRole userRole) {
        this.userRoleDao.update(userRole);
        return this.queryById(userRole.getUserRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userRoleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer userRoleId) {
        return this.userRoleDao.deleteById(userRoleId) > 0;
    }

}