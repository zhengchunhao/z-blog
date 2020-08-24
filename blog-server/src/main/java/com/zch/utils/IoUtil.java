package com.zch.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * IO工具类
 * */
public class IoUtil {
    /**
     *  下载图片并转成base64
     */
    public static String imageEncode(String path) {
        File file;
        if (path.equals("") || StringUtils.isEmpty(path)) {
            file = new File("D://blog/default.jpeg");
        } else {
            file = new File(path);
        }
        try {
            byte[] fileByte = Files.readAllBytes(file.toPath());
            return Base64.encode(fileByte);
        } catch (IOException e) {
            e.printStackTrace();
            return "头像转换失败";
        }

    }

    /**
     * 删除文件
     * */
   public static boolean deleteFile(String path){
      try {
          File file=new File(path);
          return file.delete();
      }catch (Exception e){
          e.printStackTrace();
          return  false;
      }
   }

}
