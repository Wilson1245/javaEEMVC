/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eemvc2.services;

import com.example.eemvc2.repositories.ProductRepository;
import com.example.eemvc2.pojo.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void insert(Product p) {
        productRepository.save(p);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }
    
    public Product findByName(String purchase){
        return productRepository.findByName(purchase);
    }

    public List<Product> findByBrandContaining(String brand) {
        return productRepository.findByBrandContaining(brand);
    }

    /**
     * 提供依產品編號刪除
     *
     * @param id 產品編號
     * @return 刪除成功回傳true, 反之則回傳false
     */
    public boolean delete(int id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception ex) {

        }
        return false;
    }

    /**
     * 提供分頁功能
     *
     * @param page 第幾頁
     * @param size 每頁幾筆
     * @return 回傳分頁後的記錄
     */
    public Page<Product> findAllByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        return productRepository.findAll(pageable);
    }

    /**
     * 提供分頁功能
     *
     * @param pageable 分頁物件
     * @return 回傳分頁後的記錄
     */
    public Page<Product> findAllByPage(Pageable pageable) {
//        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));

        //此處的加工是為了改善當頁次(page)大於總頁次時，顯示最後一頁，而不顯示空白頁
        Page<Product> pp = productRepository.findAll(pageable);
        int totalPage = pp.getTotalPages();
        if(pageable.getPageNumber()>=totalPage){
            pageable = PageRequest.of(totalPage-1, pageable.getPageSize(), pageable.getSort());
            pp = productRepository.findAll(pageable);
        }
        return pp;
    }
    
//    public Object readImage(String pathURL){
//        String filename = pathURL;
//        BufferedImage image;
//        try{
//            image = ImageIO.read(new File(filename));
//        }catch(Exception e){
//            e.printStackTrace();
//            image = null;
//            return image;
//        }
//        return image;
//    }
}
    

