package com.almatec.controlpiso.comunicador;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComunicadorJpaConfiguration {

	@Bean
    @ConfigurationProperties("spring.datasource.comunicador")
    DataSourceProperties comunicadorDataSourceProperties() {
        return new DataSourceProperties();
    }

	/**
	 * @return
	 */
	
    @Bean("comunicador")
    DataSource comunicadorDataSource() {
        return comunicadorDataSourceProperties()
          .initializeDataSourceBuilder()
          .build();
    }
}
