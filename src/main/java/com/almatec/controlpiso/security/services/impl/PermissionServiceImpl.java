package com.almatec.controlpiso.security.services.impl;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.security.entities.Permission;
import com.almatec.controlpiso.security.repositories.PermissionRepository;
import com.almatec.controlpiso.security.services.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	private final PermissionRepository permissionRepo;
		
	public PermissionServiceImpl(PermissionRepository permissionRepo) {
		this.permissionRepo = permissionRepo;
	}



	@Override
	public Permission findPermissionById(Long id) {
		return permissionRepo.findByIdPermiso(id);
	}

}
