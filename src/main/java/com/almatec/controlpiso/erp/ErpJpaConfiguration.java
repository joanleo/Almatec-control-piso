package com.almatec.controlpiso.erp;

import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = "com.almatec.controlpiso.erp.repositories",
  entityManagerFactoryRef = "erpEntityManagerFactory",
  transactionManagerRef = "erpTransactionManager"
)
public class ErpJpaConfiguration {
	
    /**
     * @param dataSource
     * @param builder
     * @return
     */
	@Primary
    @Bean
    LocalContainerEntityManagerFactoryBean erpEntityManagerFactory(
      @Qualifier("erp") DataSource dataSource,
      EntityManagerFactoryBuilder builder
    ) {
        return builder
          .dataSource(dataSource)
          .packages("com.almatec.controlpiso.erp.entities")
          .build();
    }

    /**
     * @param erpEntityManagerFactory
     * @return
     */
	@Primary
    @Bean
    PlatformTransactionManager erpTransactionManager(
      @Qualifier("erpEntityManagerFactory") LocalContainerEntityManagerFactoryBean erpEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(erpEntityManagerFactory.getObject()));
    }

}
