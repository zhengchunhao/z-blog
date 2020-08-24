package com.zch.utils;

import com.zch.framework.security.LoginUser;

import java.io.Serializable;
import java.util.HashMap;

public class MyResponse  extends HashMap<String,Object>    {

    private static final long serialVersionUID = 1L;


    public static final  String RES_CODE="code";

    public static final String  RES_MSG="msg";

    public static final String RES_DATA="data";


    public  MyResponse(){


    }
   public  MyResponse (int code,String msg,Object data){
      super.put(RES_CODE,code);
      super.put(RES_MSG,msg);
      if(data!=null){
          super.put(RES_DATA,data);
      }

  }

  public static  MyResponse success(){ return  MyResponse.success("操作成功"); }

  public  static  MyResponse success(Object data){
        return  MyResponse.success("操作成功",data);
  }

  public static  MyResponse success(String msg){
        return  MyResponse.success(msg,null);
  }

  public static  MyResponse success(String msg,Object data){
   return  new MyResponse(200,msg,data);
  }

  public  static MyResponse error(String msg,Object data){return  new MyResponse(999,msg,data);}

  public static  MyResponse error(String msg){return   MyResponse.error(msg,null); }

  public static  MyResponse error(Object data){return  MyResponse.error("操作失败",data);}

  public static  MyResponse error(){return  MyResponse.error("操作失败");}

}
