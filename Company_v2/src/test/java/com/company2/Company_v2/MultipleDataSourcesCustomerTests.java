/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company2.Company_v2;

import com.company2.Company_v2.customer.data.CustomerModel;
import com.company2.Company_v2.customer.repo.CustomerRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
//@RunWith(SpringRunner.class) JUnit 4
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MultipleDataSourcesCustomerTests {


    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @Transactional("customerTransactionManager")
    public void create_check_customer() {

        CustomerModel customer = new CustomerModel("user@www.javadevjournal.com","Robert","Hickle");
        customer = customerRepository.save(customer);

        assertNotNull(customerRepository.findById(customer.getId()));
        assertEquals(customerRepository.findById(customer.getId()).get().getEmail() ,"user@www.javadevjournal.com");
    }
}
