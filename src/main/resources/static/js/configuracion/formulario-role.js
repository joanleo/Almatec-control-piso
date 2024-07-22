document.addEventListener("DOMContentLoaded", function() {
    let modulos = JSON.parse(modulosString);
    let rolePermissions = JSON.parse(rolePermissionsJson);
    let permissionsField = document.getElementById('permissions-field');
    let selectedPermissions = new Set(rolePermissions.map(p => p.id));
    
    // Inicializar el campo de permisos con los IDs de los permisos del rol
    permissionsField.value = Array.from(selectedPermissions).join(',');
    
    let checkboxes = document.querySelectorAll('.modulos input[type="checkbox"]');
    let divDetalleModulos = document.getElementById('detalles-modulos');
    
    checkboxes.forEach(function(checkbox) {
        const moduleSelect = document.querySelector('label[for="' + checkbox.id + '"]').textContent;
        let moduloSeleccionado = modulos.find(function(modulo) {
            return modulo.nombre === moduleSelect;
        });
        
        if (moduloSeleccionado && selectedPermissions.has(moduloSeleccionado.permission.idPermiso.toString())) {
            checkbox.checked = true;
            cargarOpcionesModulo(moduloSeleccionado, moduleSelect);
        }
        
        checkbox.addEventListener('change', function() {
            if (this.checked) {
                if (moduloSeleccionado) {
                    selectedPermissions.add(moduloSeleccionado.permission.idPermiso.toString());
                    cargarOpcionesModulo(moduloSeleccionado, moduleSelect);
                }
            } else {
                if (moduloSeleccionado) {
                    selectedPermissions.delete(moduloSeleccionado.permission.idPermiso.toString());
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
        const opcionesMudulo = moduloSeleccionado.opciones;
        let moduloDivWrapper = document.createElement('div');
        moduloDivWrapper.dataset.moduleName = moduleSelect;
        moduloDivWrapper.classList.add('my-4');
        
        let moduloDiv = document.createElement('div');
        moduloDiv.classList.add('modulos', 'my-4');
        
        let titulo = document.createElement('h3');
        titulo.textContent = moduleSelect;
        moduloDivWrapper.appendChild(titulo);
        
        opcionesMudulo.forEach(function(opcion) {
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
        });
        moduloDivWrapper.appendChild(moduloDiv);
        divDetalleModulos.appendChild(moduloDivWrapper);
    }
    
    function updatePermissionsField() {
        permissionsField.value = Array.from(selectedPermissions).join(',');
    }
});