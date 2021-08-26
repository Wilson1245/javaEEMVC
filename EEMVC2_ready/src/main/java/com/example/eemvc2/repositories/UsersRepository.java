/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eemvc2.repositories;

import com.example.eemvc2.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface UsersRepository extends JpaRepository<Users, Integer> {

    /**
     * 提供依帳號及密碼查詢Users
     *
     * @param name 帳號
     * @param password 密碼
     * @return 傳回Users物件
     */
    Users findByNameAndPassword(String name, String password);
}
