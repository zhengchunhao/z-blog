package com.zch.blog.controller;

import com.zch.blog.entity.Blog;
import com.zch.blog.service.BlogService;
import com.zch.systerm.controller.BaseController;
import com.zch.systerm.entity.Image;
import com.zch.systerm.service.ImageService;
import com.zch.utils.IoUtil;
import com.zch.utils.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Blog)表控制层
 *
 * @author makejava
 * @since 2020-08-20 10:38:05
 */
@RestController

public class BlogController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private BlogService blogService;
    @Autowired
    private ImageService imageService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/blog/{blogId}")
    public Blog selectOne(@PathVariable("blogId") Long id) {
        return this.blogService.queryById(id);
    }

    /**
     *删除博客中的图片
     */
    @DeleteMapping("/deleteImg/{imgId}")
    public MyResponse deleteImg(@PathVariable("imgId") Long imgId){
        Image image=imageService.queryById(imgId);
        IoUtil.deleteFile(image.getPath());
        return result(imageService.deleteById(imgId));
    }

    /**
     * 上传博客
     * */
    @PostMapping("/blog")
    public MyResponse addBlof(@RequestBody Blog blog){
     blogService.insert(blog);
     return  MyResponse.success(blogService.queryById(blog.getBlogId()));
    }

    /**
     * 更新
     * */
    @PutMapping("/blog")
    public  MyResponse update(@RequestBody Blog blog){
      int result= blogService.update(blog);
      if(result>0){
          return  MyResponse.success(blogService.queryById(blog.getBlogId()));
      }
      return  MyResponse.error("服务器错误");
    }

}