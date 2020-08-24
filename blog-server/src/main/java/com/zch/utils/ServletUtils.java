package com.zch.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletUtils {

    /**
     * 获取请求参数
     * */
   public  static Integer getParameterToInt(String name){
      String value= getHttpServletRequest().getParameter(name);
       return  Integer.valueOf(value);
   }


   /**
    * 获取request 对象
    * */
   private  static HttpServletRequest getHttpServletRequest(){
      HttpServletRequest request=  getServletRequestAttributes().getRequest();
      return  request;
   }

   /**
    * 通过request上下文获取ServletRequestAttributes
    * */

   private static ServletRequestAttributes getServletRequestAttributes(){
       ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
       return  servletRequestAttributes;
   }

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string 待渲染的字符串
     * @return null
     */
  public  static  String  renderString(HttpServletResponse response,String string){
     try {
         response.setStatus(200);
         response.setContentType("application/json");
         response.setCharacterEncoding("utf-8");
         response.getWriter().write(string);
     }catch (IOException e){
         e.printStackTrace();
     }
      return  null;
  }

}
