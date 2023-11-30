package com.almatec.controlpiso.security.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.security.entities.Role;
import com.almatec.controlpiso.security.repositories.RolRepository;
import com.almatec.controlpiso.security.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RolRepository rolRepo;

	@Override
	public List<Role> buscarRoles() {
		return rolRepo.findByIsActivoTrue();
	}

}
