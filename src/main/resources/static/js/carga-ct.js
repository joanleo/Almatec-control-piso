let checkList
let opsCargaCt
let selectedCentroId

function crearTabla() {
    let table = document.createElement('table');
    table.classList.add('table', 'table-striped', 'align-middle', 'table-hover', 'table-bordered', 'rounded');
    return table;
}

function crearHeaderTabla(encabezados) {
    let header = document.createElement('thead');
    header.classList.add('table-dark');
    let headerRow = document.createElement('tr');
    let names =''
    for (let name of encabezados) {
        names += `<th scope="col">${name}</th>`;
    }
    headerRow.innerHTML = names;
    header.appendChild(headerRow);
    return header;
}

function agregarFilaATabla(table, data) {
    let row = document.createElement('tr')
    for (let item of data) {
        let cell = document.createElement('td')
        cell.innerHTML = item;
        row.appendChild(cell)
    }
    table.appendChild(row)
}

function actualizarTablas(opsCargaCt, selectedCentroId, table_items, table_componentes) {
    while (table_items.rows.length > 1) {
        table_items.deleteRow(1);
    }
    while (table_componentes.rows.length > 1) {
        table_componentes.deleteRow(1);
    }

    let foundItems = false;
    let foundComponentes = false;

    document.querySelectorAll('input[type=checkbox][id^="checkbox_"]:checked').forEach(function (checkbox) {
        let selectedIndex = parseInt(checkbox.value);
        let selectedOp = opsCargaCt[selectedIndex];

        let items = selectedOp.items;
        if (items && items.length > 0) {
            for (item of items) {
                if (selectedCentroId == item.idCentroTrabajo) {
                    agregarFilaATabla(table_items, [item.descripcion, item.cant, selectedOp.proyecto]);
                    foundItems = true;
                    table_items.removeAttribute('hidden')
                }

                let componentes = item.componentes;
                for (componente of componentes) {
                    if (selectedCentroId == componente.idCentroTrabajoPerfil) {
                        agregarFilaATabla(table_componentes, [componente.descripcionPerfil, componente.cantListaMateriales, selectedOp.proyecto]);
                        foundComponentes = true;
                        table_componentes.removeAttribute('hidden')
                    }
                }
            }
        }
    });

    mostrarOcultarTabla('title-items', foundItems);
    mostrarOcultarTabla('title-componentes', foundComponentes);
    mostrarOcultarTabla('detalle-op', foundItems);
    mostrarOcultarTabla('componentes', foundComponentes);
}

function crearCheckBoxList(opsCargaCt, selectedCentroId, table_items, table_componentes) {
    checkList = document.querySelectorAll('input[type=checkbox][id^="checkbox_"]');
    for (let index = 0; index < checkList.length; index++) {
        checkList[index].addEventListener('change', function () {
            // Llamar a la función para actualizar las tablas
            actualizarTablas(opsCargaCt, selectedCentroId, table_items, table_componentes);
        });
    }
}

function mostrarOcultarTabla(elementId, condition) {
    let element = document.getElementById(elementId);
    if (condition) {
        element.removeAttribute("hidden");
    } else {
        element.setAttribute("hidden", true);
    }
}

document.getElementById('centroSelect').addEventListener('change', async function () {
    selectedCentroId = this.value;
    opsCargaCt = await obtenerOpCentroT(selectedCentroId);
    document.getElementById('ops-ct').innerHTML = '';

    if (Array.isArray(opsCargaCt) && opsCargaCt.length > 0) {
        let wrapper_table = document.createElement('div');
        wrapper_table.classList.add('table-responsive', 'text-nowrap', 'my-5');

        let table = crearTabla();
        let encabezados = ['', 'CLIENTE', 'PROYECTO'];
        let header = crearHeaderTabla(encabezados);
        table.appendChild(header);

        let selectAllCheckbox = document.createElement('input');
        selectAllCheckbox.type = 'checkbox';
        selectAllCheckbox.addEventListener('change', function () {
            let isChecked = selectAllCheckbox.checked;

		    for(let checkbox of checkList){
				checkbox.checked = isChecked;
			}
		
		    actualizarTablas(opsCargaCt, selectedCentroId, table_items, table_componentes)
        });

        let firstCell = header.querySelector('th'); // Obtener la primera celda
        firstCell.appendChild(selectAllCheckbox);
        
        let checkboxCounter = 1;
        opsCargaCt.forEach(function (op, index) {
            agregarFilaATabla(table, [`<input type="checkbox" value="${index}" id="checkbox_${checkboxCounter}" name="checkbox_${checkboxCounter}">`, op.cliente, op.proyecto]);
            checkboxCounter++;
        });
        

        wrapper_table.appendChild(table);
        document.getElementById("title-ops").removeAttribute("hidden");
        document.getElementById('ops-ct').appendChild(wrapper_table);

        // Crear y gestionar las tablas de detalles
        let table_items = crearTabla();
        let encabezado_items = ['ITEM', 'CANT', 'PROYECTO'];
        let header_items = crearHeaderTabla(encabezado_items);
        table_items.appendChild(header_items)
        table_items.setAttribute('hidden', 'none')

        let table_componentes = crearTabla();
        let encabezado_componentes = ['DESCRIPCION', 'CANT', 'PROYECTO'];
        let header_componentes = crearHeaderTabla(encabezado_componentes);
        table_componentes.appendChild(header_componentes);
        table_componentes.setAttribute('hidden', 'none')

        document.getElementById('detalle-op').appendChild(table_items);
        document.getElementById('componentes').appendChild(table_componentes);

        crearCheckBoxList(opsCargaCt, selectedCentroId, table_items, table_componentes);

    }
});

async function imprimirSeleccion() {
    let opsSeleccionadas = obtenerOPsSeleccionadas()
    let nombreCt
    let cts = centrosTrabajo = await fetchCentrosT()
    for(let ct of cts){
		if(selectedCentroId == ct.id){
			nombreCt = ct.nombre
		}
	}
    fetch(`/centros-trabajo/${selectedCentroId}/descargar`, {
	    method: 'POST',
	    headers: {
	        'Content-Type': 'application/json'
	    },
	    body: JSON.stringify(opsSeleccionadas)
	})
	.then(response => response.blob())
    .then(blob => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `${nombreCt}.pdf`
        document.body.appendChild(a);
        a.click();
        window.URL.revokeObjectURL(url);
    })
	.catch(error => {
	    console.error('Error:', error)
	})
    console.log("OPs seleccionadas:", opsSeleccionadas);
}

function obtenerOPsSeleccionadas() {
    let opsSeleccionadas = [];
    let checkboxesSeleccionados = document.querySelectorAll('input[type=checkbox][id^="checkbox_"]:checked')

    checkboxesSeleccionados.forEach(function (checkbox) {
		
        opsSeleccionadas.push(opsCargaCt[parseInt(checkbox.value)].idOp)
    })

    return opsSeleccionadas;
}
