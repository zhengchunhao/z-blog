package com.zch.systerm.controller;

import com.zch.systerm.entity.Image;
import com.zch.systerm.service.ImageService;
import com.zch.utils.MyResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Image)表控制层
 *
 * @author makejava
 * @since 2020-08-21 08:34:55
 */
@RestController

public class ImageController extends BaseController{
    /**
     * 服务对象
     */
    @Resource
    private ImageService imageService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("image/{id}")
    public Image selectOne(@PathVariable("id") Long id) {
        return this.imageService.queryById(id);
    }

    @DeleteMapping("/image/{imgId}")
    public MyResponse delete (@PathVariable("imgId") Long imgId){
       return result(imageService.deleteById(imgId)) ;
    }

}