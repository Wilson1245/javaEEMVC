/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eemvc2.repositories;

import com.example.eemvc2.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 *
 * @author User
 */
public interface ProductRepository extends JpaRepository<Product, Integer>{
    
    //https://docs.spring.io/spring-data/jpa/docs/2.4.3/reference/html/#jpa.query-methods
    // findBy   屬性名稱  關鍵字
    public List<Product> findByBrandContaining(String brand);
    
    public Product findByName(String purchase);
}
