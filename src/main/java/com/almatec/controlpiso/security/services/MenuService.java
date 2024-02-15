package com.almatec.controlpiso.security.services;

import java.util.List;

import com.almatec.controlpiso.security.entities.Menu;

public interface MenuService {

	List<Menu> buscarMenus();

	void guardar(Menu menu);

}
