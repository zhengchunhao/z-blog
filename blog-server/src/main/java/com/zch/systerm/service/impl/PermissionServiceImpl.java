package com.zch.systerm.service.impl;

import com.zch.systerm.entity.User;
import com.zch.systerm.service.MenuService;
import com.zch.systerm.service.PermissionService;
import com.zch.systerm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private MenuService menuService;

    @Autowired

    private RoleService roleService;

    @Override
    public Set<String> getMenuPermissionByUser(User user) {
        Set<String> perms = new HashSet<>();
        perms.addAll(menuService.queryPermsByUserId(user.getId()));
        return perms;
    }

    @Override
    public Set<String> getRolePermission(User user) {
        Set<String> perms = new HashSet<>();
        perms.addAll(roleService.queryRolePermission(user.getId()));
        return perms;
    }


}
