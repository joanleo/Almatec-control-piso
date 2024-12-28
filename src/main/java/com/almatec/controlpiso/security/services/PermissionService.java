package com.almatec.controlpiso.security.services;

import java.util.Collection;
import java.util.List;

import com.almatec.controlpiso.security.entities.Permission;

public interface PermissionService {

	Permission findPermissionById(Long id);
	
	List<Permission> findAllByIdIn(Collection<Long> ids);

	Collection<Permission> getAllPermissions();

}
