package com.zch.systerm.entity;

import java.io.Serializable;

/**
 * (Image)实体类
 *
 * @author makejava
 * @since 2020-08-21 12:56:06
 */
public class Image implements Serializable {
    private static final long serialVersionUID = 521043891337565373L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 图片路径
     */
    private String path;
    /**
     * uuid
     */
    private String uuid;
    /**
     * 图片格式
     */
    private String type;
    /**
     * 图片名称
     */
    private String imageName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

}