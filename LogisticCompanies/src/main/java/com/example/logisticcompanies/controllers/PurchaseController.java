/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.logisticcompanies.controllers;

import com.example.logisticcompanies.services.secondary.PurchaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.logisticcompanies.pojo.secondary.Purchase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @GetMapping("/")
    public String findAllPurchase(Model model) {
        List<Purchase> purchases = purchaseService.findAll();
        model.addAttribute("purchase", purchases);
        return "Allpurchase";
    }
}
