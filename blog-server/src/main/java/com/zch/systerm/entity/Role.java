package com.zch.systerm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2020-08-08 23:37:43
 */
public class Role extends BaseEntity  {
    private static final long serialVersionUID = 472097986270354973L;
    /**
     * ID
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;

    private String dataScope;

    private Integer level;
    /**
     * 角色键值
     */
    private String roleKey;

    private String createBy;

    private String editBy;

    private Date createTime;

    private Date editTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

}