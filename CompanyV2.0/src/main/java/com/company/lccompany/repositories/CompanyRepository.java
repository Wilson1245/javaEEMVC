/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.lccompany.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.lccompany.pojo.Company;
import java.util.List;
/**
 *
 * @author Administrator
 */
public interface CompanyRepository extends JpaRepository<Company, Integer>{
    public List<Company> findByserialNumber(String serialNumber);
}
