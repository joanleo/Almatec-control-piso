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
    /*while (table_items.rows.length > 1) {
        table_items.deleteRow(1)
    }
    while (table_componentes.rows.length > 1) {
        table_componentes.deleteRow(1)
    }*/

    let foundItems = false
    let foundComponentes = false

    document.querySelectorAll('input[type=checkbox][id^="checkbox_"]:checked').forEach(function (checkbox) {
        let selectedIndex = parseInt(checkbox.value)
        let selectedOp = opsCargaCt[selectedIndex]

        let itemsOps = selectedOp.items
        if (itemsOps && itemsOps.length > 0) {
            for (const itemOp of itemsOps) {
                if (itemOp.item_centro_t_id == selectedCentroId) {
					let tbodyItems = document.getElementById("body-items")
					console.log(itemOp)
                    agregarFilaATabla(tbodyItems, [itemOp.item_desc, itemOp.cant_req, selectedOp.proyecto, selectedOp.op, itemOp.prioridad])
                    foundItems = true
                    table_items.removeAttribute('hidden')
                }
				if(!foundItems){
	                let componentes = itemOp.componentes
	                for (const componente of componentes) {
	                    if (componente.material_centro_t_id == selectedCentroId) {
							let tbodyComponentes = document.getElementById("body-componentes")
	                        agregarFilaATabla(tbodyComponentes, [componente.material_desc, componente.material_cant, selectedOp.proyecto, selectedOp.op])
	                        foundComponentes = true
	                        table_componentes.removeAttribute('hidden')
	                    }
	                }					
				}
            }
        }
    })

    mostrarOcultarTabla('title-items', foundItems)
    mostrarOcultarTabla('wrapper-items', foundItems)
    mostrarOcultarTabla('title-componentes', foundComponentes)
    mostrarOcultarTabla('wrapper-componentes', foundComponentes)
}

function crearCheckBoxList(opsCargaCt, selectedCentroId, table_items, table_componentes) {
    checkList = document.querySelectorAll('input[type=checkbox][id^="checkbox_"]')
    for (const element of checkList) {
        element.addEventListener('change', function () {
        	actualizarTablas(opsCargaCt, selectedCentroId, table_items, table_componentes)
        })
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
		const response = await fetch(`/centros-trabajo/${ct}/ordenes-produccion`)
		if(!response.ok){
			throw new Error(response)
		}

		const data = await response.json()

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
            agregarFilaATabla(tbody, [`<input type="checkbox" value="${index}" id="checkbox_${checkboxCounter}" name="checkbox_${checkboxCounter}">`, op.cliente, op.proyecto, op.op])
            checkboxCounter++
        })
        

        wrapper_table.appendChild(table)
        document.getElementById("title-ops").removeAttribute("hidden")
        document.getElementById('ops-ct').appendChild(wrapper_table)

        let table_items = crearTabla()
        let encabezado_items = ['ITEM', 'CANT', 'PROYECTO', 'OP', 'PRIORIDAD']
        let header_items = crearHeaderTabla(encabezado_items)
        table_items.appendChild(header_items)
        table_items.setAttribute('hidden', 'none')
        const tbodyitem = crearTbodyTabla('body-items')
        table_items.appendChild(tbodyitem)
        wrapper_table_items.appendChild(table_items)
        document.getElementById('detalle-op').appendChild(wrapper_table_items)

        let table_componentes = crearTabla()
        let encabezado_componentes = ['DESCRIPCION', 'CANT', 'PROYECTO', 'OP']
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

async function imprimirSeleccion() {
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
				console.log(filenameMatch)
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
}

function obtenerOPsSeleccionadas() {
    let opsSeleccionadas = []
    let checkboxesSeleccionados = document.querySelectorAll('input[type=checkbox][id^="checkbox_"]:checked')

    checkboxesSeleccionados.forEach(function (checkbox) {		
        opsSeleccionadas.push(opsCargaCt[parseInt(checkbox.value)].idOp)
    })

    return opsSeleccionadas
}
