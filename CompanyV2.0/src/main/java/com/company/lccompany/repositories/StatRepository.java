/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.lccompany.repositories;

import com.company.lccompany.pojo.Shipment_Stat;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Administrator
 */
public interface StatRepository extends JpaRepository<Long, Shipment_Stat> {
}