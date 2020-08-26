package com.zch.blog.service.impl;

import com.zch.blog.dao.BlogTagDao;
import com.zch.blog.dao.TagDao;
import com.zch.blog.entity.Tag;
import com.zch.blog.service.TagService;
import com.zch.common.UploadController;
import com.zch.systerm.entity.User;
import com.zch.utils.IoUtil;
import com.zch.utils.MyResponse;
import com.zch.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (Tag)表服务实现类
 *
 * @author makejava
 * @since 2020-08-20 10:36:24
 */
@Service("tagService")
public class TagServiceImpl implements TagService {
    @Resource
    private TagDao tagDao;
    @Autowired
    private BlogTagDao blogTagDao;

    /**
     * 通过ID查询单条数据
     *
     * @param tagId 主键
     * @return 实例对象
     */
    @Override
    public Tag queryById(Integer tagId) {
        return this.tagDao.queryById(tagId);
    }

    /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override
    public List<Tag> queryAll(Tag tag) {
        List<Tag> tagList= this.tagDao.queryAll(tag);
        for (Tag tag1:tagList){
           if(tag1.getImagePath()!=null&&!tag1.getImagePath().equals("")){
               tag1.setImagePath(IoUtil.imageEncode(tag1.getImagePath()));
           }
        }
        return  tagList;
    }

    /**
     * 新增数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Tag tag) {
        User user= SecurityUtils.getLoinUser().getUser();
        tag.setCreateTime(new Date());
        tag.setUserId(user.getId());
        tag.setCreateBy(user.getUsername());
        return this.tagDao.insert(tag);
    }

    /**
     * 修改数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Tag tag) {
        return  this.tagDao.update(tag);
    }

    /**
     * 通过主键删除数据
     *
     * @param tagId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer tagId) {
       if(blogTagDao.selectBlogIdsIntagId(tagId)>0){
           return false;
       }else {
          if(tagDao.deleteById(tagId)>0){
              return  true;
          } else {
              return  false;
          }
       }
    }
}