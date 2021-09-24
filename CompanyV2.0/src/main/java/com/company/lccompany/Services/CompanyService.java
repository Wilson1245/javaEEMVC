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
import org.springframework.stereotype.Service;
import com.company.lccompany.Model.ConnectionDataBase;
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
        List<Company> purchase = connectionDataBase();
        for (Company company : purchase){
            company.setCustomerAddress(purchase.get(1).toString());
            company.setCustomerEmail(purchase.get(2).toString());
            company.setCustomerName(purchase.get(3).toString());
            company.setCustomerPhone(purchase.get(4).toString());
            company.setPurchaseId(Integer.parseInt(purchase.get(5).toString()));
            company.setPurchaseName(purchase.get(6).toString());
            company.setSerialNumber(purchase.get(7).toString());
            company.setShipmentStat(Integer.parseInt(purchase.get(8).toString()));
            if(companyRepository.findByserialNumber(company.getSerialNumber()).isEmpty()){
                save(company);
            }
        }
    }
    
    public void save (Company company){
        companyRepository.save(company);
    }
    
    public List<Company> connectionDataBase(){
       return new ConnectionDataBase().connectionDataSource();
    }
}
