package com.almatec.controlpiso.config;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

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
	
	
	public void poblarBdRutas(ContextRefreshedEvent event) {
		
		ApplicationContext applicationContext = event.getApplicationContext();
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext
          .getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();

        String aux="";
        
        Set<String> arrRutinas = new HashSet<>();

        for(Entry<RequestMappingInfo, HandlerMethod> rutaMetodo: map.entrySet()) {
        	String[] parts = rutaMetodo.getKey().getActivePatternsCondition().toString().replace("[", "").replace("]", "").split("/");
        	//System.out.println(rutaMetodo.getKey());
        	if (parts.length > 1) {
                String rutina = parts[1];
                if(rutina.equalsIgnoreCase("error") || rutina.equalsIgnoreCase("v3") || rutina.equalsIgnoreCase("swagger-ui.html") || 
                		rutina.equalsIgnoreCase("auth") || rutina.equalsIgnoreCase("email") || rutina.equalsIgnoreCase("rutinas")) {
                	continue;
                }
                if (!Objects.equals(rutina, aux)) {
                    aux = rutina;
                    arrRutinas.add(aux);
                }
            }
        }
                
        for(String rut: arrRutinas) {
        	//Rutina nuevaRutina = new Rutina();
        	//nuevaRutina.setNombre(rut);
	        for(Entry<RequestMappingInfo, HandlerMethod> rutaMetodo: map.entrySet()) {
	        	String[] rutina = rutaMetodo.getKey().getActivePatternsCondition().toString().replace("[", "").replace("]", "").split("/");
	        	System.out.println("Rutina: " + rutina);
	        	String ruta = rutaMetodo.getKey().getActivePatternsCondition().toString().replace("[", "").replace("]", "");
	        	System.out.println("Ruta: " + ruta);
	        	String metodo = rutaMetodo.getKey().getMethodsCondition().toString().replace("[", "").replace("]", "");
	        	System.out.println("Metodo: " + metodo);
	        }
        }
        
	}
}
