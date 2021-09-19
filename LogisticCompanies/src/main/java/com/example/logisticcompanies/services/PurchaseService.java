/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.logisticcompanies.services;

import com.example.logisticcompanies.repositories.PurchaseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.logisticcompanies.pojo.Purchase;
/**
 *
 * @author Administrator
 */
@Service
public class PurchaseService {
    
    @Autowired
    private PurchaseRepository purchaseRepository;
    
    public List<Purchase> findAll(){
        return purchaseRepository.findAll();
    }
}
