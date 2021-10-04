/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.lccompany.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.lccompany.pojo.Shipment_Stat;
import java.util.List;
/**
 *
 * @author wuweicheng
 */
public interface Shipment_StatRepository extends JpaRepository<Shipment_Stat, Integer>{  
    List<Shipment_Stat> findByStatId(int statId);
    List<Shipment_Stat> findByStatMean(String statMean);
}
