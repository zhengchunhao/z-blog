package com.zch.systerm.controller;

import com.zch.systerm.entity.Menu;
import com.zch.systerm.service.MenuService;
import com.zch.utils.MyResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Menu)表控制层
 *
 * @author makejava
 * @since 2020-08-08 23:47:43
 */
@RestController
public class MenuController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private MenuService menuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/menu/{id}")
    public MyResponse selectOne(@PathVariable("id") Long id) {
        return MyResponse.success(this.menuService.queryById(id));
    }

    /**
     * 菜单列表
     */
    @GetMapping("/menus")
    public MyResponse getMenuList(Menu menu) {
        return MyResponse.success(menuService.queryAll());
    }


    /**
     * 新增菜单
     */
    @PostMapping("/menu")
    public MyResponse addMenu(@RequestBody Menu menu) {
        return result(menuService.insert(menu));
    }

    /**
     * 更新
     */
    @PutMapping("/menu")
    public MyResponse update(@RequestBody Menu menu) {
        return result(menuService.update(menu));
    }

    /**
     * 删除
     */
    @DeleteMapping("/menu/{id}")
    public MyResponse delMenu(@PathVariable("id") Long id) {
        Menu menu = new Menu();
        menu.setPid(id);
        List<Menu> menuList = menuService.queryAll(menu);
        if (menuList.size() > 0) {
            return MyResponse.error("存在子菜单，不可删除");
        } else {
            return result(menuService.deleteById(id));
        }
    }




}