/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company2.Company_v2;

import com.company2.Company_v2.product.data.ProductModel;
import com.company2.Company_v2.product.repo.ProductRepository;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wuweicheng
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MultipleDataSourcesProductTests {

    @Autowired
    private ProductRepository productRepository;
    
    @Test
    @Transactional("productTransactionManager")
    public void create_check_product() {
        ProductModel product = new ProductModel("228781","Running Shoes", 20.0);
        product = productRepository.save(product);

        assertNotNull(productRepository.findById(product.getId()));
    }

}

