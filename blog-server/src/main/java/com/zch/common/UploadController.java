package com.zch.common;

import com.sun.corba.se.impl.encoding.BufferManagerReadStream;
import com.zch.systerm.controller.BaseController;
import com.zch.systerm.dao.ImageDao;
import com.zch.systerm.entity.Image;
import com.zch.systerm.service.ImageService;
import com.zch.utils.Base64;
import com.zch.utils.IoUtil;
import com.zch.utils.MyResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.UUID;

@RestController
@RequestMapping("/commom")
public class UploadController extends BaseController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageDao imageDao;
    @Value("${upload.path}")
    private  String filePath;

     /**
      * 图片上传返回imgId
      * */
    @PostMapping("/upload")
  public MyResponse upload( @RequestParam("file") MultipartFile file){
        try {
            if(file.isEmpty()){
         return MyResponse.error("文件为空");
       }
       String uuid= UUID.randomUUID().toString();
       String fileName= file.getOriginalFilename();
       String suffixName=fileName.substring(fileName.lastIndexOf('.'));
       String lastPath=filePath+uuid+fileName;
        File dest = new File(lastPath);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
            file.transferTo(dest);
            Image image=new Image();
            image.setPath(lastPath);
            image.setUuid(uuid);
            image.setType(suffixName);
            image.setImageName(fileName);
            imageService.insert(image);
            return MyResponse.success(image.getId());
        }catch (IllegalStateException e){
           e.printStackTrace();
           return MyResponse.error("上传失败");
        }catch (IOException e){
            e.printStackTrace();
            return  MyResponse.error("上传失败");
        }

   }

   //上传图片  返回base64格式
  @PostMapping("/uploadPath")
  public  MyResponse uploadPath(@RequestParam("file") MultipartFile file){
        try {
            if(file.isEmpty()){
                return MyResponse.error("文件为空");
            }
          String uuid= UUID.randomUUID().toString();
          String fileName=file.getOriginalFilename();
          String lastPath=filePath+uuid+fileName;
          File dest=new File(lastPath);
          if(!dest.getParentFile().exists()){
              dest.getParentFile().mkdir();
          }
            file.transferTo(dest);
            return MyResponse.success(lastPath);
        }catch (IOException e){
            e.printStackTrace();
            return MyResponse.error("上传失败");
        }
  }


   /**
    * 图片下载
    * */
   @GetMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response){
     Long imgId=Long.valueOf(request.getParameter("imgId"));
     Image image=imageDao.queryById(imgId);
     String imgPath=image.getPath();
      response.setContentType("image/"+image.getType().replace(".",""));

      try {
          File file=new File(imgPath);
          OutputStream outputStream=response.getOutputStream();
          BufferedInputStream inputStream=new BufferedInputStream(new FileInputStream(file));
          byte by[]=new  byte[1024];
          int len=0;
          while ((len=inputStream.read(by)) != -1){
              outputStream.write(by);
          }
          inputStream.close();
          outputStream.close();;
      }catch (IOException e){
         e.printStackTrace();
      }

   }

}
