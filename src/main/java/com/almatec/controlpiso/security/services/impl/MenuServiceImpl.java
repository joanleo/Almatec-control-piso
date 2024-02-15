package com.almatec.controlpiso.security.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.security.entities.Menu;
import com.almatec.controlpiso.security.repositories.MenuRepository;
import com.almatec.controlpiso.security.services.MenuService;

@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private MenuRepository menuRepo;

	@Override
	public List<Menu> buscarMenus() {
		return menuRepo.findAll();
	}

	@Override
	public void guardar(Menu menu) {
		menuRepo.save(menu);
		
	}

}
