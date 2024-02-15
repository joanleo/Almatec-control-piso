package com.almatec.controlpiso.integrapps;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  entityManagerFactoryRef = "integrappsEntityManagerFactory",
  transactionManagerRef = "integrappsTransactionManager",
  basePackages = {"com.almatec.controlpiso.integrapps.repositories","com.almatec.controlpiso.security.repositories"}
)
public class IntegrappsJpaConfiguration {
	
	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String dialect;
	
	@Value("${spring.jpa.properties.integrapps.hibernate.ddl-auto}")
    private String ddlAuto;

	@Bean(name = "integrappsEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean integrappsEntityManagerFactory(
    		@Qualifier("integrapps") DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
    	factoryBean.setDataSource(dataSource);
    	factoryBean.setPackagesToScan("com.almatec.controlpiso.integrapps.entities","com.almatec.controlpiso.security.entities");
    	HibernateJpaVendorAdapter vendor = new HibernateJpaVendorAdapter();
    	factoryBean.setJpaVendorAdapter(vendor);
    	Map<String, Object> properties = new HashMap<>();
    	properties.put("hibernate.dialect", dialect);
    	properties.put("hibernate.hbm2ddl.auto", ddlAuto);
    	factoryBean.setJpaPropertyMap(properties);
		
    	return factoryBean;
	}
	
	@Bean(name = "integrappsTransactionManager")
    PlatformTransactionManager integrappsTransactionManager(
    		@Qualifier("integrappsEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
