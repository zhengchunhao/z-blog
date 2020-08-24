package com.zch.systerm.service.impl;

import com.zch.systerm.dao.MenuDao;
import com.zch.systerm.entity.Menu;
import com.zch.systerm.entity.TreeEntity;
import com.zch.systerm.entity.User;
import com.zch.systerm.service.MenuService;
import com.zch.utils.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * (Menu)表服务实现类
 *
 * @author makejava
 * @since 2020-08-08 23:47:43
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuDao menuDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Menu queryById(Long id) {

        return this.menuDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Menu> queryAll() {
        List<Menu> menuList = menuDao.queryAllOrderBy();
        List<Menu> rootList = getMenuRootList(menuList);
        for (Menu menu : rootList) {
            menu.setChildren(getMenuChild(menu.getId(), menuList));
        }
        return rootList;
    }

    /**
     * 查询菜单列表
     */
    public List<Menu> queryAll(Menu menu) {
        return menuDao.queryAll(menu);
    }


    /**
     * 新增数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Menu menu) {
        User user = SecurityUtils.getLoinUser().getUser();
        menu.setCreateBy(user.getUsername());
        menu.setCreateTime(new Date());
        return menuDao.insert(menu);

    }

    /**
     * 修改数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Menu menu) {

        return this.menuDao.update(menu);

    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Long id) {
        return this.menuDao.deleteById(id);
    }

    /**
     * 根据用户id查询菜单权限
     */
    @Override
    public Set<String> queryPermsByUserId(Long userId) {
        List<String> perms = menuDao.queryPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 获取用户下菜单
     */
    public List<Menu> queryMenuByUserId(Long userId) {
        List<Menu> menuList = menuDao.queryMenuByUserId(userId);
        List<Menu> rootList = getMenuRootList(menuList);
        for (Menu menu : rootList) {
            menu.setChildren(getMenuChild(menu.getId(), menuList));
        }
        return rootList;
    }

    /**
     * 获取菜单所有根节点
     */

    private List<Menu> getMenuRootList(List<Menu> menuList) {
        List<Menu> menuRootList = new ArrayList<>();
        for (Menu menu : menuList) {
            if (menu.getPid() == -1) {
                menuRootList.add(menu);
            }
        }
        return menuRootList;
    }

    /**
     * 递归取出子菜单
     */
    private List<Menu> getMenuChild(Long pid, List<Menu> menuList) {
        List<Menu> childMenus = new ArrayList<>();
        for (Menu menu : menuList) {
            if (menu.getPid() == pid) {
                childMenus.add(menu);
            }
        }
        for (Menu menu : childMenus) {
            //路径为空的还有子级
            if (menu.getPath() == null || "".equals(menu.getPath())) {
                menu.setChildren(getMenuChild(menu.getId(), menuList));
            }
        }
        if (childMenus == null) {
            return null;
        }
        return childMenus;
    }

    /**
     * 获取整个菜单权限树形结构
     */
    public List<TreeEntity> menuPermsTrees() {
        List<Menu> menuList = queryAll();
        return menuToTree(menuList);
    }

    /**
     * 菜单权限转成树结构
     */
    private List<TreeEntity> menuToTree(List<Menu> menuList) {
        List<TreeEntity> treeEntities = new ArrayList<>();
        for (Menu menu : menuList) {
            TreeEntity tree = new TreeEntity();
            tree.setTitle(menu.getName());
            tree.setKey(menu.getId());
            if (menu.getChildren() != null) {
                tree.setChildren(menuToTree(menu.getChildren()));
            }
            treeEntities.add(tree);
        }
        return treeEntities;
    }

    /**
     * 该权限的授权菜单
     * */
   public List<Long> roleMenuPermsTrees(Long roleId){
    List<Menu> list=menuDao.queryMenusByRoleId(roleId);
    List<Long> ids=new ArrayList<>();
    for (Menu menu:list){
        ids.add(menu.getId());
    }
    return  ids;
   }
}