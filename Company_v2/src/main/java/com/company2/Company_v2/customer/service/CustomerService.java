/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company2.Company_v2.customer.service;

import com.company2.Company_v2.customer.data.CustomerModel;
import com.company2.Company_v2.customer.repo.CustomerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author wuweicheng
 */
@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    
    public CustomerModel create(){
        CustomerModel cm = new CustomerModel("wilson052864@gmail", "John", "Doe");
        return cm;
    }
    
    public void save(CustomerModel cm){
        customerRepository.save(cm);
    }
    
    public List<CustomerModel> findAll(){
        return customerRepository.findAll(Sort.by("id"));
    }
    
    public Optional<CustomerModel> findbyId(int id){
        return customerRepository.findById(id);
    }
}
