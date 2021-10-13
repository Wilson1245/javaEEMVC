/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.logisticcompanies.repositories.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.logisticcompanies.pojo.primary.Company;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Administrator
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{
    
}
