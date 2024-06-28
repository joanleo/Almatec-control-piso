document.addEventListener("DOMContentLoaded", function() {
	
	let modulos = JSON.parse(modulosString)
    let checkboxes = document.querySelectorAll('.modulos input[type="checkbox"]')
    let divDetalleModulos = document.getElementById('detalles-modulos')
    
    let permissionsField = document.getElementById('permissions-field');
    let selectedPermissions = new Set();
    
    console.log(modulos)
    
    checkboxes.forEach(function(checkbox) {
        checkbox.addEventListener('change', function() {
            const moduleSelect = document.querySelector('label[for="' + checkbox.id + '"]').textContent
            checkbox.dataset.moduleName = moduleSelect
                let moduloSeleccionado = modulos.find(function(modulo) {
                    return modulo.nombre === moduleSelect
                })            
            if (this.checked) {
                console.log(moduleSelect)
                  
                if (moduloSeleccionado) {
                    console.log('Módulo seleccionado:', moduloSeleccionado);
                    
                    const moduloPermission = moduloSeleccionado.permission.idPermiso
                    selectedPermissions.add(moduloPermission)
                    
                    const opcionesMudulo = moduloSeleccionado.opciones
                    let moduloDivWrapper = document.createElement('div')
                    moduloDivWrapper.dataset.moduleName = moduleSelect
	                moduloDivWrapper.classList.add('my-4')
	                
                    let moduloDiv = document.createElement('div')
	                moduloDiv.classList.add('modulos', 'my-4')
	                
	                let titulo = document.createElement('h3')
	                titulo.textContent = moduleSelect
	                moduloDivWrapper.appendChild(titulo)
	                
	                opcionesMudulo.forEach(function(opcion) {
		                let moduloDivGroup = document.createElement('div')
		                moduloDivGroup.classList.add('form-check', 'form-switch', 'mb-5', 'mx-4')

	                    let opcionCheckbox = document.createElement('input')
	                    opcionCheckbox.type = 'checkbox';
	                    opcionCheckbox.classList.add('form-check-input')
	                    opcionCheckbox.id = 'checkbox_' + opcion.idOpcion
	                    opcionCheckbox.role = 'switch'
	                    opcionCheckbox.name = 'permissions';
	                    opcionCheckbox.value = opcion.permission.idPermiso;
	                    
	                    let opcionLabel = document.createElement('label')
	                    opcionLabel.classList.add('form-check-label')
	                    opcionLabel.textContent = opcion.descripcion
	                    opcionLabel.setAttribute('for', opcionCheckbox.id)
	                    
	                    moduloDivGroup.appendChild(opcionCheckbox)
	                    moduloDivGroup.appendChild(opcionLabel)
	                    moduloDiv.appendChild(moduloDivGroup)
	                    
	                    opcionCheckbox.addEventListener('change', function() {
                            if (this.checked) {
                                selectedPermissions.add(this.value)
                            } else {
                                selectedPermissions.delete(this.value)
                            }
                            updatePermissionsField();
                        });
	                })
	                moduloDivWrapper.appendChild(moduloDiv)
	                divDetalleModulos.appendChild(moduloDivWrapper)
                } else {
                    console.log('No se encontró ningún módulo con el nombre:', moduleSelect)
                }
            } else {
				const moduloPermission = moduloSeleccionado.permission.idPermiso;
                selectedPermissions.delete(moduloPermission)
                
				this.dataset.moduleName = moduleSelect
                let moduleDivWrapper = divDetalleModulos.querySelector(`div[data-module-name="${this.dataset.moduleName}"]`)
                if (moduleDivWrapper) {
                    let childCheckboxes = moduleDivWrapper.querySelectorAll('input[type="checkbox"]')
                    childCheckboxes.forEach(function(childCheckbox) {
                        selectedPermissions.delete(childCheckbox.value)
                    });
                    divDetalleModulos.removeChild(moduleDivWrapper)
                    updatePermissionsField();
                }
            }
            
            updatePermissionsField();
        })
    })
    
    function updatePermissionsField() {
        permissionsField.value = Array.from(selectedPermissions).join(',')
        console.log(permissionsField.value)
    }
})
