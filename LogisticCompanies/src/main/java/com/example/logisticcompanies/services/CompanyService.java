/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.logisticcompanies.services;

import com.example.logisticcompanies.repositories.CompanyRepository;
import com.example.logisticcompanies.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class CompanyService {
    @Autowired
    PurchaseRepository purchaseRepository;
    
    @Autowired
    CompanyRepository companyRepository;
}
