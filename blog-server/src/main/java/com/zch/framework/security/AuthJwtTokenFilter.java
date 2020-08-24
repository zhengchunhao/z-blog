package com.zch.framework.security;

import com.zch.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthJwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    ToeknService toeknService;
    @Autowired
    SecurityUserDetailsServiceImpl securityUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        LoginUser loginUser=toeknService.getLoginUser(httpServletRequest);
        if(loginUser!=null&& SecurityUtils.getAuthentication()==null){
         toeknService.verifyToken(loginUser);
         UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
         usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
         SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
