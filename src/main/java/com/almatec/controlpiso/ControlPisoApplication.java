package com.almatec.controlpiso;

import java.awt.image.BufferedImage;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@ComponentScan("com.almatec.controlpiso")
@EntityScan(basePackages = {"com.almatec.controlpiso.integrapps.entities","com.almatec.controlpiso.security.entities"})
public class ControlPisoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlPisoApplication.class, args);
	}
	
	@Bean
    HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
