package com.zch.systerm.dao;

import com.zch.systerm.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-04 21:27:18
 */
@Mapper
public interface UserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit();


    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> queryAll(User user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long[] ids);


    //根据用户名查询用户

    User  loadUserByuserName(String username);


    /**
     * 根据用户名查询用户个数
     * */
    int selectCountByUserName(String userName);


    /**
     * 根据用户名查询用户个数
     * */
    int  selectCountByPhone(String phone);


    /**
     * 验证邮箱唯一
     * */
    int selectCountByEmail(String email);

}