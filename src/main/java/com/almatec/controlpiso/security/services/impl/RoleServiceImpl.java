package com.almatec.controlpiso.security.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.security.entities.Role;
import com.almatec.controlpiso.security.repositories.RoleRepository;
import com.almatec.controlpiso.security.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository rolRepo;

	@Override
	public List<Role> buscarRoles() {
		return rolRepo.findByIsActivoTrue();
	}

}
