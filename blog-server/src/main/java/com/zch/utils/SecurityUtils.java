package com.zch.utils;

import com.zch.framework.security.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {



    /**
     * 获取当前用户
     * */
    public static LoginUser getLoinUser(){
        Object obj= getAuthentication().getPrincipal();
        return  (LoginUser) obj;
    }
    /***
     *
     * 获取认证信息
     */

    public static Authentication getAuthentication(){

        return SecurityContextHolder.getContext().getAuthentication();
    }
}
