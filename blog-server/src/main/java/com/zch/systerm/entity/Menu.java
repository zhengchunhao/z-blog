package com.zch.systerm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (Menu)实体类
 *
 * @author makejava
 * @since 2020-08-09 08:28:15
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = -21903591673947883L;
    /**
     * ID
     */
    private Long id;
    /**
     * 是否外链
     */
    private Long iFrame;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 组件
     */
    private String component;
    /**
     * 上级菜单ID
     */
    private Long pid;
    /**
     * 排序
     */
    private Long sort;
    /**
     * 图标
     */
    private String icon;
    /**
     * 链接地址
     */
    private String path;

    private String createBy;

    private String editBy;

    private Date createTime;

    private Date editTime;
    /**
     * 菜单类型（C 菜单，M 目录 A按钮）
     */
    private String menuType;
    /**
     * 是否可见（0 不可见 1 可见）
     */
    private String visible;
    /**
     * 权限标识
     */
    private String perms;

    private List<Menu> children;


    public Long getiFrame() {
        return iFrame;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public void setiFrame(Long iFrame) {
        this.iFrame = iFrame;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIFrame() {
        return iFrame;
    }

    public void setIFrame(Long iFrame) {
        this.iFrame = iFrame;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

}