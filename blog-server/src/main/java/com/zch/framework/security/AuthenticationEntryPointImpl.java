package com.zch.framework.security;

import com.alibaba.fastjson.JSON;
import com.zch.utils.MyResponse;
import com.zch.utils.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 *  认证失败
 * */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint , Serializable {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
      int code=401;
      String msg = "认证失败，无法访问资源"+httpServletRequest.getRequestURI();
        ServletUtils.renderString(httpServletResponse, JSON.toJSONString(new MyResponse(code,msg,null)));
    }
}
