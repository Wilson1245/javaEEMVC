/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company2.Company_v2.product.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author wuweicheng
 */
@Data
@Entity
public class ProductModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private String code;
    private String name;
    private double price;
    
    protected ProductModel(){}
    
    public ProductModel(String code, String name, double price){
        this.code = code;
        this.name = name;
        this.price = price;
    }
}
