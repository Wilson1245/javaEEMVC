/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eemvc2.controllers;

import com.example.eemvc2.services.ProductService;
import com.example.eemvc2.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public String findById(@PathVariable int id, Model m) {
//        Product p;
//        try {
//            p = productService.findById(id).get();
//        } catch (NoSuchElementException ex) {
//            p = new Product();
//        }
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
        m.addAttribute("products", lps);
        return "Products";
    }

    /**
     * 提供產品資料添加及異動的頁面
     *
     * @return 回傳輸入頁面
     */
    @GetMapping("/insert")
    public String insertPage(Model m) {
        m.addAttribute("product", new Product());
        return "Product_Entry";
    }

    /**
     * 將產品資料更新至資料庫中(新增及修改)
     *
     * @return
     */
    @PostMapping("/insertSave")
    //public String insertPost(String name, String brand, String madein, int price, int qty, int state){
    public String insertPost(Product p, RedirectAttributes attributes) {//, Model m) {
        productService.insert(p);
        String msg = String.format("「%s」該產品已異動成功!", p.getName());
        //m.addAttribute("message", msg);
        attributes.addFlashAttribute("message", msg);
        return "redirect:/product/";
    }

    /**
     * 提供修改並且轉向至Product_Entry.html
     *
     * @param id 欲查詢的產品編號
     * @param m 用來附加資料並且傳遞到 Product_Entry.html中
     * @return 反回 Product_Entry.html頁面
     */
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable int id, Model m) {
        Product p = productService.findById(id).get();
        m.addAttribute("product", p);
        return "Product_Entry";
    }

    /**
     * 提供依據產品編號刪除的控制動作
     *
     * @param id 欲刪除的產品編號
     * @param attributes 系統內建的flash attribute 物件
     * @return 返一個新請求至 Products.html頁面
     */
    @GetMapping("/del/{id}")
    public String deleteGet(@PathVariable int id, RedirectAttributes attributes) {
        boolean result = productService.delete(id);
        String msg = String.format("「%d」該記錄已刪除!", id);
        if (!result) {
            msg = String.format("「%d」該記錄刪除失敗!", id);
        }
        attributes.addFlashAttribute("message", msg);
        return "redirect:/product/";
    }
}
