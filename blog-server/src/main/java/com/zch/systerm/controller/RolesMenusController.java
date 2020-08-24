package com.zch.systerm.controller;

import com.zch.systerm.entity.RolesMenus;
import com.zch.systerm.service.RolesMenusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (RolesMenus)表控制层
 *
 * @author makejava
 * @since 2020-08-08 23:49:06
 */
@RestController
@RequestMapping("rolesMenus")
public class RolesMenusController {
    /**
     * 服务对象
     */
    @Resource
    private RolesMenusService rolesMenusService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public RolesMenus selectOne(Long id) {
        return this.rolesMenusService.queryById(id);
    }

}