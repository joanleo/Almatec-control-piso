package com.almatec.controlpiso.security.services.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.security.entities.Modulo;
import com.almatec.controlpiso.security.entities.Permission;
import com.almatec.controlpiso.security.repositories.ModuloRepository;
import com.almatec.controlpiso.security.repositories.PermissionRepository;
import com.almatec.controlpiso.security.services.ModuloService;

@Service
public class ModuloServiceImpl implements ModuloService {
	
	private final ModuloRepository moduloRepo;
	private final PermissionRepository permissionRepo;
	private final Logger log = LoggerFactory.getLogger(getClass());
	

	public ModuloServiceImpl(ModuloRepository moduloRepo, PermissionRepository permissionRepo) {
        this.moduloRepo = moduloRepo;
        this.permissionRepo = permissionRepo;
	}


	@Override
	public List<Modulo> findAll() {
		List<Modulo> modulos = moduloRepo.findAllWithPermissionsAndOptions();
        
        // Obtener los permisos principales (con opcion_modulo_id NULL)
        List<Permission> mainPermissions = permissionRepo.findByOpcionModuloIdOpcionIsNull(); 
        
        // Crear un mapa para acceso rápido a los permisos principales por id_modulo
        Map<Long, Permission> mainPermissionsByModuleId = mainPermissions.stream()
                .collect(Collectors.<Permission, Long, Permission>toMap(
                    p -> p.getModulo().getId(),  // key mapper
                    p -> p,                      // value mapper
                    (p1, p2) -> p1              // merge function en caso de duplicados
                ));
        
        // Asignar los permisos principales correctos a cada módulo
        for (Modulo modulo : modulos) {
            Permission mainPermission = mainPermissionsByModuleId.get(modulo.getId());
            if (mainPermission != null) {
                modulo.setPermission(mainPermission);
            }
            log.info("Módulo: {}, Permiso principal asignado: {}", 
                modulo.getNombre(), 
                modulo.getPermission() != null ? modulo.getPermission().getName() : "Sin permiso");
        }
        
        return modulos;
	}

}
