/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eemvc2.forms;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author User
 */
@Data
public class LoginForm {

    @NotBlank
    @Length(max = 20)
    private String name;
    @NotBlank
    @Length(min = 8, max = 20)
    private String password;
}
