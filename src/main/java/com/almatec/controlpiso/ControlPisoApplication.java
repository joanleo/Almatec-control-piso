package com.almatec.controlpiso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.almatec.controlpiso")
public class ControlPisoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlPisoApplication.class, args);
	}

}
