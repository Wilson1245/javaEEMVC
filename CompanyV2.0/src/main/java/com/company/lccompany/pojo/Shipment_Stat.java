/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.lccompany.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author Administrator
 */
@Data
@Entity
public class Shipment_Stat {
    
    @Id
    public int stat_id;
    
    public String stat_mean;
}
