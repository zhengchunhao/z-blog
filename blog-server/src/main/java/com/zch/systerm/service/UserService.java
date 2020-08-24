package com.zch.systerm.service;

import com.zch.systerm.entity.User;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2020-08-04 21:27:18
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Long id);

    /**
     * 查询多条数据
     *
     *
     * @return 对象列表
     */
    List<User> queryAll(User user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    int insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *s
     * @param ids 主键
     * @return 是否成功
     */
    int deleteById(Long[] ids);

    //根据用户名查询用户

    User  loadUserByuserName(String username);


    /**
     * 验证用户名唯一
     * */
    boolean checkUserNameUnique(String userName);

    /**
     * 验证电话号码唯一
     * */
    boolean  checkPhoneUnique(String phone);

    /**
     * 验证邮箱唯一
     * */
    boolean checkEmailUnique(String email);


}