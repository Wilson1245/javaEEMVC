/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eemvc2.forms;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 *
 * @author wuweicheng
 */
@Data
public class SearchForm {
    
    @NotBlank
    private String phonenumber;
    
    @NotBlank
    private String email;
}
