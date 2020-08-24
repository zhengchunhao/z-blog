package com.zch.framework.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthJwtTokenFilter authJwtTokenFilter;
    @Autowired
     @Qualifier(value = "SecurityUserDetailsServiceImpl")
     UserDetailsService userDetailsService;

    @Autowired
    AuthenticationEntryPointImpl authenticationEntryPoint;
    /**
     * SpringSecurity提供有一些基本的页面(如:login、logout等)；如果觉得它提供的
     * 基础页面难看，想使用自己的页面的话，可以在此方法里面进行相关配置。
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .csrf().disable()
               .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
               .authorizeRequests()
               .antMatchers("/login", "/register","/Code","/commom/download").permitAll()
               .anyRequest().authenticated()
               .and()
               .addFilterBefore(authJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
