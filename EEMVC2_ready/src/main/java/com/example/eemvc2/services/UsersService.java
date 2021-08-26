/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eemvc2.services;

import com.example.eemvc2.repositories.UsersRepository;
import com.example.eemvc2.pojo.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    
    /**
     * 存檔一個Users物件
     * @param users 欲儲存的Users物件(新增/更新)
     */
    public void save(Users users){
        usersRepository.save(users);
    }
    public Users findByNameAndPassword(String name, String password){
        return usersRepository.findByNameAndPassword(name, password);
    }
}
