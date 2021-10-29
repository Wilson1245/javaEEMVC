/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eemvc2.controllers;

import com.example.eemvc2.services.UsersService;
import com.example.eemvc2.pojo.Users;
import com.example.eemvc2.forms.UsersForm;
import com.example.eemvc2.forms.LoginForm;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.eemvc2.pojo.Purchase;
import com.example.eemvc2.repositories.PurchaseRepository;
import com.example.eemvc2.services.PurchaseService;
/**
 *
 * @author User
 */
@Controller
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UsersService usersService;
    
    @Autowired
    private PurchaseService purchaseService;
    
    @GetMapping("/goUsersIndex")
    public String goUsersIndex(){
        return "UsersIndex";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("usersForm", new UsersForm());
        return "Register";
    }

    @GetMapping("/login")
    public String loginView(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "Login";
    }
    
    @PostMapping("/login")
    public String loginPost(@Valid LoginForm loginForm, BindingResult bindingResult, HttpSession session) {
        if(bindingResult.hasErrors()){
            return "Login";
        }
        
        //依帳號密碼尋找資料庫的記錄
        Users users = usersService.findByNameAndPassword(loginForm.getName(), loginForm.getPassword());
        if(users!=null){
            //將依帳密尋找到的用戶加入至session中
            session.setAttribute("users", users);
            return "UsersIndex";
        }
        //在尋找資料庫後發現找不到時
        bindingResult.rejectValue("name", "NoMatch","可能是帳號不正確");
        bindingResult.rejectValue("password", "NoMatch","可能是密碼不正確");        
        return "Login";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session, Model model){
        //清除Session物中有所存放的資料
        session.invalidate();
        model.addAttribute("loginForm", new LoginForm());
        return "Login";
    }

    @PostMapping("/registerSave")
    public String registerPost(@Valid UsersForm usersForm, BindingResult bindingResult) {
        System.out.println(usersForm);
        //Users users = new Users();        
        //users.setName(usersForm.getName());
        //users.setPassword(usersForm.getPassword());
        //...
        //可利用BeanUtils提供複製屬性功能來替代需要單一屬性指派的動作
        //BeanUtils.copyProperties(usersForm, users);        

//        Users users = convertFor(usersForm);
        //檢查密碼與確認是否相符
        if(!usersForm.matchPassword()){
            bindingResult.rejectValue("confirmPassword", "Match", "密碼與確認密碼不相符");
        }
        //處理資料驗證部份
        if (bindingResult.hasErrors()) {
            List<FieldError> lfes = bindingResult.getFieldErrors();
            for(FieldError fe :lfes){
                System.out.printf("%s , %s , %s , %s%n",
                        fe.getField(),
                        fe.getDefaultMessage(),
                        fe.getCode(),
                        fe.getRejectedValue()
                        );
            }
            return "Register";
        }
        Users users = usersForm.convertToUsers();
        usersService.save(users);
        return "redirect:/user/login";
    }

    @GetMapping("/purchaseAll")
    public String findPurchaseAll(Model model){
        List<Purchase> purchasesList = purchaseService.findAll();
        model.addAttribute("searchnew", purchasesList);
        return "UserPurchaseAll";
    }
    
    @GetMapping("/updateDB")
    public String updateDB(){
        purchaseService.updateData();
        return "redirect:/user/purchaseAll";
    }
}
