package com.example.logisticcompanies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.logisticcompanies.repositories.PurchaseRepository","com.example.logisticcompanies.services.PurchaseService"})
public class LogisticCompaniesApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogisticCompaniesApplication.class, args);
	}

}
