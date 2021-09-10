/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eemvc2.controllers;

import com.example.eemvc2.pojo.Product;
import com.example.eemvc2.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.eemvc2.forms.SearchForm;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.eemvc2.pojo.Purchase;
import com.example.eemvc2.repositories.PurchaseRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author wuweicheng
 */
@Controller
@RequestMapping("/centent")
public class CententController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private PurchaseRepository purchaseRepository;
    
    @GetMapping("/{id}")
    public String findById(@PathVariable int id, Model m){
        Product p = productService.findById(id).get();
        System.out.println("product v3:" + p);
        m.addAttribute("product", p);
        return "Product";
    }
    
    @GetMapping("/")
//    public String findAll(@RequestParam(defaultValue = "0", required = false) int page,
//            @RequestParam(defaultValue = "5", required = false) int size, Model m) {
    public String findAll(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
            Model m) {
//        Page<Product> lps = productService.findAllByPage(page, size);
        Page<Product> lps = productService.findAllByPage(pageable);

//        for (Product p : lps) {
//            System.out.println(p);
//        }
//
//        Iterator<Product> ir = lps.iterator();
//        while (ir.hasNext()) {
//            System.out.println(ir.next());
//        }
        m.addAttribute("centent_products", lps);
        return "centent";
    }
    
//    @GetMapping("/")
//    public String findAll(Model m){
//        List<Product> p = productService.findAll();
//        m.addAttribute("centent_products", p);
//        return "centent";
//    }
    
    @GetMapping("/shopping/{id}")
    public String shoppingPage(@PathVariable int id, Model m){
        Product p = productService.findById(id).get();
        m.addAttribute("shopping_product", p);
        return "shopping_Product";
    }
    
    @GetMapping("/searchyourbuy")
    public String search(Model model){
        model.addAttribute("searchForm", new SearchForm());
        return "SearchYourBuy";
    }
    
    @PostMapping("/searchnew")
    public String searchnew(String customerPhone, String customerEmail, Model model, RedirectAttributes attributes){
//        List<Purchase> p = purchaseService.findAll();
////        System.out.println("-------All--->>>" + p);
////        System.out.println("---.get(0)--->>>>" + p.get(0));
////        System.out.println("---.size()--->>>>" + p.size());
//        List<Purchase> pSave = new ArrayList<>();
//        boolean pAdd = false;
////        int pSize = p.size();
////        while(pSize-- != 0){
////            System.out.println("----for()--->>>>" + p.get(pSize));
////        }
//        
//        for(int i = 0; i < p.size(); i++){
//            System.out.println("----for()--->>>>" + p.get(i));
//            boolean booPhonenumber = p.get(i).getPhonenumber().equals(phonenumber);
//            boolean booEmail = p.get(i).getEmail().equals(email);
//            
//            if(booPhonenumber == true && booEmail == true){
//               System.out.println("booPhonenumber : " + booPhonenumber);
//               System.out.println("booEmail : " + booEmail);
//               pAdd = pSave.add(p.get(i));
//            }
//            
//        }
//        
//        //--------Found Purchase-------
//        if(pAdd){
//            System.out.println("----->>>" + pSave);
////            String msg = "Find Purchase";
////            attributes.addFlashAttribute("message", msg);
////            return "redirect:/centent/";
//              model.addAttribute("searchnew", pSave);
//              return "SearchSuccess";
//        }
//        
//        //--------No found Purchase-------
//        String msg = "No Purchase";
//        attributes.addFlashAttribute("message", msg);
//        return "redirect:/centent/";
//    }
    List<Purchase> purchasesList = purchaseRepository.findByCustomerPhoneAndCustomerEmail(customerPhone, customerEmail);
    if(!purchasesList.isEmpty()){
        model.addAttribute("searchnew", purchasesList);
        return "SearchSuccess";
    }
    //--------No found Purchase-------
        String msg = "No Purchase";
        attributes.addFlashAttribute("message", msg);
        return "redirect:/centent/";
    }
}