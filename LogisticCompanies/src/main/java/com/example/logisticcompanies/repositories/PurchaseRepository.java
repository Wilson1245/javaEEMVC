/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.logisticcompanies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.logisticcompanies.pojo.Purchase;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Administrator
 */
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer>{
    
}
