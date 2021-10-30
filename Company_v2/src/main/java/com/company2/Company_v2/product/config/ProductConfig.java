/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company2.Company_v2.product.config;

import com.company2.Company_v2.customer.data.CustomerModel;
import com.company2.Company_v2.customer.repo.CustomerRepository;
import com.company2.Company_v2.product.data.ProductModel;
import com.company2.Company_v2.product.repo.ProductRepository;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author wuweicheng
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
 entityManagerFactoryRef = "productEntityManagerFactory",
 transactionManagerRef = "productTransactionManager",
    basePackageClasses = {
            ProductRepository.class
    }
)
public class ProductConfig {

    @Bean(name = "productDataSource")
    @ConfigurationProperties(prefix = "db2.datasource")
    public DataSource dataSource() {
     return DataSourceBuilder.create().build();
    }

    @Bean(name = "productEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    barEntityManagerFactory(
     EntityManagerFactoryBuilder builder,
     @Qualifier("productDataSource") DataSource dataSource
    ) {
     return builder
      .dataSource(dataSource)
      .packages(ProductModel.class)
      .persistenceUnit("db2")
      .build();
    }

    @Bean(name = "productTransactionManager")
    public PlatformTransactionManager productTransactionManager(
     @Qualifier("productEntityManagerFactory") EntityManagerFactory productEntityManagerFactory
    ) {
     return new JpaTransactionManager(productEntityManagerFactory);
    }
}
