package com.almatec.controlpiso.integrapps;

import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = "com.almatec.controlpiso",
  entityManagerFactoryRef = "integrappsEntityManagerFactory",
  transactionManagerRef = "integrappsTransactionManager"
)
public class IntegrappsJpaConfiguration {

    /**
     * @param dataSource
     * @param builder
     * @return
     */
    
    @Bean
    LocalContainerEntityManagerFactoryBean integrappsEntityManagerFactory(
       @Qualifier("integrapps") DataSource dataSource,
       EntityManagerFactoryBuilder builder
       ) {
        return builder
          .dataSource(dataSource)
          .packages("com.almatec.controlpiso.integrapps.entities","com.almatec.controlpiso.security.entities")
          .build();
    }

    /**
     * @param integrappsEntityManagerFactory
     * @return
     */
    
    @Bean
    PlatformTransactionManager integrappsTransactionManager(
       @Qualifier("integrappsEntityManagerFactory") LocalContainerEntityManagerFactoryBean integrappsEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(integrappsEntityManagerFactory.getObject()));
    }

}
