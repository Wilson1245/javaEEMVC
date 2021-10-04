/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.lccompany.Services;

import com.company.lccompany.repositories.CompanyRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.company.lccompany.pojo.Company;
import java.util.Optional;
import org.springframework.stereotype.Service;
/**
 *
 * @author Administrator
 */
@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    
    public List<Company> ShowAllpurchase (){
        updateCompany();
        return companyRepository.findAll();
    }
    
    public void updateCompany(){
          companyRepository.logURL();
    }
    
    public void save (Company company){
        companyRepository.save(company);
    }
    
    public Optional<Company> findById(int id){
        return companyRepository.findById(id);
    }
}
