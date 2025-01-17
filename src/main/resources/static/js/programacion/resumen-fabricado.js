let itemsGlobal = []
let itemsOriginales = []
const pageSize = 10
let centrosTrabajoPrioridad = []
let centroTrabajoSelectedId
let spinner

let currentPage = 1
let sortColumn = 'op'
let sortDirection = 'desc'
let filterText = ''
document.addEventListener('DOMContentLoaded', async function(){
	spinner = document.getElementById('spinner')
	spinner.removeAttribute('hidden')
	const centrosTrabajoFilter = [3, 4, 5, 6, 7, 8, 9,11,13, 17];

	const centrosTrabajo = await fetchCentrosT()
	
	centrosTrabajoPrioridad = centrosTrabajo.filter(centro=>centrosTrabajoFilter.includes(centro.id))
	const select = document.getElementById('centroTrabajoSelect')
	select.innerHTML = '';
	
	const optionDefault = document.createElement('option')
	optionDefault.value = ''
	optionDefault.text = 'Seleccione un centro de trabajo'
	optionDefault.disabled = true
	optionDefault.selected = true
	select.appendChild(optionDefault)
	
	centrosTrabajoPrioridad.forEach(centro => {
	  const option = document.createElement('option')
	  option.value = centro.id
	  option.text = centro.nombre
	  select.appendChild(option)
	})
	spinner.setAttribute('hidden', '')
	
	select.addEventListener('change', async function() {
		document.getElementById('filterInput').value = ''
		filterText = ''
		centroTrabajoSelectedId = this.value;
        if (centroTrabajoSelectedId) {		
			spinner.removeAttribute('hidden')	
            const ordenes = await obtenerOrdenes(centroTrabajoSelectedId);
			ordenes.sort((a, b) => {
			    return b.idOpIntegrapps - a.idOpIntegrapps;
			  });
		   itemsGlobal = procesarOrdenes(ordenes)
           itemsOriginales = [...itemsGlobal]
           updateTable()
		   spinner.setAttribute('hidden', '')
		   
        } else {
            document.getElementById('resultadosContainer').style.display = 'none';
        }
	  })
	  
	  document.getElementById('filterInput').addEventListener('input', handleFilter)
 			  
})

function handleFilter() {
    filterText = document.getElementById('filterInput').value.toLowerCase()
    currentPage = 1
    updateTable()
}

function updateTable() {
	let filteredItems = []
	if(filterText != ''){
	    filteredItems = itemsGlobal.filter(item => 
	        Object.values(item).some(val =>
	            val != null && val.toString().toLowerCase().includes(filterText)
	        )
	    )
		
	}else{
		filteredItems = [...itemsGlobal]
	}
	
	//console.log(filteredItems)
    filteredItems.sort((a, b) => {
        if (a[sortColumn] < b[sortColumn]) return sortDirection === 'asc' ? -1 : 1
        if (a[sortColumn] > b[sortColumn]) return sortDirection === 'asc' ? 1 : -1
        return 0
    })

    //const startIndex = (currentPage - 1) * pageSize
    //const paginatedItems = filteredItems.slice(startIndex, startIndex + pageSize)

    mostrarItems(filteredItems)
    //updatePagination(filteredItems.length)
    updateSummary(filteredItems)
}

function updateSummary(items) {
    const esCentroEspecial = [13, 17].includes(Number(centroTrabajoSelectedId));
    let totalRequerido = 0;
    let totalReportado = 0;
    let summaryHTML = '';

    if (esCentroEspecial) {
        // Para centros especiales, calcular totales directamente de las cantidades sin multiplicar
        totalRequerido = items.reduce((sum, item) => sum + item.cantReq, 0);
        totalReportado = items.reduce((sum, item) => sum + (item.cantReportada || 0), 0);

        const porcentajeAvance = totalRequerido > 0 ? (totalReportado / totalRequerido * 100).toFixed(0) : '0';
        const dashArray = 2 * Math.PI * 45;
        const dashOffset = dashArray * (1 - porcentajeAvance / 100);

        summaryHTML = `
            <div class="row justify-content-center">
                <div class="col-md-4">
                    <div class="card bg-white">
                        <div class="card-body text-center">
                            <h5 class="card-title">Porcentaje de Avance</h5>
                            <svg width="150" height="150" viewBox="0 0 100 100">
                                <circle cx="50" cy="50" r="45" fill="none" stroke="#e6e6e6" stroke-width="10"/>
                                <circle cx="50" cy="50" r="45" fill="none" stroke="#007bff" stroke-width="10"
                                        stroke-dasharray="${dashArray}" stroke-dashoffset="${dashOffset}"
                                        transform="rotate(-90 50 50)"/>
                                <text x="50" y="50" font-size="18" text-anchor="middle" alignment-baseline="middle">
                                    ${porcentajeAvance}%
                                </text>
                                <text x="50" y="70" font-size="12" text-anchor="middle" alignment-baseline="middle">
                                    AVANCE
                                </text>
                            </svg>
                            <p class="mt-3">Total Requerido: ${totalRequerido.toFixed(0)} und</p>
                            <p>Total Reportado: ${totalReportado.toFixed(0)} und</p>
                        </div>
                    </div>
                </div>
            </div>
        `;
    } else {
        // Mantener el comportamiento original para otros centros
        const materiaPrimaSummary = items.reduce((acc, item) => {
            const key = item.materiaPrimaId;
            if (!acc[key]) {
                acc[key] = {
                    id: key,
                    descripcion: item.materiaPrimaDescripcion,
                    cantidadRequerida: 0,
                    cantidadReportada: 0,
                    cantidadPendiente: 0
                };
            }
            const cantidadItem = item.cantReq * item.materiaPrimaCant;
            const cantidadReportada = item.cantReportada * item.materiaPrimaCant;
            acc[key].cantidadRequerida += cantidadItem;
            acc[key].cantidadReportada += cantidadReportada;
            acc[key].cantidadPendiente += Math.max(0, cantidadItem - cantidadReportada);
            return acc;
        }, {});

        summaryHTML = `
            <div class="row">
                <div class="col-md-8">
                    <div class="card bg-white">
                        <div class="card-body">
                            <h5 class="card-title">Resumen de Materia Prima</h5>
                            <div class="table-responsive rounded-3 shadows-sm my-4">
                                <table class="table table-striped table-hover table-sm">
                                    <thead>
                                        <tr>
                                            <th>Cod</th>
                                            <th>Descripción</th>
                                            <th>Cantidad Requerida</th>
                                            <th>Cantidad Reportada</th>
                                            <th>Cantidad Pendiente</th>
                                        </tr>
                                    </thead>
                                    <tbody>
        `;

        for (const [materiaPrimaId, data] of Object.entries(materiaPrimaSummary)) {
            totalRequerido += data.cantidadRequerida;
            totalReportado += data.cantidadReportada;
            summaryHTML += `
                <tr>
                    <td>${data.id}</td>
                    <td>${data.descripcion}</td>
                    <td>${data.cantidadRequerida.toFixed(2)} kg</td>
                    <td>${data.cantidadReportada.toFixed(2)} kg</td>
                    <td>${data.cantidadPendiente.toFixed(2)} kg</td>
                </tr>
            `;
        }

        const porcentajeAvance = totalRequerido > 0 ? (totalReportado / totalRequerido * 100).toFixed(0) : '0';
        const dashArray = 2 * Math.PI * 45;
        const dashOffset = dashArray * (1 - porcentajeAvance / 100);

        summaryHTML += `
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card bg-white">
                        <div class="card-body text-center">
                            <h5 class="card-title">Porcentaje de Avance</h5>
                            <svg width="150" height="150" viewBox="0 0 100 100">
                                <circle cx="50" cy="50" r="45" fill="none" stroke="#e6e6e6" stroke-width="10"/>
                                <circle cx="50" cy="50" r="45" fill="none" stroke="#007bff" stroke-width="10"
                                        stroke-dasharray="${dashArray}" stroke-dashoffset="${dashOffset}"
                                        transform="rotate(-90 50 50)"/>
                                <text x="50" y="50" font-size="18" text-anchor="middle" alignment-baseline="middle">
                                    ${porcentajeAvance}%
                                </text>
                                <text x="50" y="70" font-size="12" text-anchor="middle" alignment-baseline="middle">
                                    AVANCE
                                </text>
                            </svg>
                            <p class="mt-3">Total Requerido: ${totalRequerido.toFixed(2)} kg</p>
                            <p>Total Reportado: ${totalReportado.toFixed(2)} kg</p>
                        </div>
                    </div>
                </div>
            </div>
        `;
    }

    document.getElementById('summaryCard').innerHTML = summaryHTML;
}

async function fetchCentrosT(){
	try{
		const response = await fetch('/centros-trabajo/listar')
		if(!response.ok){
			throw new Error("Error al obtener los Centros de trabajo")
		}
		const data = await response.json()
		return data
	}catch(error){
		console.error("Error al obtener los Centros de trabajo: ", error)
	}
}

async function obtenerOrdenes(idCentroT){
	
	const response = await fetch(`/programacion/centro-trabajo/${idCentroT}/resumen-ops`)
	
	const data = await response.json()
	
	console.log(data)
	return data
	
}

function mostrarItems(items){
	document.getElementById('resultadosContainer').style.display = 'block';
		
	let tbody = document.getElementById("resumen-op")
	tbody.innerHTML = ''
	
	const esCentroEspecial = [13, 17].includes(Number(centroTrabajoSelectedId));
	    
    // Actualizar los encabezados según el centro de trabajo
    const thead = document.querySelector('#resultsTable thead tr');
    if (esCentroEspecial) {
        thead.innerHTML = `
            <th>
                <a data-sort="op">
                    <span>O.P.</span>
                </a>
            </th>
            <th>
                <a>
                    <span>Proyecto</span>
                </a>
            </th>
            <th>
                <a>
                    <span>Descripción</span>
                </a>
            </th>
            <th>
                <a>
                    <span>Cant. Requerida</span>
                </a>
            </th>
            <th>
                <a>
                    <span>Cant. Reportada</span>
                </a>
            </th>
        `;
    } 
	
	items.forEach(item => {
		let row = document.createElement("tr")
		//console.log(item)
		if (esCentroEspecial) {
            // Vista simplificada para centros 13 y 17
            // OP
            let cellOp = document.createElement("td");
            cellOp.textContent = item.op;
            cellOp.classList.add("text-nowrap");
            row.appendChild(cellOp);
            
            // Proyecto
            let cellProyecto = document.createElement("td");
            cellProyecto.textContent = item.un;
            row.appendChild(cellProyecto);
            
            // Descripción
            let cellDescripcion = document.createElement("td");
            cellDescripcion.textContent = item.descripcion;
            row.appendChild(cellDescripcion);
            
            // Cantidad Requerida
            let cellCantReq = document.createElement("td");
            cellCantReq.textContent = item.cantReq.toFixed(0) + ' und';
            cellCantReq.style.textAlign = 'right';
            row.appendChild(cellCantReq);
            
            // Cantidad Reportada
            let cellCantReport = document.createElement("td");
            cellCantReport.textContent = item.cantReportada.toFixed(0) + ' und';
            cellCantReport.style.textAlign = 'right';
            row.appendChild(cellCantReport);
            
        } else {			
			
			let cellOp = document.createElement("td")
			cellOp.textContent = item.op
			cellOp.classList.add("text-nowrap")
			row.appendChild(cellOp)
			
			let cellProyecto = document.createElement("td")
			cellProyecto.textContent = item.un
			row.appendChild(cellProyecto)
			
			let cellDescripcion = document.createElement("td")
			cellDescripcion.textContent = item.descripcion
			row.appendChild(cellDescripcion)
			
			let cellCantidad = document.createElement("td")
			cellCantidad.textContent = item.cantReq
			row.appendChild(cellCantidad)
			
			let cellPesoBrutoUnd = document.createElement("td")
			cellPesoBrutoUnd.textContent = item.materiaPrimaCant
			row.appendChild(cellPesoBrutoUnd)
			
			let cellCodMateriaPrima = document.createElement("td")
			cellCodMateriaPrima.textContent = item.materiaPrimaId
			row.appendChild(cellCodMateriaPrima)
			
			let cellMateriaPrima = document.createElement("td")
			cellMateriaPrima.textContent = item.materiaPrimaDescripcion
			row.appendChild(cellMateriaPrima)		
			
			let cellPesoTotalReq = document.createElement("td")
			cellPesoTotalReq.textContent = (item.cantReq * item.materiaPrimaCant).toFixed(2)
			row.appendChild(cellPesoTotalReq)
			
			let cellCantFinalizada = document.createElement("td")
			cellCantFinalizada.textContent = (item.cantReportada * item.materiaPrimaCant).toFixed(2)
			row.appendChild(cellCantFinalizada)
		}		
		
		tbody.appendChild(row)
	})
	
	//console.log(items)
	
}

function procesarOrdenes(ordenes){
	let items = []
	ordenes.forEach(op => {
		items = [...items, ...procesarElementosOp(op)]
	})
	
	return items
}

function procesarElementosOp(op){
	let items = []
	
	const agregarOActualizarItem = (newItem) => {
        const existingItem = items.find(item => 
            item.descripcion === newItem.descripcion && 
            item.materiaPrimaId === newItem.materiaPrimaId
        );
        
        if (existingItem) {
            existingItem.cantReq += newItem.cantReq;
            existingItem.cantReportada += newItem.cantReportada;
        } else {
            items.push(newItem);
        }
    };
		
	op.items.forEach(elemento => {
		if(centroTrabajoSelectedId == elemento.itemCentroTId){
			if ([12, 17].includes(Number(centroTrabajoSelectedId))) {
                agregarOActualizarItem({
                    op: op.op,
                    un: op.un,
                    zona: op.zona,
                    itemOpId: elemento.itemOpId,
                    itemId: elemento.itemId,
                    descripcion: elemento.itemDescripcion,
                    cantReq: elemento.cantReq,
                    cantReportada: elemento.cantReportadaPieza ?? 0,
                    itemLongitud: elemento.itemLongitud ?? 0,
					itemPeso: elemento.itemPeso ?? 0,
                    materiaPrimaId: elemento.materiaPrimaId,
                    materiaPrimaDescripcion: "PIEZA ELABORADA",
                    materiaPrimaCant: 1, // Para piezas elaboradas, la cantidad es 1
                    isParte: false
                });
            } else {
                // Para otros centros, mantener el comportamiento original
                agregarOActualizarItem({
                    op: op.op,
                    un: op.un,
                    zona: op.zona,
                    itemOpId: elemento.itemOpId,
                    itemId: elemento.itemId,
                    descripcion: elemento.itemDescripcion,
                    cantReq: elemento.cantReq,
                    cantReportada: elemento.cantReportadaPieza ?? 0,
                    itemLongitud: elemento.itemLongitud ?? 0,
                    materiaPrimaId: elemento.materiaPrimaId,
                    materiaPrimaDescripcion: elemento.materiaPrimaDescripcion,
                    materiaPrimaCant: elemento.materiaPrimaCant,
                    isParte: false
                });
            }
		}
		if(elemento.componentes && elemento.componentes.length > 0){
			elemento.componentes.forEach(componente => {
                if (centroTrabajoSelectedId == componente.materialCentroTId) {
                    if ([12, 17].includes(Number(centroTrabajoSelectedId))) {
                        agregarOActualizarItem({
                            op: op.op,
                            un: op.un,
                            zona: op.zona,
                            itemOpId: elemento.itemOpId,
                            itemId: componente.materialId,
                            descripcion: componente.materialDescripcion,
                            cantReq: elemento.cantReq * componente.materialCant,
                            cantReportada: componente.cantReportadaMaterial ?? 0,
                            itemLongitud: componente.itemLongitud ?? 0,
							itemPeso: elemento.itemPeso ?? 0,
                            materiaPrimaId: componente.materialId,
                            materiaPrimaDescripcion: "PIEZA ELABORADA",
                            materiaPrimaCant: 1,
                            isParte: true
                        });
                    } else {
                        agregarOActualizarItem({
                            op: op.op,
                            un: op.un,
                            zona: op.zona,
                            itemOpId: elemento.itemOpId,
                            itemId: componente.materialId,
                            descripcion: componente.materialDescripcion,
                            cantReq: elemento.cantReq * componente.materialCant,
                            cantReportada: componente.cantReportadaMaterial ?? 0,
                            itemLongitud: componente.itemLongitud ?? 0,
                            materiaPrimaId: componente.materiaPrimaId,
                            materiaPrimaDescripcion: componente.materiaPrimaDescripcion,
                            materiaPrimaCant: componente.materiaPrimaCant,
                            isParte: true
                        });
                    }
                }
            });
		}
		
	})
	
	return items
}