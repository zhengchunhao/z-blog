package com.zch.framework.security;

import com.zch.systerm.entity.User;
import com.zch.systerm.service.PermissionService;
import com.zch.systerm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("SecurityUserDetailsServiceImpl")
public class SecurityUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.loadUserByuserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return createLoginUser(user);
    }


    private LoginUser createLoginUser(User user) {
        Set<String> permission = permissionService.getMenuPermissionByUser(user);
        return new LoginUser(user, permission);
    }

}
