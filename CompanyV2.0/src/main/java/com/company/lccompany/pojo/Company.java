/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.lccompany.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author Administrator
 */
@Data
@Entity
public class Company {
    
    
//    private int companyId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    //流水號
    private String serialNumber;
    
    //貨物名稱
    @Column(columnDefinition = "TEXT", nullable = false)
    private String purchaseName;
    
    //貨物編號
    private int purchaseId;
    
    //出貨狀態
    private int shipmentStat;
    
    //客戶名稱
    @Column(columnDefinition = "TEXT", nullable = false)
    private String customerName;
    
    //客戶電話
    @Column(columnDefinition = "TEXT", nullable = false)
    private String customerPhone;
    
    //客戶地址
    @Column(columnDefinition = "TEXT", nullable = false)
    private String customerAddress;
    
    //客戶信箱
    @Column(columnDefinition = "TEXT", nullable = false)
    private String customerEmail;
}