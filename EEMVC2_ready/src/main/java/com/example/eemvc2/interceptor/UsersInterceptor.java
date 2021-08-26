/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eemvc2.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author User
 */
public class UsersInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //檢查請求的用戶端是否已完成驗證
        if(request.getSession().getAttribute("users")==null){
            //若失則轉到登入
            response.sendRedirect("/centent/");
            return false;
        }
        //若成表示可以繼續執行
        return true;        
    }
    
}
