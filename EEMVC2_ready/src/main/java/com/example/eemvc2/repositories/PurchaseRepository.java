/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eemvc2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.eemvc2.pojo.Purchase;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author wuweicheng
 */
public interface PurchaseRepository extends JpaRepository<Purchase, Integer>{
        
    List<Purchase> findByCustomerPhoneAndCustomerEmail(String phonenumber, String email);
    
    @Modifying
    @Query(value = "update purchase t set shipment_stat=i.shipment_stat from dblink('hostaddr=127.0.0.1 port=5432 dbname=xljpnqcy user=xljpnqcy password=VIVvCXYfzRt5vhkrZAK2kTNZ9y4y8Mz7', 'select id, shipment_stat from company')as i(id int, shipment_stat int) where t.id=i.id;", nativeQuery = true)
    @Transactional
    void logURL();
}
