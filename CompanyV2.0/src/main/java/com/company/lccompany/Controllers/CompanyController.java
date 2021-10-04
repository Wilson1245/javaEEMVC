/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.lccompany.Controllers;

import com.company.lccompany.Services.CompanyService;
import com.company.lccompany.forms.ShipmantStatForm;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.company.lccompany.pojo.Company;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.company.lccompany.repositories.StatRepository;

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
    
    @GetMapping("/edit/{id}")
    public String editShipStat(@PathVariable int id, Model model){
        Company company = companyService.findById(id).get();
        List<Integer> stat = companyService.Stat_findAll();
        System.out.println("stat->>>>>>>>>>" + stat);
        model.addAttribute("StatList", stat);
        model.addAttribute("purchase", company);
        model.addAttribute("shipmentStatForm", new ShipmantStatForm());
        return "EditShipmentStat";
    }
    
    @PostMapping("/editInsert")
    public String editInsert(HttpServletRequest request, Company company, RedirectAttributes attributes){
        int shipStat = (int) request.getAttribute("shipStat");
        company.setShipmentStat(shipStat);
        System.out.println("Company---->>>>>>" + company);
        companyService.save(company);
        return "redirect:/show";
    }
}
