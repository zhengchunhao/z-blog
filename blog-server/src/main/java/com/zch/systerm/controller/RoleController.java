package com.zch.systerm.controller;

import com.zch.common.TablePageResult;
import com.zch.systerm.entity.BaseEntity;
import com.zch.systerm.entity.Role;
import com.zch.systerm.service.MenuService;
import com.zch.systerm.service.RoleService;
import com.zch.utils.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (Role)表控制层
 *
 * @author makejava
 * @since 2020-08-08 23:37:43
 */
@RestController
public class RoleController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/role/{id}")
    public Role selectOne(@PathVariable("id") Long id) {
        return this.roleService.queryById(id);
    }

    @GetMapping("/roles")
    public TablePageResult roleList(Role role) {
        return tablePageResult(roleService.queryAll(role));
    }
    @GetMapping("/allRoles")
    public  MyResponse getAllRoles(Role role){
        return  MyResponse.success(roleService.queryAll(role));
    }
    @GetMapping("/user/{userId}/rolesId")
    public  MyResponse getUserRoleIds(@PathVariable("userId") Long userId){
      List<Role> roleList=  roleService.userRoles(userId);
      List <Long> list=new ArrayList<>();
      for (Role role:roleList){
          list.add(role.getId());
      }
      return  MyResponse.success(list);
    }

    @PostMapping("/role")
    public MyResponse add(@RequestBody Role role) {
        return result(roleService.insert(role));
    }

    @PutMapping("/role")
    public MyResponse update(@RequestBody Role role) {
        return result(roleService.update(role));
    }

    @DeleteMapping("/roles/{ids}")
    public MyResponse delete(@PathVariable("ids") Long[] ids) {
        return result(roleService.deleteByIds(ids));
    }

    @GetMapping("/permsTree")
    public MyResponse permsTree() {
        return MyResponse.success(menuService.menuPermsTrees());
    }

    @GetMapping("/rolePermsTree/{roleId}")
    public  MyResponse  rolePermsTree(@PathVariable("roleId") Long roleId){
      return  MyResponse.success(menuService.roleMenuPermsTrees(roleId));
    }


    @GetMapping("/checkRoleKeyUnique")
    public boolean checkRoleKeyUnique(@RequestParam("roleKey") String roleKey, @RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            if (roleService.checkRoleKeyUnique(roleKey)) {
                Role role = roleService.queryById(id);
                if (role.getRoleKey().equals(roleKey)) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } else {
            return roleService.checkRoleKeyUnique(roleKey);
        }
    }

    /**
     * 角色授权
     * */
    @GetMapping("/role/{roleId}/perms/{ids}")
    public  MyResponse authorize(@PathVariable(value = "roleId") Long roleId,@PathVariable(value = "ids",required = false)Long ids[]){
      return  result(roleService.authorize(ids,roleId));
    }

}