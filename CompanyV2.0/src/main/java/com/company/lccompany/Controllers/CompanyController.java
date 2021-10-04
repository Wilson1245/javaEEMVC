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
import org.springframework.web.bind.annotation.PathVariable;
import com.company.lccompany.pojo.Shipment_Stat;
import com.company.lccompany.Services.Shipment_StatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    
    @Autowired
    Shipment_StatService statService;
    
    @GetMapping("/show")
    public String ShowAll (Model model){
        List<Company> purchase = companyService.ShowAllpurchase();
        model.addAttribute("purchase", purchase);
        return "ShowPurchase";
    }
    
    @GetMapping("/insertStat")
    public String insertStat(Model model){
        List<Shipment_Stat> shipment_Stats = statService.findAll();
        model.addAttribute("shipment_Stats", shipment_Stats);
        return "InsertStat";
    }
    
//    @PostMapping("/saveStat")
//    public String saveStat(ShipmantStatForm shipmantStatForm, Model model){
//        int stat_id = shipmantStatForm.getStat_id();
//        String stat_mean = shipmantStatForm.getStat_mean();
//        System.out.println("shipmantStatForm--->>>" + shipmantStatForm);
//        if(statService.findStat_id(stat_id).isEmpty() || statService.findStat_mean(stat_mean).isEmpty()){
//                Shipment_Stat shipment_Stat = shipmantStatForm.convertToShipment_Stat();
//                statService.save(shipment_Stat);
//        }
//        return "redirect:/insertStat";
//    }
    
    @GetMapping("/edit/{id}")
    public String editShipStat(@PathVariable int id, Model model){
        Company company = companyService.findById(id).get();
        List<Shipment_Stat> list = statService.findAll();
        model.addAttribute("StatList", list);
        model.addAttribute("purchase", company);
        return "EditShipmentStat";
    }
    
    @PostMapping("/editInsert")
    public String editInsert(Company company, RedirectAttributes attributes){
        System.out.println("Company---->>>>>>" + company);
        companyService.save(company);
        return "redirect:/show";
    }
}
