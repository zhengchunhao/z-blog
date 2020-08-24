package com.zch.systerm.service;

import com.zch.systerm.entity.User;

import java.util.Set;

public interface PermissionService {

   Set<String> getMenuPermissionByUser(User user);

   Set<String> getRolePermission(User user);



}
