/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eemvc2.controllers;

/**
 *
 * @author wuweicheng
 */
import com.example.eemvc2.services.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.api.payments.Links;
import com.example.eemvc2.pojo.Order;
import com.example.eemvc2.services.ProductService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.eemvc2.pojo.Product;
import com.example.eemvc2.forms.PaypalForm;
import com.example.eemvc2.services.PurchaseService;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import com.example.eemvc2.pojo.Purchase;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/paypal")
public class PaypalController {

	@Autowired
	PaypalService service;

        @Autowired
        ProductService productService;
        
        @Autowired
        PurchaseService purchaseService;
        
	public static final String SUCCESS_URL = "pay/Paypal_success";
	public static final String CANCEL_URL = "pay/Paypal_cancel";

        String purchaseName;
        int purchaseId;
	@GetMapping("/{id}")
	public String home(@PathVariable int id , Model m) {
            Product p = productService.findById(id).get();
            purchaseName = p.getName();
            purchaseId = p.getId();
            m.addAttribute("product_paypal", p);
            return "Paypalment";
	}

	@PostMapping("/pay")
	public String payment(@ModelAttribute("order") Order order){
                    try {
                            Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
                                    order.getIntent(), order.getDescription(), "http://localhost:8080/paypal/" + CANCEL_URL,
                                    "http://localhost:8080/paypal/" + SUCCESS_URL);
                            for(Links link : payment.getLinks()){
                                if(link.getRel().equals("approval_url")) {
                                    return "redirect:"+link.getHref();
                                }
                            }
                        } catch (PayPalRESTException ex) {
                            ex.printStackTrace();
                            }
            return "redirect:/paypal/";
	}
	
	 @GetMapping(value = CANCEL_URL)
	    public String cancelPay() {
	        return "Paypal_cancel";
	    }

	    @GetMapping(value = SUCCESS_URL)
	    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, Model model) {
	        try {
	            Payment payment = service.executePayment(paymentId, payerId);
//	            System.out.println(payment.toJSON());
//                    System.out.println("PaypalID : " + payment.getId());
//                    System.out.println("Paypal Transactions : " + payment.getTransactions());
	            if (payment.getState().equals("approved")) {
                        model.addAttribute("paypalForm", new PaypalForm());
//                        model.addAttribute("purchaseName", purchaseName);
                        System.out.println("Product Name : ----->>>>>" + purchaseName);
                        System.out.println("Product ID : ----->>>>>" + purchaseId);
	                return "Paypal_success";
	            }
	        } catch (PayPalRESTException e) {
	         System.out.println(e.getMessage());
	        }
	        return "redirect:/paypal/";
	    }
            
        @PostMapping("/paypalForm")
        public String gopaypalForm(Purchase purchase, RedirectAttributes attributes){
            purchase.setPurchase(purchaseName);
            purchase.setPurchaseid(purchaseId);
            System.out.println("Name : " + purchase.getName());
            System.out.println("PhoneNumber : " + purchase.getPhonenumber());
            System.out.println("Address : " + purchase.getAddress());
            System.out.println("Email : " + purchase.getEmail());
            System.out.println("Purchase : " + purchase.getPurchase());
            System.out.println("PurchaseId : " + purchase.getPurchaseid());
            purchaseService.insert(purchase);
            String msg = "Buy Now";
            attributes.addFlashAttribute("message", msg);
            return "redirect:/centent/";
        }

}