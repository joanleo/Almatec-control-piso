package com.almatec.controlpiso.security.services;

import com.almatec.controlpiso.security.entities.Permission;

public interface PermissionService {

	Permission findPermissionById(Long id);

}
