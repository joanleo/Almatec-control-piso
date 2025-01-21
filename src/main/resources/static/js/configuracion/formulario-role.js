document.addEventListener("DOMContentLoaded", function() {
    let modulos = JSON.parse(modulosString);
    let rolePermissions = JSON.parse(rolePermissionsJson);
    let permissionsField = document.getElementById('permissions-field');
    let selectedPermissions = new Set(rolePermissions.map(p => p.id));
    
    // Log inicial para depuración
    console.log('Módulos cargados:', modulos);
    console.log('Permisos del rol:', rolePermissions);
    console.log('Permisos seleccionados:', Array.from(selectedPermissions));
    
    // Inicializar el campo de permisos
    permissionsField.value = Array.from(selectedPermissions).join(',');
    
    let checkboxes = document.querySelectorAll('.modulos input[type="checkbox"]');
    let divDetalleModulos = document.getElementById('detalles-modulos');
    
    checkboxes.forEach(function(checkbox) {
        const moduleSelect = document.querySelector('label[for="' + checkbox.id + '"]').textContent;
        let moduloSeleccionado = modulos.find(function(modulo) {
            return modulo.nombre === moduleSelect;
        });
        
        // Log para verificar el módulo seleccionado
        console.log('Procesando módulo:', {
            nombre: moduleSelect,
            modulo: moduloSeleccionado,
            permisoModulo: moduloSeleccionado?.permission
        });
        
        // Verificar si el permiso principal del módulo está seleccionado
        if (moduloSeleccionado && 
            moduloSeleccionado.permission && 
            selectedPermissions.has(moduloSeleccionado.permission.idPermiso.toString())) {
            checkbox.checked = true;
            cargarOpcionesModulo(moduloSeleccionado, moduleSelect);
        }
        
        checkbox.addEventListener('change', function() {
            if (this.checked) {
                if (moduloSeleccionado && moduloSeleccionado.permission) {
                    console.log('Activando módulo:', moduleSelect, moduloSeleccionado.permission);
                    // Agregar el permiso principal del módulo
                    selectedPermissions.add(moduloSeleccionado.permission.idPermiso.toString());
                    cargarOpcionesModulo(moduloSeleccionado, moduleSelect);
                }
            } else {
                if (moduloSeleccionado && moduloSeleccionado.permission) {
                    console.log('Desactivando módulo:', moduleSelect);
                    // Eliminar el permiso principal del módulo
                    selectedPermissions.delete(moduloSeleccionado.permission.idPermiso.toString());
                    
                    // Eliminar los permisos de las opciones
                    moduloSeleccionado.opciones.forEach(opcion => {
                        if (opcion.permission) {
                            selectedPermissions.delete(opcion.permission.idPermiso.toString());
                        }
                    });
                    
                    let moduleDivWrapper = divDetalleModulos.querySelector(`div[data-module-name="${moduleSelect}"]`);
                    if (moduleDivWrapper) {
                        divDetalleModulos.removeChild(moduleDivWrapper);
                    }
                }
            }
            updatePermissionsField();
        });
    });
    
    function cargarOpcionesModulo(moduloSeleccionado, moduleSelect) {
        const opcionesModulo = moduloSeleccionado.opciones || [];
        let moduloDivWrapper = document.createElement('div');
        moduloDivWrapper.dataset.moduleName = moduleSelect;
        moduloDivWrapper.classList.add('my-4');
        
        let moduloDiv = document.createElement('div');
        moduloDiv.classList.add('modulos', 'my-4');
        
        let titulo = document.createElement('h3');
        titulo.textContent = moduleSelect;
        moduloDivWrapper.appendChild(titulo);
        
        opcionesModulo.forEach(function(opcion) {
            if (opcion && opcion.permission) {
                let moduloDivGroup = document.createElement('div');
                moduloDivGroup.classList.add('form-check', 'form-switch', 'mb-5', 'mx-4');

                let opcionCheckbox = document.createElement('input');
                opcionCheckbox.type = 'checkbox';
                opcionCheckbox.classList.add('form-check-input');
                opcionCheckbox.id = 'checkbox_' + opcion.idOpcion;
                opcionCheckbox.role = 'switch';
                opcionCheckbox.name = 'permissions';
                opcionCheckbox.value = opcion.permission.idPermiso;
                
                if (selectedPermissions.has(opcion.permission.idPermiso.toString())) {
                    opcionCheckbox.checked = true;
                }
                
                let opcionLabel = document.createElement('label');
                opcionLabel.classList.add('form-check-label');
                opcionLabel.textContent = opcion.descripcion;
                opcionLabel.setAttribute('for', opcionCheckbox.id);
                
                moduloDivGroup.appendChild(opcionCheckbox);
                moduloDivGroup.appendChild(opcionLabel);
                moduloDiv.appendChild(moduloDivGroup);
                
                opcionCheckbox.addEventListener('change', function() {
                    if (this.checked) {
                        selectedPermissions.add(this.value);
                    } else {
                        selectedPermissions.delete(this.value);
                    }
                    updatePermissionsField();
                });
            }
        });
        
        moduloDivWrapper.appendChild(moduloDiv);
        divDetalleModulos.appendChild(moduloDivWrapper);
    }
    
    function updatePermissionsField() {
        let data = Array.from(selectedPermissions).join(',');
        permissionsField.value = data;
        console.log('Permisos actualizados:', data);
    }
});