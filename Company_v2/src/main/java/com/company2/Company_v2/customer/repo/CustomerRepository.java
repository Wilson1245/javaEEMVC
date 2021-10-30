/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company2.Company_v2.customer.repo;

import com.company2.Company_v2.customer.data.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author wuweicheng
 */
@Repository
    public interface CustomerRepository extends JpaRepository < CustomerModel, Integer > {
}
