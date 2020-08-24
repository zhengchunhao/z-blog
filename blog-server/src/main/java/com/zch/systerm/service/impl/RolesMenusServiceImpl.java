package com.zch.systerm.service.impl;

import com.zch.systerm.dao.RolesMenusDao;
import com.zch.systerm.entity.RolesMenus;
import com.zch.systerm.service.RolesMenusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (RolesMenus)表服务实现类
 *
 * @author makejava
 * @since 2020-08-08 23:49:06
 */
@Service("rolesMenusService")
public class RolesMenusServiceImpl implements RolesMenusService {
    @Resource
    private RolesMenusDao rolesMenusDao;

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    @Override
    public RolesMenus queryById(Long menuId) {
        return this.rolesMenusDao.queryById(menuId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<RolesMenus> queryAllByLimit(int offset, int limit) {
        return this.rolesMenusDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param rolesMenus 实例对象
     * @return 实例对象
     */
    @Override
    public RolesMenus insert(RolesMenus rolesMenus) {
        this.rolesMenusDao.insert(rolesMenus);
        return rolesMenus;
    }

    /**
     * 修改数据
     *
     * @param rolesMenus 实例对象
     * @return 实例对象
     */
    @Override
    public RolesMenus update(RolesMenus rolesMenus) {
        this.rolesMenusDao.update(rolesMenus);
        return this.queryById(rolesMenus.getMenuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long menuId) {
        return this.rolesMenusDao.deleteById(menuId) > 0;
    }
}