/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eemvc2.controllers;

import com.example.eemvc2.Eemvc2Application;
import com.example.eemvc2.services.ProductService;
import com.example.eemvc2.pojo.Product;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
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
     * ??????????????????????????????????????????
     *
     * @return ??????????????????
     */
    @GetMapping("/insert")
    public String insertPage(Model m) {
        m.addAttribute("product", new Product());
        return "Product_Entry";
    }

    /**
     * ????????????????????????????????????(???????????????)
     *
     * @return
     */
//    @Value("${file.upload.path}")
//    private String path;
//    @Value("classpath:/static/image/")
//    private String path;
    @PostMapping("/insertSave")
    //public String insertPost(String name, String brand, String madein, int price, int qty, int state){
    public String insertPost(Product p, RedirectAttributes attributes, @RequestPart MultipartFile newimage) throws IOException {//, Model m) {
//        System.out.println("Before imageFile : " + p.getImage());
//        String filename = p.getImage();
//        p.setImage("/image/" + filename);
//        System.out.println("After imageFile : " + p.getImage());
        String imgName = p.getImage();//???????????????
        String fileName = newimage.getOriginalFilename();

        if (!fileName.isEmpty()) {
            //??????Database????????? OK
            String imagePath = "/image/";
            String newImagePath = imagePath + fileName;
            p.setImage(newImagePath);
            System.out.println("fileName : " + fileName);
            System.out.println("New ImagePath : " + p.getImage());

            //???????????????/static/image/
//            System.out.println("ProductController.GetResources / -------->>>>>> : " + Eemvc2Application.class.getResource("/").getPath());
            StringBuilder MyFilePath = new StringBuilder(Eemvc2Application.class.getResource("/").getPath());
            MyFilePath = MyFilePath.deleteCharAt(0);
//            System.out.println("MyFilePath : " + MyFilePath.toString());
            String MyFilePathV2 = MyFilePath.toString();
            MyFilePathV2 = MyFilePathV2.substring(0, MyFilePathV2.length() - 15) + "src/main/resources/static/image/";
            System.out.println("MyFilePathV2 : " + MyFilePathV2);

            String filePath = MyFilePathV2 + fileName;
            System.out.println("filePath ------------->>>>>>>>>>>" + filePath);
            File dest = new File(filePath);
            System.out.println("Dest -------->>>>> : " + dest);
            
            String imgNamePath = MyFilePathV2 + imgName;//?????????????????????
            System.out.println("imgNamePath : " + imgNamePath);
            File file = new File(imgNamePath);
            Path path = file.toPath();
            Files.deleteIfExists(path);
            
            Files.copy(newimage.getInputStream(), dest.toPath());//?????????image?????????
        }

        //Save
        productService.insert(p);
        String msg = String.format("???%s???????????????????????????!", p.getName());
        //m.addAttribute("message", msg);
        attributes.addFlashAttribute("message", msg);
        return "redirect:/product/";
    }

    /**
     * ???????????????????????????Product_Entry.html
     *
     * @param id ????????????????????????
     * @param m ????????????????????????????????? Product_Entry.html???
     * @return ?????? Product_Entry.html??????
     */
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable int id, Model m) {
        Product p = productService.findById(id).get();
        m.addAttribute("product", p);
        return "Product_Entry";
    }

    /**
     * ?????????????????????????????????????????????
     *
     * @param id ????????????????????????
     * @param attributes ???????????????flash attribute ??????
     * @return ????????????????????? Products.html??????
     */
    @GetMapping("/del/{id}")
    public String deleteGet(@PathVariable int id, RedirectAttributes attributes) {
        boolean result = productService.delete(id);
        String msg = String.format("???%d?????????????????????!", id);
        if (!result) {
            msg = String.format("???%d????????????????????????!", id);
        }
        attributes.addFlashAttribute("message", msg);
        return "redirect:/product/";
    }

//    //Copy
//    public void copyFileUsingFileChannels(File source, File dest) throws IOException {
//        FileChannel inputChannel = null;
//        FileChannel outputChannel = null;
//        try {
//            inputChannel = new FileInputStream(source).getChannel();
//            outputChannel = new FileOutputStream(dest).getChannel();
//            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
//        } finally {
//            inputChannel.close();
//            outputChannel.close();
//        }
//    }
}
