/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.lccompany.Services;

import com.company.lccompany.repositories.Shipment_StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.lccompany.pojo.Shipment_Stat;
import java.util.List;

/**
 *
 * @author wuweicheng
 */
@Service
public class Shipment_StatService {
    
    @Autowired
    Shipment_StatRepository statRepository;
    
    public void save (Shipment_Stat shipment_Stat){
        statRepository.save(shipment_Stat);
    }
    
    public List<Shipment_Stat> findAll(){
        return statRepository.findAll();
    }
    
    public List<Shipment_Stat> findStat_id(int stat_id){
        return statRepository.findByStatId(stat_id);
    }
    
    public List<Shipment_Stat> findStat_mean(String stat_mean){
        return statRepository.findByStatMean(stat_mean);
    }
}
