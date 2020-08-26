package com.zch.systerm.service.impl;

import com.zch.framework.security.LoginUser;
import com.zch.systerm.dao.MenuDao;
import com.zch.systerm.entity.Menu;
import com.zch.systerm.entity.Meta;
import com.zch.systerm.entity.Route;
import com.zch.systerm.service.MenuService;
import com.zch.systerm.service.RouteService;
import com.zch.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private MenuDao menuDao;
    @Autowired
    private MenuService menuService;

    @Override
    public List<Route> getRoutes() {
        LoginUser loginUser = SecurityUtils.getLoinUser();
        List<Menu> menuList = menuService.queryMenuByUserId(loginUser.getUser().getId());
        return menusChangeRoutes(menuList);
    }

    @Override
    public List<Route> getAllRoutes(Menu menu) {
        List<Menu> menuList = menuDao.queryAll(menu);
        return menusChangeRoutes(menuList);
    }


    /**
     * 把menu对象变成route对象
     */
    private Route menuChangeRoute(Menu menu) {
        Route route = new Route();
        route.setName(menu.getName());
        route.setRouter(menu.getComponent());
        route.setPath(menu.getPath());
        if(menu.getVisible()==null){
            route.setInvisible(false);
        }else {
            route.setInvisible(menu.getVisible().equals("1" ) ? false : true);
        }

        Meta meta = new Meta();
        meta.setAuthority(menu.getPerms());
        meta.setIcon(menu.getIcon());
        route.setMeta(meta);
        return route;
    }

    /**
     * menu 集合变成前端的route集合
     */
    private List<Route> menusChangeRoutes(List<Menu> menuList) {
        List<Route> routeList = new ArrayList<>();
        for (Menu menu : menuList) {
            Route route = menuChangeRoute(menu);
            if (menu.getChildren() != null) {
                route.setChildren(menusChangeRoutes(menu.getChildren()));
            }
            routeList.add(route);
        }
        return routeList;
    }

}



