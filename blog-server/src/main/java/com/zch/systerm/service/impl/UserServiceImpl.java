package com.zch.systerm.service.impl;

import com.zch.systerm.dao.UserDao;
import com.zch.systerm.dao.UserRoleDao;
import com.zch.systerm.entity.User;
import com.zch.systerm.entity.UserRole;
import com.zch.systerm.service.UserService;
import com.zch.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-08-04 21:27:18
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRoleDao userRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Long id) {
        return this.userDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<User> queryAll(User user) {

        return userDao.queryAll(user);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public int insert(User user) {
        User user1= SecurityUtils.getLoinUser().getUser();
        user.setCreateBy(user1.getUsername());
        user.setCreateTime(new Date());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.userDao.insert(user);
        return  userRoleDao.insertRecords(user.getId(),user.getRoleIds());
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public int update(User user) {
        User user1= SecurityUtils.getLoinUser().getUser();
        user.setEditTime(new Date());
        user.setEditBy(user1.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.userDao.update(user);
        UserRole userRole=new UserRole();
        userRole.setUserId(user.getId().intValue());
        List<UserRole> list =userRoleDao.queryAll(userRole);
        if(list!=null&&list.size()>0){
            userRoleDao.deleteByUserId(user.getId());
        }

     return    userRoleDao.insertRecords(user.getId(),user.getRoleIds());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Long[] ids) {
        return this.userDao.deleteById(ids) ;
    }

    //根据用户名查询用户
    @Override
    public User  loadUserByuserName(String username){
        return  userDao.loadUserByuserName(username);
    }

    /**
     * 验证用户名唯一
     * */
    public boolean checkUserNameUnique(String userName){
     return  userDao.selectCountByUserName(userName)>0 ? false : true;
    }

    /**
     * 验证电话号码唯一
     * */
   public boolean  checkPhoneUnique(String phone){
     return  userDao.selectCountByPhone(phone)>0 ? false:true;
    }

    /**
     * 验证邮箱唯一
     * */
  public   boolean checkEmailUnique(String email){
    return  userDao.selectCountByEmail(email) >0 ? false:true;
  }


}