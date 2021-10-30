/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company2.Company_v2.customer.controller;

import com.company2.Company_v2.customer.data.CustomerModel;
import com.company2.Company_v2.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author wuweicheng
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
public class CustomerController {
    
    @Autowired
    CustomerService customerService;
    
    @GetMapping("/create")
    public CustomerModel createCustomer(){
        CustomerModel cm = customerService.create();
        return cm;
    }
}
