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
 * @author wuweicheng
 */
@Data
public class PaypalForm {
    
    @NotBlank
    @Length(max = 20)
    private String customerName;
    
    @NotBlank
    private String customerPhone;
    
    @NotBlank
    private String customerAddress;
    
    @NotBlank
    private String customerEmail;
    
}
