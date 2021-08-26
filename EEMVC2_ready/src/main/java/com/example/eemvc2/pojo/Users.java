/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eemvc2.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;

/**
 *
 * @author User
 */
@Data
@Entity
//@Table(name = "Users")
public class Users{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 20, unique = true, nullable = false)
    private String name;
    //@Column(length = 20,nullable = false)
    @Column(length = 255)
    @ColumnTransformer(
            read = "pgp_sym_decrypt(password::bytea,'mySecretKey')",
            write = "pgp_sym_encrypt(?, 'mySecretKey')")
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
}