package com.almatec.controlpiso.erp;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ErpDatasourceConfiguration {
	
	/**
	 * @return
	 */
	@Primary
	@Bean
    @ConfigurationProperties("spring.datasource.erp")
    DataSourceProperties erpDataSourceProperties() {
        return new DataSourceProperties();
    }
	
    /**
     * @return
     */
	@Primary
    @Bean("erp")
    DataSource erpDataSource() {
        return erpDataSourceProperties()
          .initializeDataSourceBuilder()
          .build();
    }
}
