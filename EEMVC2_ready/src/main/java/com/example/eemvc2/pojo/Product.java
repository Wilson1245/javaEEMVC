/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eemvc2.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

/**
 *
 * @author User
 */
@Data
@Entity
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private int id;
    //@Column(length = 20 ,name = "prod_name")
    private String name;
    private String brand;
    private String madein;
    private int price;
    private int qty;
    private String image;
    /**
     * state 0 未上架, 1 準備中, 2 已上架
     */
    @ColumnDefault(value = "0")
    //@Column(nullable = true)
    private int state;
}
