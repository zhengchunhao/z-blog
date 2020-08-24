package com.zch.systerm.controller;

import com.zch.framework.security.LoginBody;
import com.zch.framework.security.LoginSevice;
import com.zch.framework.security.ToeknService;
import com.zch.systerm.entity.Menu;
import com.zch.systerm.entity.User;
import com.zch.systerm.service.PermissionService;
import com.zch.systerm.service.RouteService;
import com.zch.systerm.service.UserService;
import com.zch.utils.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    LoginSevice loginSevice;
    @Autowired
    ToeknService toeknService;
    @Autowired
    PermissionService permissionService;
    @Autowired
    RouteService routeService;

    /**
     * 用户注册
     * */
    @PostMapping("/register")
    public  String register(@RequestBody User user){
       user.setPassword(new  BCryptPasswordEncoder().encode(user.getPassword()));
       userService.insert(user);
       return  "ok";
    }
   /**
    * 登录
    * */
    @PostMapping("/login")
    public MyResponse login(@RequestBody LoginBody loginBody){
       MyResponse myResponse= loginSevice.login(loginBody.getUsername(),loginBody.getPassword(),loginBody.getUuid(),loginBody.getCode());
      return  myResponse;
    }
    /**
     * 拉取用户信息
     * */
    @GetMapping("/userInfo")
   public MyResponse userInfo(HttpServletRequest request){
       User user=toeknService.getLoginUser(request).getUser();
        Set<String> permission=permissionService.getMenuPermissionByUser(user);
        Set<String>  roles=permissionService.getRolePermission(user);
        MyResponse myResponse=  MyResponse.success();
        myResponse.put("user",user);
        myResponse.put("roles",roles);
        myResponse.put("perms",permission);
        return  myResponse;
    }

    /**
     * 获取路由配置(返回用户对应的有权限的菜单)
     * */
   @GetMapping("/userRoutes")
    public MyResponse getRouteConfig(){

         return MyResponse.success(routeService.getRoutes());
   }

   /**
    *获取全部菜单
    * */
   @GetMapping("/allRouets")
    public  MyResponse getAllRoute(){
       Menu menu=new Menu();
     return MyResponse.success(routeService.getAllRoutes(menu)) ;

   }


}
