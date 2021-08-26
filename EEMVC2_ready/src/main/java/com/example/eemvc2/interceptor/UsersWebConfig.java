/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eemvc2.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author User
 */
@Configuration
public class UsersWebConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry); //To change body of generated methods, choose Tools | Templates.
        //註冊欄截器
        
        registry.addInterceptor(new UsersInterceptor())
                //套用的URL
                //.addPathPatterns("/product/**")  //從product(含)所有的動作都套用
                //.addPathPatterns("/product/*/**")  //從product下的子動作才套用
                
                .addPathPatterns("/**")
                
                //排除的URL
                .excludePathPatterns("/user/login",
                        "/user/register",
                        "/user/registerSave",
                        "/centent/**",
                        "/paypal/**",
                        //---------------
                        "/css/**",
                        "/fonts/**",
                        "/js/**",
                        "/image/**");
    }
    
}
