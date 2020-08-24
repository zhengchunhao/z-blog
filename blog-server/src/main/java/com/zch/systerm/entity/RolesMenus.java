package com.zch.systerm.entity;

import java.io.Serializable;

/**
 * (RolesMenus)实体类
 *
 * @author makejava
 * @since 2020-08-08 23:49:05
 */
public class RolesMenus implements Serializable {
    private static final long serialVersionUID = -57482521813763412L;
    /**
     * 菜单ID
     */
    private Long menuId;
    /**
     * 角色ID
     */
    private Long roleId;


    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}