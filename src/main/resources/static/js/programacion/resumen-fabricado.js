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
	const centrosTrabajoFilter = [3, 4, 5, 6, 7, 8, 9, 17];

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
		//document.getElementById('filtroForm').reset();
		document.getElementById('filterInput').value = ''
		filterText = ''
		centroTrabajoSelectedId = this.value;
        if (centroTrabajoSelectedId) {			
            const ordenes = await obtenerOrdenes(centroTrabajoSelectedId);
			ordenes.sort((a, b) => {
			    return b.idOpIntegrapps - a.idOpIntegrapps;
			  });
		   itemsGlobal = procesarOrdenes(ordenes)
           itemsOriginales = [...itemsGlobal]
           updateTable()
		   //mostrarItems(ordenes)
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
	console.log(filterText)
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
	
	console.log(filteredItems)
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
    const materiaPrimaSummary = items.reduce((acc, item) => {
        const key = item.materiaPrimaId; // Usamos el ID como clave para evitar duplicados
        if (!acc[key]) {
            acc[key] = {
				id: key,
                descripcion: item.materiaPrimaDescripcion,
                cantidadRequerida: 0,
                cantidadPendiente: 0
            };
        }
        const cantidadItem = item.cantReq * item.materiaPrimaCant;
        acc[key].cantidadRequerida += cantidadItem;
        acc[key].cantidadPendiente += Math.max(0, cantidadItem - (item.cantReportada * item.materiaPrimaCant));
        return acc;
    }, {});

    let summaryHTML = `
        <div class="card bg-white w-75">
            <h5 class="card-title">Resumen de Materia Prima</h5>
            <div class="card-body">
                <table class="table table-sm">
                    <thead>
                        <tr>
							<th>Cod</th>
                            <th>Descripción</th>
                            <th>Cantidad Requerida</th>
                            <th>Cantidad Pendiente</th>
                        </tr>
                    </thead>
                    <tbody>
    `;

    for (const [materiaPrimaId, data] of Object.entries(materiaPrimaSummary)) {
        summaryHTML += `
            <tr>
				<td>${data.id}</td>
                <td>${data.descripcion}</td>
                <td>${data.cantidadRequerida.toFixed(2)} kg</td>
                <td>${data.cantidadPendiente.toFixed(2)} kg</td>
            </tr>
        `;
    }

    summaryHTML += `
                    </tbody>
                </table>
            </div>
        </div>
    `;

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
	
	//const items = procesarOrdenes(ordenes)
	
	let tbody = document.getElementById("resumen-op")
	tbody.innerHTML = ''
	
	items.forEach(item => {
		let row = document.createElement("tr")
		
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
		
		
		tbody.appendChild(row)
	})
	
	console.log(items)
	
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
	op.items.forEach(elemento => {
		
		if(centroTrabajoSelectedId == elemento.itemCentroTId){
			const existingItem = items.find(item => item.descripcion === elemento.itemDescripcion)			            
            if (existingItem) {
                existingItem.cantReq += elemento.cantReq
				existingItem.cantReportada += elemento.cantReportadaPieza
            } else {
				const item = {
					"op": op.op,
					"un": op.un,
					"zona": op.zona,
					"itemOpId": elemento.itemOpId,
					"itemId": elemento.itemId,
					"descripcion": elemento.itemDescripcion,
					"cantReq": elemento.cantReq,
					"cantReportada": elemento.cantReportadaPieza ?? 0,
					"itemLongitud": elemento.itemLongitud ?? 0,
					"materiaPrimaId": elemento.materiaPrimaId,
					"materiaPrimaDescripcion": elemento.materiaPrimaDescripcion,
					"materiaPrimaCant": elemento.materiaPrimaCant,
					"isParte": false					
				}
				items.push(item)
			}
		}
		if(elemento.componentes.length > 0){
			const componentes = elemento.componentes
			componentes.forEach(componente => {
				if(centroTrabajoSelectedId == componente.materialCentroTId){
					const existingItem = items.find(item => item.descripcion === componente.materialDescripcion)			            
		            if (existingItem) {
		                existingItem.cantReq += elemento.cantReq * componente.materialCant
						existingItem.cantReportada += componente.cantReportadaMaterial
		            } else {
						const item = {
							"op": op.op,
							"un": op.un,
							"zona": op.zona,
							"itemOpId": elemento.itemOpId,
							"itemId": componente.materialId,
							"descripcion": componente.materialDescripcion,
							"cantReq": elemento.cantReq * componente.materialCant,
							"cantReportada": componente.cantReportadaMaterial ?? 0,
							"itemLongitud": componente.itemLongitud ?? 0,
							"materiaPrimaId": componente.materiaPrimaId,
							"materiaPrimaDescripcion": componente.materiaPrimaDescripcion,
							"materiaPrimaCant": componente.materiaPrimaCant,
							"isParte": true					
						}
						items.push(item)
					}
				}				
			})
		}
		
	})
	
	return items
}