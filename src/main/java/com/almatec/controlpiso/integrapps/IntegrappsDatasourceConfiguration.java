package com.almatec.controlpiso.integrapps;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IntegrappsDatasourceConfiguration {
		
	/**
	 * @return
	 */
	
	@Bean
    @ConfigurationProperties("spring.datasource.integrapps")
    DataSourceProperties integrappsDataSourceProperties() {
        return new DataSourceProperties();
    }

	/**
	 * @return
	 */
	
    @Bean("integrapps")
    DataSource integrappsDataSource() {
        return integrappsDataSourceProperties()
          .initializeDataSourceBuilder()
          .build();
    }
}
