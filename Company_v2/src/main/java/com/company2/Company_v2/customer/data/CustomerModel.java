/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company2.Company_v2.customer.data;

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
public class CustomerModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(unique = true, nullable = false)
    private String email;
    private String firstName;
    private String lastName;

    protected CustomerModel() {}

    public CustomerModel(String email, String firstName, String lastName) {
     this.email = email;
     this.firstName = firstName;
     this.lastName = lastName;
    }
}
