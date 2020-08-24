package com.zch.framework.security;

import com.zch.utils.Constant;
import com.zch.utils.MyResponse;
import com.zch.utils.RedisCache;
import com.zch.utils.VerificationCodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LoginSevice {
@Resource
    AuthenticationManager authenticationManager;
@Autowired
ToeknService toeknService;
@Autowired
private RedisCache redisCache;
  public MyResponse login(String userName, String password, String uuid, String code){
      String codeKey= Constant.VERFEY_CODE_KEY+uuid;
      String verificationCode=redisCache.getCacheObject(codeKey);

      if(StringUtils.isEmpty(verificationCode)){
          return  MyResponse.error("验证码已过期");
      }
      redisCache.deleteObject(verificationCode);

      if(!verificationCode.toLowerCase().equals(code.toLowerCase())){
          return MyResponse.error("验证码错误");
      }

      Authentication authentication=null;
      try {
          authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));
      }catch (Exception e){
         if(e instanceof BadCredentialsException){
             return  MyResponse.error("用户名或密码错误");
         }else {
            e.printStackTrace();
         }
      }
      LoginUser loginUser=(LoginUser) authentication.getPrincipal();
      return  MyResponse.success(toeknService.createToken(loginUser));
  }

}
