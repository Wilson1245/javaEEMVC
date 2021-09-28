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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
//        Connection conn = null;
//        String sql = "insert into company select id, customer_name, customer_phone, customer_email, customer_address, purchase_id, purchase_name, serial_number, shipment_stat FROM dblink('hostaddr=127.0.0.1 port=5432 dbname=jfxrsbdq user=jfxrsbdq password=IwjwxOnT88lCjGHGp5LVdehUoOvl_67a','SELECT id,customer_email, customer_address,customer_name, customer_phone, purchase_id, purchase_name, serial_number, shipment_stat FROM purchase;')As t(id int, customer_phone text, customer_name text, customer_email text, customer_address text,  purchase_id int, purchase_name text, serial_number text, shipment_stat int)where shipment_stat >= 1 on conflict do nothing";
//        try {
//            Statement st = conn.createStatement();
//            st.execute(sql);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
          companyRepository.logURL();
        
    }
    
    public void save (Company company){
        companyRepository.save(company);
    }
}
