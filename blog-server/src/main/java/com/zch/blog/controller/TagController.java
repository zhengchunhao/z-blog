package com.zch.blog.controller;

import com.zch.blog.entity.Tag;
import com.zch.blog.service.TagService;
import com.zch.systerm.controller.BaseController;
import com.zch.utils.MyResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Tag)表控制层
 *
 * @author makejava
 * @since 2020-08-20 10:36:25
 */
@RestController
public class TagController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private TagService tagService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/tag/{tagId}")
    public MyResponse selectOne(@PathVariable("tagId") Integer id) {
        return MyResponse.success(tagService.queryById(id));
    }

    @GetMapping("/tags")
    public MyResponse tagList(Tag tag){
        return MyResponse.success( tagService.queryAll(tag));
    }

    @PostMapping("/tag")
    public MyResponse add(@RequestBody Tag tag){
        return  result(tagService.insert(tag));
    }

    @PutMapping("/tag")
    public  MyResponse update(@RequestBody Tag tag){
        return  result(tagService.update(tag));
    }

    @DeleteMapping("/tag/{tagId}")
    public  MyResponse deleteById(@PathVariable("tagId") Integer tagId){
       if(tagService.deleteById(tagId)){
           return  MyResponse.success();
       } else {
           return  MyResponse.error();
       }
    }



}