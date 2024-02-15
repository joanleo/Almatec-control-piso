package com.almatec.controlpiso.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

	@Bean
	RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Value("${pdf.folder.path}")
    private String pdfFolderPath;

	public String getPdfFolderPath() {
		return pdfFolderPath;
	}
	
}
