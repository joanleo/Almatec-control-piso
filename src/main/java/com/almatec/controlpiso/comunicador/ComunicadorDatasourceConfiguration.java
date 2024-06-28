package com.almatec.controlpiso.comunicador;

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
  entityManagerFactoryRef = "comunicadorEntityManagerFactory",
  transactionManagerRef = "comunicadorTransactionManager",
  basePackages = {"com.almatec.controlpiso.comunicador.repositories"}
)
public class ComunicadorDatasourceConfiguration {

	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String dialect;
	
	@Value("${spring.jpa.properties.comunicador.hibernate.ddl-auto}")
    private String ddlAuto;

	@Bean(name = "comunicadorEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean comunicadorEntityManagerFactory(
    		@Qualifier("comunicador") DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
    	factoryBean.setDataSource(dataSource);
    	factoryBean.setPackagesToScan("com.almatec.controlpiso.comunicador.entities");
    	HibernateJpaVendorAdapter vendor = new HibernateJpaVendorAdapter();
    	factoryBean.setJpaVendorAdapter(vendor);
    	Map<String, Object> properties = new HashMap<>();
    	properties.put("hibernate.dialect", dialect);
    	properties.put("hibernate.hbm2ddl.auto", ddlAuto);
    	factoryBean.setJpaPropertyMap(properties);
		
    	return factoryBean;
	}
	
	@Bean(name = "comunicadorTransactionManager")
    PlatformTransactionManager comunicadorTransactionManager(
    		@Qualifier("comunicadorEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
