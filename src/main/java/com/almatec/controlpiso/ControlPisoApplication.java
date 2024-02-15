package com.almatec.controlpiso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.almatec.controlpiso")
@EntityScan(basePackages = {"com.almatec.controlpiso.integrapps.entities","com.almatec.controlpiso.security.entities"})
public class ControlPisoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlPisoApplication.class, args);
	}

}
