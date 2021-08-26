/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eemvc2.controllers;

import com.example.eemvc2.pojo.Product;
import com.example.eemvc2.repositories.ProductRepository;
import com.example.eemvc2.services.ProductService;
import com.example.eemvc2.services.ProductService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */

@RestController
@RequestMapping("/productapp")
public class ProductApp {

    @Autowired
    private ProductService productService;
    //public ProductRepository productRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    //@PostMapping("/add") 
    public void insertProduct(String name, String brand, String madein, int price, int qty) {
        Product product = new Product();
        product.setName(name);
        product.setBrand(brand);
        product.setMadein(madein);
        product.setPrice(price);
        product.setQty(qty);
        productService.insert(product);
    }

    @GetMapping("/")
    public Page<Product> findAll(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "3", required = false) int size) {        
        //return productService.findAll();
        return productService.findAllByPage(page, size);
    }

    @GetMapping("/{id}")
    //public Optional<Product> findById(@PathVariable(name = "pid") int id) {
    public Optional<Product> findById(@PathVariable int id) {
        return productService.findById(id);
    }

    @GetMapping("/query1/{brand}")
    public List<Product> findById(@PathVariable String brand) {
        return productService.findByBrandContaining(brand);
    }

}
