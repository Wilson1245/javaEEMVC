/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.lccompany.Controllers;

import com.company.lccompany.Services.CompanyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.company.lccompany.pojo.Company;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    
    @GetMapping("/show")
    public String ShowAll (Model model){
        List<Company> purchase = companyService.ShowAllpurchase();
        model.addAttribute("purchase", purchase);
        return "ShowPurchase";
    }
}
