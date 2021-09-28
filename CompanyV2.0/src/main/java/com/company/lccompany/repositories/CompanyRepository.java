/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.lccompany.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.lccompany.pojo.Company;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Administrator
 */
public interface CompanyRepository extends JpaRepository<Company, Integer>{
    public List<Company> findByserialNumber(String serialNumber);
    
    @Modifying
    @Query(value = "insert into company select id, customer_name, customer_phone, customer_email, customer_address, purchase_id, purchase_name, serial_number, shipment_stat FROM dblink('hostaddr=127.0.0.1 port=5432 dbname=jfxrsbdq user=jfxrsbdq password=IwjwxOnT88lCjGHGp5LVdehUoOvl_67a','SELECT id,customer_email, customer_address,customer_name, customer_phone, purchase_id, purchase_name, serial_number, shipment_stat FROM purchase;')As t(id int, customer_phone text, customer_name text, customer_email text, customer_address text,  purchase_id int, purchase_name text, serial_number text, shipment_stat int)where shipment_stat >= 1 on conflict do nothing", nativeQuery = true)
    @Transactional
    void logURL();
}
