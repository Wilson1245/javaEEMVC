/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eemvc2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.eemvc2.pojo.Purchase;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author wuweicheng
 */
public interface PurchaseRepository extends JpaRepository<Purchase, Integer>{
        
    List<Purchase> findByPhonenumberAndEmail(String phonenumber, String email);
}
