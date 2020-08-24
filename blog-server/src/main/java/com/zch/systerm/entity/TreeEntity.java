package com.zch.systerm.entity;


import java.io.Serializable;
import java.util.List;

/**
 *转换成前台树形组件的数据
 */
public class TreeEntity implements Serializable {

   private   String title;

   private   Long key;

   private List<TreeEntity> children;

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public Long getKey() {
      return key;
   }

   public void setKey(Long key) {
      this.key = key;
   }

   public List<TreeEntity> getChildren() {
      return children;
   }

   public void setChildren(List<TreeEntity> children) {
      this.children = children;
   }
}
