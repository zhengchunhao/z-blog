package com.zch.systerm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-08-15 21:48:13
 */
public class User extends  BaseEntity {
    private static final long serialVersionUID = 950008067606183762L;
    /**
     * ID
     */
    private Long id;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 状态：'T'启用、'F'禁用
     */
    private String enabled;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名
     */
    private String username;
    /**
     * 最后修改密码的日期
     */
    private Date lastPasswordResetTime;

    private Long deptId;

    private String phone;

    private Long jobId;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 修改时间
     */
    private Date editTime;
    /**
     * 修改人
     */
    private String editBy;
    /**
     * 性别（'M'：男 'F'：女）
     */
    private String sex;

    /**
      * 删除标志  1 删除  0 为删除
     * */
    private  Integer delFlag;

    /**
     * 用户角色id数组
     * */
    private List<Long> roleIds;

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLastPasswordResetTime() {
        return lastPasswordResetTime;
    }

    public void setLastPasswordResetTime(Date lastPasswordResetTime) {
        this.lastPasswordResetTime = lastPasswordResetTime;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}