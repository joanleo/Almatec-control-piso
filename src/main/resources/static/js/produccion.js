let turnos
let turnoSelected
let centrosTrabajo
let centroTSelected
let configProceso

function toggleTable() {
  let table = document.getElementById("table-produccion")
  let button = document.querySelector(".button-check-table")

  if (table && button) {
    toggleVisibility(table, button)
  }
}

function toggleVisibility(element, button) {
  if (element.classList.contains("table-production")) {
    element.classList.remove("table-production")
    button.setAttribute("aria-expanded", "false")
    button.classList.remove("show")
  } else {
    element.classList.add("table-production")
    button.setAttribute("aria-expanded", "true")
    button.classList.add("show")
  }
}

let porcentaje
let progressBar = document.getElementById('progress-bar')
let improductivoStr = document.querySelector("#improductivo").textContent
let productivoStr = document.querySelector("#productivo").textContent

let improductivo = parseFloat(improductivoStr)
let productivo = parseFloat(productivoStr)

if(improductivo + productivo !== 0){
	porcentaje = Math.ceil((1- improductivo / (improductivo + productivo)) * 100)
}
progressBar.style.width = porcentaje + '%'
progressBar.textContent = porcentaje + '%'


function saveToLocalStorage() {
    localStorage.setItem('turnos', JSON.stringify(turnos))
    localStorage.setItem('turnoSelected', JSON.stringify(turnoSelected))
    localStorage.setItem('centrosTrabajo', JSON.stringify(centrosTrabajo))
    localStorage.setItem('centroTSelected', JSON.stringify(centroTSelected))
    localStorage.setItem('configProceso', JSON.stringify(configProceso))
}

function loadFromLocalStorage() {
    turnos = JSON.parse(localStorage.getItem('turnos')) || []
    turnoSelected = JSON.parse(localStorage.getItem('turnoSelected')) || null
    centrosTrabajo = JSON.parse(localStorage.getItem('centrosTrabajo')) || []
    centroTSelected = JSON.parse(localStorage.getItem('centroTSelected')) || null
    configProceso = JSON.parse(localStorage.getItem('configProceso')) || null
}

async function fetchTurnos(){
	try{
		const response = await fetch('/turnos')
		if(!response.ok){
			console.log(response.status)
			throw new Error("Error al obtener los turnos")
		}
		const data = await response.json()
		return data
	}catch(error){
		console.error("Error al obtener los turnos: ", error)
	}
}

async function fetchCentrosT(){
	try{
		const response = await fetch('/centros-trabajo/listar')
		if(!response.ok){
			console.log(response.status)
			throw new Error("Error al obtener los Centros de trabajo")
		}
		const data = await response.json()
		return data
	}catch(error){
		console.error("Error al obtener los Centros de trabajo: ", error)
	}
}

async function obtenerOperario(cedula){
	try{
		const response = await fetch('/operarios/' + cedula)
		if(!response.ok){
			const dato = await response.json()
			mostrarAlert(dato.mensaje, "danger")
			throw new Error(dato.mensaje)
		}
		const data = await response.json()
		return data
	}
	catch(error){
		console.error("Ocurrio un problema al tratar de obtener el operario: ", error)
	}
}

async function obtenerOperariosCT(){
	try{
		
		const response = await fetch(`/centros-trabajo/${centroTSelected.id}/${configProceso.id}/operarios`)
		if(!response.ok){
			throw new Error("Error al tratar de obtener operarios en centro de trabajo")
		}
		const data = response.json()
		return data
	}catch(error){
		console.error("Error al tratar de obtener operarios en centro de trabajo: ", error)
	}
} 

async function fillTurnosDropdown() {
    try {
        turnos = await fetchTurnos()
        const selectElement = document.getElementById('turno')

        turnos.forEach(turno => {
            const option = document.createElement('option')
            option.value = turno.descripcion
            option.text = turno.descripcion
            option.setAttribute('id',turno.id)
            selectElement.add(option)
        })
    } catch (error) {
        console.error("Error al llenar el desplegable de turnos: ", error)
    }
}

async function calculateTurno() {
	const horaActual = new Date().toLocaleTimeString('en-US',{ hour12: false, hour: '2-digit', minute: '2-digit' })
	for (const turno of turnos) {
		const inicioTurno = turno.inicio;
        const finTurno = turno.fin;

        // Convertir la hora simulada y los límites del turno a objetos Date
        let horaSimuladaDate = new Date(`2000-01-01T${horaActual}`)
        let inicioTurnoDate = new Date(`2000-01-01T${inicioTurno}`)
        let finTurnoDate = new Date(`2000-01-01T${finTurno}`)
        
        if (inicioTurnoDate > finTurnoDate) {
			horaSimuladaDate = new Date(`2000-01-02T${horaActual}`)
            finTurnoDate = new Date(`2000-01-02T${finTurno}`)
        }


        if (horaSimuladaDate >= inicioTurnoDate && horaSimuladaDate <= finTurnoDate) {
            return turno
        }
    }
}
	
document.addEventListener('DOMContentLoaded', function () {
	(async ()=> {
		loadFromLocalStorage()
		await fillTurnosDropdown()
		centrosTrabajo = await fetchCentrosT()
	    const turnosElement = document.getElementById('turno')
	    const turnoCalculated = await calculateTurno()
	    turnosElement.value = turnoCalculated.descripcion
	    turnoSelected = turnoCalculated
	    if(centroTSelected !== null){
			const title = document.querySelector("#title-ct")
			title.textContent = centroTSelected.nombre
		}
	    mostrarOperariosCT()
    })()
})

document.getElementById('codigo').addEventListener('change', function () {
	(async()=>{
	    await validateCode()		
	})()
})

document.getElementById('turno').addEventListener('change', function () {
	console.log('Turno changed!')
	turnos.forEach(turno=>{
		console.log(this.options[this.selectedIndex].id)
		if(turno.id == this.options[this.selectedIndex].id){
			turnoSelected = turno
		}
	})
    console.log("Turno seleccionado:", turnoSelected)
    
    saveToLocalStorage()
})

async function handleKeyPress(event) {
    if (event.key === 'Enter') {
        await validateCode()
        document.getElementById('codigo').value = ''
    }
}

async function validateCode() {
	const codigoElement = document.querySelector("#codigo")
	const codigo = codigoElement.value
	const prefix = codigo.substring(0, 3)
	switch (prefix) {
		case "CTR":
		case "ÑCT":
			configuraCentroTrabajo(codigo)
			break
		case "USU":
			registraUsuarioCT(codigo)
			break
		case "EVE":
			validaEvento()
			break
		case "IDU":
			asignaPiezaOperario()
			break
		case "PAR":
			regActualizarParada(codigo)
			break
		default:
			console.log("La opcion non existe")
			break
	}		
}

async function configuraCentroTrabajo(codigo){
	console.log("configura centro de trabajo para el turno: ", turnoSelected.descripcion)
	const title = document.querySelector("#title-ct")
	for(const ct of centrosTrabajo){
		if(codigo === ct.codigoBarraHum || codigo === ct){
			centroTSelected = ct
			console.log("CT selected!")
			console.log(centroTSelected.nombre)
			title.textContent = ct.nombre
			try{
				const responseConfiProceso = await fetch(`/config-procesos/centro-trabajo/${centroTSelected.id}/turno/${turnoSelected.id}`)
				if(!responseConfiProceso.ok){
					console.log(responseConfiProceso.status)
					throw new Error("Error al tratar de configurar el proceso el en centro de trabajo ", centroTSelected.nombre)
				}
				console.log("ESta pasando")
				configProceso = await responseConfiProceso.json()
				mostrarAlert(`Centro de trabajo ${centroTSelected.nombre} registrado exitosamente`, "success")
				saveToLocalStorage()
				return	
			}catch(error){
				console.log("Error al tratar de configurar el proceso el en centro de trabajo ", error)
			}
			document.querySelector("#codigo").value = ""
		}
	}
	mostrarAlert("El centro de trabajo no existe.", "danger")
}

function mostrarAlert(mensaje, tipo){
	const alertElement = document.createElement('div')
    	alertElement.className = `alert alert-${tipo} alert-dismissible fade show fixed-top`
    	alertElement.role = 'alert'
        alertElement.innerHTML = `
        <strong>${mensaje}</strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>`

        document.body.appendChild(alertElement)
        
        setTimeout(() => alertElement.remove(), 5000)
}

async function registraUsuarioCT(codigo){
	if(centroTSelected === null){
		console.log(centroTSelected)
		mostrarAlert("Debe seleccionar un centro de trabajo", "warning")
        return
	}
	try{
		const operario = await obtenerOperario(codigo.substring(3))
		console.log(`Registra o retira al operario ${operario.nombre} usuario del centro de trabajo ${centroTSelected.nombre}`)
		const operarioDTO = {
			"idOperario": operario.id,
			"idCentroTrabajo": centroTSelected.id,
			"idConfigProceso": configProceso.id
		}
		const responseRegistroOperario = await fetch(`/centros-trabajo/${centroTSelected.id}/agregar-retirar-operario`,{
												method: 'POST',
												headers: {
											      'Content-Type': 'application/json'
											    },
												body: JSON.stringify(operarioDTO)
												})
		if(!responseRegistroOperario.ok){
			console.log(responseRegistroOperario)
		}
		const data = await responseRegistroOperario.text()
		console.log(data)
		if(data.includes("registrado")){
			mostrarAlert(`Operario ${operario.nombre} ${data}.`, "warning")
		}else{
			mostrarAlert(`Operario ${operario.nombre} ${data}.`, "success")			
		}
		mostrarOperariosCT()
	}catch(error){
		console.log("Error al tratar de realizar la operacion ", error)
	}
	
}

async function validaEvento(){
	console.log("Valida un evento")
}

async function obtenerOpCentroT(){
	try{
		const response = await fetch(`/centros-trabajo/${centroTSelected.id}/ordenes-produccion`)
		if(!response.ok){
			throw new Error(response)
		}
		const data = await response.json()
		console.log("respuesta ops CT: ",data)
		return data
	}catch(error){
		console.log("Error al tratar de obtener oprerarion en centro de trabajo", error)
	}
		
}

function cargarListadoItems(){
	let selectOpsCt = document.getElementById('op-selected')
    let opSelected = selectOpsCt.value
    let listadoItemsTbody = document.getElementById('listado-items')
    let opElement = opSelected.split("-")
    listadoItemsTbody.innerHTML = ''
	let ops = opsCt.filter(item => item.numOp === parseInt(opElement[1], 10) && item.tipoOp === opElement[0])
    console.log(ops)
    if (ops) {
        ops.forEach(function(op, index) {
			for(const item of op.items){
		        let row = document.createElement('tr')
	            let cellNum = document.createElement('td')
	            cellNum.textContent = index + 1
	            row.appendChild(cellNum)
				
	            let cellName = document.createElement('td')
	            let componentes = item.componentes
	            let descripcion
	            let isComponente = false
	            let cantListaMaterial
	            let longitud
	            for(const componente of componentes){
					if(centroTSelected.id === componente.idCentroTrabajoPerfil){
						descripcion = componente.descripcionPerfil
						isComponente = true
						cantListaMaterial = componente.cantListaMateriales
						longitud = componente.longitud
					}
				}
				console.log(isComponente, cantListaMaterial)
				
				descripcion = descripcion ?? item.descripcion
	            cellName.textContent = descripcion 
	            row.appendChild(cellName)
	            
	            let cellLongitud = document.createElement('td')
				cellLongitud.textContent = isComponente? longitud: ''
				row.append(cellLongitud)

	            let cellCliente = document.createElement('td')
	            cellCliente.textContent = op.cliente
	            row.appendChild(cellCliente)
	            
	            let cellProyecto = document.createElement('td')
	            cellProyecto.textContent = op.proyecto
	            row.appendChild(cellProyecto)

	            let cellCantSol = document.createElement('td')
	            cellCantSol.textContent = isComponente ? item.cant * cantListaMaterial: item.cant
	            row.appendChild(cellCantSol)

	            row.addEventListener('click', function(){
					confirmModal(item, isComponente)})
	                        
	            listadoItemsTbody.appendChild(row)
			}            
        })
    } else {
        let row = document.createElement('tr')
        let cell = document.createElement('td')
        cell.textContent = 'No hay items disponibles en esta op.'
        row.appendChild(cell)
        listadoItemsTbody.appendChild(row)
    }
}

function crearSelectCT(opsCt){
	let opCtElement = document.getElementById("ops-ct")
	let labelElement = document.createElement("label")
	labelElement.textContent = "Seleccione OP: "
	labelElement.setAttribute("for", "op-selected")
	opCtElement.appendChild(labelElement)
	
	let selectElement = document.createElement("select")
	selectElement.setAttribute("id", "op-selected")
	selectElement.addEventListener("change", function() {
        cargarListadoItems()
    })
	let uniqueOps = new Set()
	if (Array.isArray(opsCt)) {
        opsCt.forEach(function (op) {
			let uniqueKey = op.tipoOp + op.numOp
			if (!uniqueOps.has(uniqueKey)) {
	            const optionElement = document.createElement("option")
	            selectElement.style.margin = "0 0 0 1rem"
	            optionElement.value = op.tipoOp +"-"+ op.numOp
	            optionElement.text = op.tipoOp +"-"+ op.numOp
	            selectElement.appendChild(optionElement)
			}
			uniqueOps.add(uniqueKey)
        })

        opCtElement.appendChild(selectElement)
    } else {
        console.error("La variable opsCt no es un array.")
    }
}

function crearSelectOperariosCt(operariosCt){
	let operariosCtElement = document.getElementById("operarios-ct")
	let labelElement = document.createElement("label")
	labelElement.textContent = "Seleccione operario: "
	labelElement.setAttribute("for", "operario-selected")
	operariosCtElement.appendChild(labelElement)
	
	let selectElement = document.createElement("select")
	selectElement.setAttribute("id", "operario-selected")
	selectElement.style.margin = "0 0 0 1rem"
	
	operariosCt.forEach(function (operario){
		const optionElement = document.createElement("option")
		optionElement.value = operario.id
		optionElement.text = operario.nombre
		selectElement.appendChild(optionElement)
	})
	operariosCtElement.appendChild(selectElement)
}

function limpiarElementos() {
    let selectElement = document.getElementById('ops-ct')
    if (selectElement) {
        selectElement.innerHTML = ''
    }
    let operariosCtElement = document.getElementById('operarios-ct')
    if (operariosCtElement) {
        operariosCtElement.innerHTML = ''
    }
}

let opsCt

async function asignaPiezaOperario(){
	const modalAsignarPieza = new bootstrap.Modal('#asignarPieza')
	console.log("traer ordenes de produccion en este ct")
	opsCt = await obtenerOpCentroT()
    let operariosCt = await obtenerOperariosCT()
	
    crearSelectCT(opsCt)
    crearSelectOperariosCt(operariosCt)
    console.log("Items de la OP seleccionada")
    cargarListadoItems()
    modalAsignarPieza.show()
    console.log("Asigna una pieza a un operario")
    modalAsignarPieza._element.addEventListener('hidden.bs.modal', function () {
    limpiarElementos()
})
}

let itemAgregar
function confirmModal(item, isComponente) {
	const operarioSelected = document.getElementById("operario-selected").textContent
	console.log(operarioSelected)
	console.log(item)
	const confirm_modal = new bootstrap.Modal('#modal-confirma-pieza', {
		  keyboard: false
		})
	const confirm_modal_body = confirm_modal._element.querySelector(".modal-confirm-body")
	let items
	if(isComponente){
		for(const componente of item.componentes){
			items += componente.descripcionPerfil +" ,"
		}
		confirm_modal_body.textContent = `Esta seguro que desea asignar la pieza ${items} al operario ${operarioSelected}.`
		itemAgregar = item
		confirm_modal.show()
	}
	confirm_modal_body.textContent = `Esta seguro que desea asignar la pieza ${item} al operario ${operarioSelected}.`
	itemAgregar = item
	confirm_modal.show()	
}

function agregarPiezaOperario(){
	
}

async function obtenerParadas(){
	
	try{
		const response = await fetch(`/paradas`)
		if(!response.ok){
			const error = response.json()
			throw new Error(error)
		}
		const data = response.json()
		return data		
	}catch(error){
		console.error("Ocurrio un error al tratar de obtener las paradas ", error)
	}
	
}

async function regActualizarParada(codigo){
	console.log("Registra una parada, id de la parada: ", codigo)
	const paradas = await obtenerParadas()
	console.log(paradas)
}

function clearTable(table) {
    while (table.rows.length > 1) {
        table.deleteRow(1)
    }
}

function addRowToTable(table, operario, index) {
    // Añade una nueva fila a la tabla
    let newRow = table.insertRow()

    // Agrega celdas a la fila con la información del operario
    let cells = [
        index,
        operario.nombre,
        operario.turno,
        operario.inicio,
        operario.productivo,
        operario.improductivo,
        operario.final,
        operario.disponible,
        operario.estado
    ]

    cells.forEach((cellContent, cellIndex) => {
        let cell = newRow.insertCell(cellIndex)
        cell.textContent = cellContent
    })
}

async function obtenerTiemposOperariosProceso(){
	try{
		const response = await fetch(`/centros-trabajo/${configProceso.id}/tiempos_operarios`)
		if(!response.ok){
			const error = await response.json()
			throw new Error(error)
		}
		return await response.json()
	}catch(error){
		console.error(error)
	}
	
}

async function mostrarOperariosCT(){
	if(centroTSelected !== null && configProceso !== null){
		let operariosCT = await obtenerOperariosCT()
		const tiemposOperarios = await obtenerTiemposOperariosProceso()
		console.log(operariosCT)
		console.log(tiemposOperarios)
		
		let table = document.querySelector("#table-produccion")
		//clearTable(table)
	}
}

