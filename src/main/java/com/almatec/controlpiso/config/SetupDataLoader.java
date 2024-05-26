package com.almatec.controlpiso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	private AppConfig app;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		//app.poblarBdRutas(event);
		
	}

	
}
