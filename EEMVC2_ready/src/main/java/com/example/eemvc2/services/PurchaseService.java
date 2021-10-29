/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eemvc2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.eemvc2.pojo.Purchase;
import com.example.eemvc2.repositories.PurchaseRepository;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author wuweicheng
 */
@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    
    public void insert(Purchase purchase){
        purchaseRepository.save(purchase);
    }
    
    public void updateData(){
        purchaseRepository.logURL();
    }
    
    public List<Purchase> findAll(){
        return purchaseRepository.findAll();
    }
    
    public List<Purchase> findByPhonenumberAndEmail (String phonenumber, String email){
        return purchaseRepository.findByCustomerPhoneAndCustomerEmail(phonenumber, email);
    }
}
