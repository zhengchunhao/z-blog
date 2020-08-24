package com.zch.systerm.entity;

import java.io.Serializable;

/**
 * (UserRole)实体类
 *
 * @author makejava
 * @since 2020-08-08 23:48:34
 */
public class UserRole implements Serializable {
    private static final long serialVersionUID = -69528932461751311L;
    /**
     * 主键
     */
    private Integer userRoleId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 角色id
     */
    private Integer roleId;


    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}