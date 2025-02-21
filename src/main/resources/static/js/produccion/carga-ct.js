let checkList
let opsCargaCt
let selectedCentroId
let spinner = document.getElementById("spinner")

function crearTabla() {
    let table = document.createElement('table')
    table.classList.add('table', 'table-striped')//, 'align-middle', 'table-hover'
    return table
}

function crearHeaderTabla(encabezados) {
    let header = document.createElement('thead')
    header.classList.add('table-light')
    let headerRow = document.createElement('tr')
    let names =''
    for (let name of encabezados) {
        names += `<th scope="col">${name}</th>`
    }
    headerRow.innerHTML = names
    header.appendChild(headerRow)
    return header
}

function crearTbodyTabla(id){
	let tbody = document.createElement('tbody')
	tbody.setAttribute('id', id)
	return tbody
}

function agregarFilaATabla(tbody, data) {
    let row = document.createElement('tr')
    for (let item of data) {
        let cell = document.createElement('td')
        cell.innerHTML = item
        row.appendChild(cell)
    }
    tbody.appendChild(row)
}

function actualizarTablas(opsCargaCt, selectedCentroId, table_items, table_componentes) {
	
	document.getElementById('body-items').innerHTML = ''
	document.getElementById('body-componentes').innerHTML = ''

    let foundItems = false
    let foundComponentes = false
	
	let uniqueItems = new Map()
    let uniqueComponentes = new Map()

    document.querySelectorAll('input[type=checkbox][id^="checkbox_"]:checked').forEach(function (checkbox) {
        let selectedIndex = parseInt(checkbox.value)
        let selectedOp = opsCargaCt[selectedIndex]
        let itemsOps = selectedOp.items
        if (itemsOps && itemsOps.length > 0) {
            for (const itemOp of itemsOps) {
                if (itemOp.itemCentroTId == selectedCentroId) {
					const itemKey = `${itemOp.marca}-${itemOp.itemDescripcion}-${selectedOp.un}-${selectedOp.op}-${itemOp.cantReq}`
                    const existingItem = uniqueItems.get(itemKey)
                    if (!existingItem || itemOp.prioridad < existingItem.prioridad) {
                        uniqueItems.set(itemKey, {
							marca: itemOp.marca,
                            item_desc: itemOp.itemDescripcion,
                            cant_req: itemOp.cantReq,
							cant_fab: itemOp.cantReportadaPieza,
                            proyecto: selectedOp.un,
                            op: selectedOp.op,
                            prioridad: itemOp.prioridad
                        })
                    }
	                    foundItems = true
                }
				
	                let componentes = itemOp.componentes
					for (const componente of componentes) {
	                    if (componente.materialCentroTId == selectedCentroId) {
	                        const componenteKey = `${itemOp.marca}-${componente.materialDescripcion}-${selectedOp.un}-${selectedOp.op}-${componente.materialCant * itemOp.cantReq}`
	                        const existingComponente = uniqueComponentes.get(componenteKey)
	                        if (!existingComponente || itemOp.prioridad < existingComponente.prioridad) {
	                            uniqueComponentes.set(componenteKey, {
									marca: itemOp.marca,
	                                material_desc: componente.materialDescripcion,
	                                material_cant: componente.materialCant * itemOp.cantReq,
									material_fab: componente.cantReportadaMaterial,
	                                proyecto: selectedOp.un,
	                                op: selectedOp.op,
	                                prioridad: itemOp.prioridad
	                            })
	                        }
	                        foundComponentes = true
	                    }
	                }					
				
            }
        }
    })
	
	let allItems = Array.from(uniqueItems.values()).sort((a, b) => a.prioridad - b.prioridad)
    let allComponentes = Array.from(uniqueComponentes.values()).sort((a, b) => a.prioridad - b.prioridad)


    // Add sorted items to the table
    let tbodyItems = document.getElementById("body-items")
    allItems.forEach(item => {
        agregarFilaATabla(tbodyItems, [item.marca, item.item_desc, item.cant_req, item.cant_fab, item.proyecto, item.op, item.prioridad])
    })

    // Add sorted components to the table
    let tbodyComponentes = document.getElementById("body-componentes")
    allComponentes.forEach(componente => {
        agregarFilaATabla(tbodyComponentes, [componente.marca, componente.material_desc, componente.material_cant, componente.material_fab, componente.proyecto, componente.op, componente.prioridad])
    })

    mostrarOcultarTabla('title-items', foundItems)
    mostrarOcultarTabla('wrapper-items', foundItems)
    mostrarOcultarTabla('title-componentes', foundComponentes)
    mostrarOcultarTabla('wrapper-componentes', foundComponentes)
	
	if (foundItems) {
		table_items.removeAttribute('hidden')
		}
    if (foundComponentes){
		table_componentes.removeAttribute('hidden')		
	} 
}

function crearCheckBoxList(opsCargaCt, selectedCentroId, table_items, table_componentes) {
	checkList = document.querySelectorAll('input[type=checkbox][id^="checkbox_"]');
    for (const element of checkList) {
        element.addEventListener('change', function () {
            actualizarTablas(opsCargaCt, selectedCentroId, table_items, table_componentes);
            actualizarBotonesExportacion();
        });
    }
    
    // Actualizar también cuando se usa "Seleccionar todo"
    const selectAllCheckbox = document.getElementById('selectAll');
    if (selectAllCheckbox) {
        selectAllCheckbox.addEventListener('change', function() {
            actualizarBotonesExportacion();
        });
    }
}


function mostrarOcultarTabla(elementId, condition) {
    let element = document.getElementById(elementId)
    if (condition) {
        element.removeAttribute("hidden")
    } else {
        element.setAttribute("hidden", '')
    }
}

async function obtenerOpCentroT(ct){
	spinner.removeAttribute('hidden')
	try{
		const response = await fetch(`/programacion/centro-trabajo/${ct}/resumen-ops`)
		if(!response.ok){
			throw new Error(response)
		}

		const data = await response.json()
		console.log(data)

		spinner.setAttribute('hidden', '')
		return data
	}catch(error){
		console.log("Error al tratar de obtener oprerarion en centro de trabajo", error)
	}
		
}

document.getElementById('centroSelect').addEventListener('change', async function () {
    selectedCentroId = this.value
    opsCargaCt = await obtenerOpCentroT(selectedCentroId)
    document.getElementById('ops-ct').innerHTML = ''
    document.getElementById('detalle-op').innerHTML = ''
    document.getElementById('componentes').innerHTML = ''
    document.getElementById('title-items').setAttribute('hidden', true)
    document.getElementById('title-componentes').setAttribute('hidden', true)

    if (Array.isArray(opsCargaCt) && opsCargaCt.length > 0) {
		
		opsCargaCt.sort((a, b) => b.idOpIntegrapps - a.idOpIntegrapps)
		
        let wrapper_table = document.createElement('div')
        wrapper_table.classList.add('table-responsive', 'text-nowrap', 'my-5','rounded-3', 'shadow-sm')
        
        let wrapper_table_items = document.createElement('div')
        wrapper_table_items.classList.add('table-responsive', 'text-nowrap', 'my-5','rounded-3', 'shadow-sm')
        wrapper_table_items.setAttribute('hidden', '')
        wrapper_table_items.setAttribute('id', 'wrapper-items')
        
        let wrapper_table_componentes = document.createElement('div')
        wrapper_table_componentes.classList.add('table-responsive', 'text-nowrap', 'my-5','rounded-3', 'shadow-sm')
        wrapper_table_componentes.setAttribute('hidden', '')
        wrapper_table_componentes.setAttribute('id', 'wrapper-componentes')

        let table = crearTabla()
        let encabezados = ['', 'CLIENTE', 'PROYECTO', 'OP']
        let header = crearHeaderTabla(encabezados)
        const tbody = crearTbodyTabla('ops')
        table.appendChild(header)
        table.appendChild(tbody)

        let selectAllCheckbox = document.createElement('input')
        selectAllCheckbox.type = 'checkbox'
        selectAllCheckbox.addEventListener('change', function () {
            let isChecked = selectAllCheckbox.checked

		    for(let checkbox of checkList){
				checkbox.checked = isChecked
			}
		
		    actualizarTablas(opsCargaCt, selectedCentroId, table_items, table_componentes)
        })

        let firstCell = header.querySelector('th')
        firstCell.appendChild(selectAllCheckbox)
        
        let checkboxCounter = 1
        opsCargaCt.forEach(function (op, index) {
            agregarFilaATabla(tbody, [`<input type="checkbox" value="${index}" id="checkbox_${checkboxCounter}" name="checkbox_${checkboxCounter}">`, op.cliente, op.un, op.op])
            checkboxCounter++
        })
        

        wrapper_table.appendChild(table)
        document.getElementById("title-ops").removeAttribute("hidden")
        document.getElementById('ops-ct').appendChild(wrapper_table)

        let table_items = crearTabla()
        let encabezado_items = ['MARCA', 'ITEM', 'CANT REQ', 'CANT FAB', 'PROYECTO', 'OP', 'PRIORIDAD']
        let header_items = crearHeaderTabla(encabezado_items)
        table_items.appendChild(header_items)
        table_items.setAttribute('hidden', 'none')
        const tbodyitem = crearTbodyTabla('body-items')
        table_items.appendChild(tbodyitem)
        wrapper_table_items.appendChild(table_items)
        document.getElementById('detalle-op').appendChild(wrapper_table_items)

        let table_componentes = crearTabla()
        let encabezado_componentes = ['MARCA', 'DESCRIPCION', 'CANT REQ', 'CANT FAB', 'PROYECTO', 'OP', 'PRIORIDAD']
        let header_componentes = crearHeaderTabla(encabezado_componentes)
        table_componentes.appendChild(header_componentes)
        table_componentes.setAttribute('hidden', 'none')
        const tbodyComponentes = crearTbodyTabla('body-componentes')
        table_componentes.appendChild(tbodyComponentes)
        wrapper_table_componentes.appendChild(table_componentes)

        
        document.getElementById('componentes').appendChild(wrapper_table_componentes)

        crearCheckBoxList(opsCargaCt, selectedCentroId, table_items, table_componentes)

    }
})

/*async function imprimirSeleccion() {
    let opsSeleccionadas = obtenerOPsSeleccionadas()
    let downloadedFilename 
	spinner.removeAttribute('hidden')
    fetch(`/centros-trabajo/${selectedCentroId}/descargar`, {
	    method: 'POST',
	    headers: {
	        'Content-Type': 'application/json'
	    },
	    body: JSON.stringify(opsSeleccionadas)
	})
	.then(response =>{
		const contentDispositionHeader = response.headers.get('Content-Disposition');
		    if (contentDispositionHeader) {
		        const filenameMatch = contentDispositionHeader.match(/filename=["']?([^"']+)["']?/);
		        if (filenameMatch) {
		            downloadedFilename = filenameMatch[1];
		        }
		    }
		    return response.blob();
	})
    .then(blob => {
        const url = window.URL.createObjectURL(blob)
        const a = document.createElement('a')
        a.href = url
        a.download = `${downloadedFilename}.pdf`
        document.body.appendChild(a)
        a.click()
        window.URL.revokeObjectURL(url)
        spinner.setAttribute('hidden', '')
    })
	.catch(error => {
	    console.error('Error:', error)
	})
    console.log("OPs seleccionadas:", opsSeleccionadas)
}*/

function obtenerOPsSeleccionadas() {
    let opsSeleccionadas = []
    let checkboxesSeleccionados = document.querySelectorAll('input[type=checkbox][id^="checkbox_"]:checked')

    checkboxesSeleccionados.forEach(function (checkbox) {		
        opsSeleccionadas.push(opsCargaCt[parseInt(checkbox.value)].idOpIntegrapps)
    })

    return opsSeleccionadas
}

// Función para agregar botones de exportación
function agregarBotonesExportacion() {
    const btnContainer = document.createElement('div');
    btnContainer.className = 'd-flex justify-content-end mb-4';
    
    const btnGroup = document.createElement('div');
    btnGroup.className = 'btn-group';
    btnGroup.setAttribute('role', 'group');
    
    btnGroup.innerHTML = `
        <button type="button" class="btn btn-danger d-flex align-items-center" onclick="descargarSeleccion('pdf')" disabled id="btnPdf">
            <i class="fa fa-file-pdf me-2"></i>
            <span>Exportar PDF</span>
        </button>
        <button type="button" class="btn btn-success d-flex align-items-center" onclick="descargarSeleccion('excel')" disabled id="btnExcel">
            <i class="fa fa-file-excel me-2"></i>
            <span>Exportar Excel</span>
        </button>
    `;
    
    btnContainer.appendChild(btnGroup);
    
    // Reemplazar el botón existente
    const oldButton = document.querySelector('button[onclick="imprimirSeleccion()"]');
    if (oldButton) {
        oldButton.parentNode.replaceChild(btnContainer, oldButton);
    }
}

// Función para habilitar/deshabilitar botones según la selección
function actualizarBotonesExportacion() {
    const checkboxesSeleccionados = document.querySelectorAll('input[type=checkbox][id^="checkbox_"]:checked');
    const btnPdf = document.getElementById('btnPdf');
    const btnExcel = document.getElementById('btnExcel');
    
    if (btnPdf && btnExcel) {
        const haySeleccion = checkboxesSeleccionados.length > 0;
        btnPdf.disabled = !haySeleccion;
        btnExcel.disabled = !haySeleccion;
    }
}

async function descargarSeleccion(formato) {
    const opsSeleccionadas = obtenerOPsSeleccionadas();
    if (opsSeleccionadas.length === 0) {
        mostrarError('Por favor, seleccione al menos una operación para exportar.');
        return;
    }

    const btnPdf = document.getElementById('btnPdf');
    const btnExcel = document.getElementById('btnExcel');
    const botones = [btnPdf, btnExcel];
    
    // Deshabilitar botones y mostrar spinner
    botones.forEach(btn => {
        if (btn) btn.disabled = true;
    });
    spinner.removeAttribute('hidden');
    
    try {
        const response = await fetch(`/centros-trabajo/${selectedCentroId}/descargar/${formato}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(opsSeleccionadas)
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const contentDisposition = response.headers.get('Content-Disposition');
        let downloadedFilename = `export.${formato}`;
        
        if (contentDisposition) {
            const filenameMatch = contentDisposition.match(/filename=["']?([^"']+)["']?/);
            if (filenameMatch) {
                downloadedFilename = filenameMatch[1];
            }
        }

        const blob = await response.blob();
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = downloadedFilename;
        document.body.appendChild(a);
        a.click();
        window.URL.revokeObjectURL(url);
    } catch (error) {
        console.error('Error:', error);
        mostrarError('Error al descargar el archivo. Por favor, intente nuevamente.');
    } finally {
        // Restaurar estado de los botones y ocultar spinner
        spinner.setAttribute('hidden', '');
        actualizarBotonesExportacion();
    }
}

function mostrarError(mensaje) {
    const alertDiv = document.createElement('div');
    alertDiv.className = 'alert alert-danger alert-dismissible fade show mt-3';
    alertDiv.innerHTML = `
        <div class="d-flex align-items-center">
            <i class="bi bi-exclamation-triangle-fill me-2"></i>
            <span>${mensaje}</span>
        </div>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    `;
    
    const container = document.querySelector('.container');
    const opsSection = document.getElementById('ops-section');
    container.insertBefore(alertDiv, opsSection);
    
    // Auto-cerrar la alerta después de 5 segundos
    setTimeout(() => {
        const bsAlert = new bootstrap.Alert(alertDiv);
        bsAlert.close();
    }, 5000);
}

// Llamar a esta función después de que se cargue el documento
document.addEventListener('DOMContentLoaded', function() {
    agregarBotonesExportacion();
});