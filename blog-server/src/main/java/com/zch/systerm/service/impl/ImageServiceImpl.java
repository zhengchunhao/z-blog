package com.zch.systerm.service.impl;

import com.zch.common.UploadController;
import com.zch.systerm.dao.ImageDao;
import com.zch.systerm.entity.Image;
import com.zch.systerm.service.ImageService;
import com.zch.utils.IoUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Image)表服务实现类
 *
 * @author makejava
 * @since 2020-08-21 08:34:55
 */
@Service("imageService")
public class ImageServiceImpl implements ImageService {
    @Resource
    private ImageDao imageDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Image queryById(Long id) {
        Image image= this.imageDao.queryById(id);
        image.setPath(IoUtil.imageEncode(image.getPath()));
        return  image;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Image> queryAllByLimit(int offset, int limit) {
        return this.imageDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param image 实例对象
     * @return 实例对象
     */
    @Override
    public Image insert(Image image) {
        this.imageDao.insert(image);
        return image;
    }

    /**
     * 修改数据
     *
     * @param image 实例对象
     * @return 实例对象
     */
    @Override
    public Image update(Image image) {
        this.imageDao.update(image);
        return this.queryById(image.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Long id) {
        Image image=imageDao.queryById(id);
        IoUtil.deleteFile(image.getPath());
        return this.imageDao.deleteById(id) ;
    }
}