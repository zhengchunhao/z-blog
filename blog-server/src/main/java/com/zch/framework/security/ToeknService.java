package com.zch.framework.security;

import com.zch.utils.Constant;
import com.zch.utils.RedisCache;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class ToeknService implements Serializable {

    @Value("${token.secret}")
    private  String secret;
    @Value("${token.header}")
    private  String header;
    @Value("${token.expiration}")
    private int expiration;

    @Autowired
    RedisCache redisCache;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    /**
     * 获取登录用户
     * */
    public  LoginUser getLoginUser(HttpServletRequest request){

     String token=getToken(request);
     if(StringUtils.isNotEmpty(token)){
         Claims claims=parseToken(token);
         String uuid=(String) claims.get(Constant.USER_LOGIN_KEY);
         String userKey=getUserTokenKey(uuid);
         LoginUser loginUser=  redisCache.getCacheObject(userKey);
         return  loginUser;
     }
     return  null;
    }
    /**
     * 获取请求token
     * */
    public  String getToken(HttpServletRequest request){
        String token=request.getHeader(header);
        if(StringUtils.isNotEmpty(token)&&token.startsWith(Constant.TOKEN_PREFIX)){
         token=token.replace(Constant.TOKEN_PREFIX,"");
        }
        return  token;
    }
    /**
     * 数据声明生成令牌
     * */
    private  String createToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }
    /**
     * 创建令牌
     * */
    public  String createToken(LoginUser loginUser){
     String token= UUID.randomUUID().toString();
     loginUser.setToken(token);
     refreshToken(loginUser);
     Map<String,Object> claims=new HashMap<>();
     claims.put(Constant.USER_LOGIN_KEY,token);
     return  createToken(claims);
    }
    /**
     * 验证令牌
     * */
    public void verifyToken(LoginUser loginUser){
        long expireTime=loginUser.getExpireTime();
        long  currentTime=System.currentTimeMillis();
        if(expireTime-currentTime<=20*MILLIS_MINUTE){
            refreshToken(loginUser);
        }
    }
    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 刷新令牌
     * */

    public  void refreshToken(LoginUser loginUser){
     loginUser.setLoginTime(System.currentTimeMillis());
     loginUser.setExpireTime(System.currentTimeMillis()+expiration*MILLIS_MINUTE);
     String userTokenKey=getUserTokenKey(loginUser.getToken());
     //将用户存入reids
     redisCache.setCacheObject(userTokenKey,loginUser,expiration, TimeUnit.MINUTES);
    }

     /**
      * 获取用户tokenKey
      * */
     public  String getUserTokenKey(String uuid){
         return Constant.USER_LOGIN_KEY+uuid;
     }
}
