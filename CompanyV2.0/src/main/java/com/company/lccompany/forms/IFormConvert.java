/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.lccompany.forms;

/**
 *
 * @author User
 */
public interface IFormConvert<S, T> {
       T convertFor(S s);
}
