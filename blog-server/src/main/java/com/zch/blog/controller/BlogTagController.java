package com.zch.blog.controller;

import com.zch.blog.entity.BlogTag;
import com.zch.blog.service.BlogTagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (BlogTag)表控制层
 *
 * @author makejava
 * @since 2020-08-20 10:37:42
 */
@RestController
@RequestMapping("blogTag")
public class BlogTagController {
    /**
     * 服务对象
     */
    @Resource
    private BlogTagService blogTagService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public BlogTag selectOne() {
        return this.blogTagService.queryById();
    }

}