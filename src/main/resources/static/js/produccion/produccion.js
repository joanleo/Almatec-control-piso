let turnos
let turnoSelected
let centrosTrabajo
let centroTSelected
let configProceso
let centrosTrabajoPrioridad
let mostrarPrioridad

let spinner = document.getElementById("spinner")

function toggleTable() {
  let table = document.getElementById("productividad-operario")
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

/*let porcentaje
let progressBar = document.getElementById('progress-bar')
let improductivoStr = document.querySelector("#improductivo").textContent
let productivoStr = document.querySelector("#productivo").textContent

let improductivo = parseFloat(improductivoStr)
let productivo = parseFloat(productivoStr)

if(improductivo + productivo !== 0){
	porcentaje = Math.ceil((1- improductivo / (improductivo + productivo)) * 100)
}
progressBar.style.width = porcentaje + '%'
progressBar.textContent = porcentaje + '%'*/

async function actualizarTablasConTiemposOperarios() {
    const tiemposOperarios = await obtenerTiemposOperariosProceso();
    llenarTablaProductividadOperario(tiemposOperarios)
}

actualizarTablasConTiemposOperarios()

async function llenarTablaProductividadOperario(tiemposOperarios){
	if(centroTSelected != null){
		//const tiemposOperarios = await obtenerTiemposOperariosProceso()
		let tBodyProductividad = document.getElementById("body-table-productividad-operario")
		tBodyProductividad.innerHTML = ""
		tiemposOperarios.forEach(operario => {
			let row = document.createElement('tr')
	        let cellNombre = document.createElement('td')
	        cellNombre.textContent = operario.nombreOperario
	        row.appendChild(cellNombre)
	        
	        let cellTurno = document.createElement('td')
	        cellTurno.textContent = operario.turno
	        row.appendChild(cellTurno)
	        
	        let cellInicio = document.createElement('td')
	        cellInicio.textContent = formatarFechaHora(operario.fechaInicioTurno)
	        row.appendChild(cellInicio)
	        
	        let cellPruductivo = document.createElement('td')
	        cellPruductivo.textContent = formatearTiempo(parseFloat(operario.productivo))
	        row.appendChild(cellPruductivo)
	        const productivo = parseFloat(operario.productivo)
	        
	        let cellImproductivo = document.createElement('td')
	        cellImproductivo.textContent = formatearTiempo(parseFloat(operario.improductivo))
	        row.appendChild(cellImproductivo)
	        const improductivo = parseFloat(operario.improductivo)
	        
	        let cellFinal = document.createElement('td')
	        cellFinal.textContent = formatarFechaHora(operario.fechaInicioTurno)
	        row.appendChild(cellFinal)
	        
	        let porcentaje
	        if(improductivo + productivo !== 0){
				porcentaje = Math.ceil((1- improductivo / (improductivo + productivo)) * 100)
			}
			porcentaje = porcentaje ?? 100
			let cellProgressBar = document.createElement('td')
			let containerProgressBar = document.createElement("div")
			containerProgressBar.classList.add("progress-bar-container")
			let progressBar = document.createElement("div")
			progressBar.classList.add("progress-bar")
			progressBar.style.width = porcentaje + '%'
			progressBar.textContent = porcentaje + '%'
			containerProgressBar.append(progressBar)
			cellProgressBar.appendChild(containerProgressBar)
	        row.appendChild(cellProgressBar)
	        
	        let cellEstado = document.createElement('td')
	        cellEstado.textContent = operario.isActivo ? "Activo": "Inactivo"
	        row.appendChild(cellEstado)
	        
	        tBodyProductividad.appendChild(row)		
		})		
	}
	
}

function formatearTiempo(segundos) {
    const horas = Math.floor(segundos / 3600);
    const minutos = Math.floor((segundos % 3600) / 60);
    return `${String(horas).padStart(2, '0')}:${String(minutos).padStart(2, '0')}`;
}

function formatarFechaHora(fecha) {
    const fechaObj = new Date(fecha);
    const horas = String(fechaObj.getHours()).padStart(2, '0');
    const minutos = String(fechaObj.getMinutes()).padStart(2, '0');
    return `${horas}:${minutos}`;
}
async function obtenerPiezasCtProceso(){
	if(configProceso != null){
		try{
			const response = await fetch(`/centros-trabajo/${centroTSelected.id}/${configProceso.id}/piezas-operarios-ct-proceso`)
			if(!response.ok){
				console.error("Error en la solicitud:", response.statusText);
				throw new Error("Error en la asignacion de pieza")
			}
			const data = await response.json()
			return data
			
		}catch (error){
			console.log(error)
		}			
	}
}

async function llenarTablaPiezasOperario(){
	if(centroTSelected != null){
		let piezasCT = await obtenerPiezasCtProceso()
		let tBodyProductividad = document.getElementById("body-table-piezas-proceso")
		tBodyProductividad.innerHTML = ""
		console.log("Piezas en el ct en proceso: ", piezasCT)
		piezasCT.forEach((pieza, index) => {
			let row = document.createElement('tr')
	        
	        let cellIndex = document.createElement('td')
	        cellIndex.textContent = index + 1
	        row.appendChild(cellIndex)
	        
	        let cellOp = document.createElement('td')
	        cellOp.textContent = pieza.tipoOp + "-" + pieza.numOp
			cellOp.classList.add('text-nowrap')
	        row.appendChild(cellOp)
	        
	        /*const itemProceso = pieza.idItemFab?? pieza.idParte
	        let cellNumItem = document.createElement('td')
	        cellNumItem.textContent = pieza.idItem + "-" + itemProceso
	        row.appendChild(cellNumItem)*/
	        
	        let cellcliente = document.createElement('td')
	        cellcliente.textContent = pieza.cliente
	        row.appendChild(cellcliente)
	        
	        let cellProyecto = document.createElement('td')
	        cellProyecto.textContent = pieza.co
	        row.appendChild(cellProyecto)
	        
	        let celldescripcion = document.createElement('td')
	        celldescripcion.textContent = pieza.descripcionItem
	        row.appendChild(celldescripcion)
	        
	        let cellTiempo = document.createElement('td')
	        //cellTiempo.textContent = formatearTiempo(parseFloat(pieza.tiempoTrancurrido))
	        cellTiempo.textContent = pieza.cantReq - pieza.cantFabricada
	        row.appendChild(cellTiempo)
	        
	        let cellOperario = document.createElement('td')
	        cellOperario.textContent = pieza.nombreOperario
	        row.appendChild(cellOperario)
	        
	        /*let cellStdr = document.createElement('td')
	        cellStdr.textContent = pieza.tiempoStd
	        row.appendChild(cellStdr)*/
	        
	        let cellEstado = document.createElement('td')
	        cellEstado.textContent = pieza.isActivo ? "Activo": "Inactivo"
	        row.appendChild(cellEstado)
	        
	        let cellPlano = document.createElement('td')
	        if (pieza.plano) {
			    let linkPlano = document.createElement('a');
			    linkPlano.textContent = "Ver plano";
			    linkPlano.href = "#"; // Puedes establecer la URL adecuada si tienes una disponible
			    linkPlano.addEventListener("click", function() {
			        verPdf(pieza.plano);
			    });
			    cellPlano.appendChild(linkPlano);
			} else {
			    cellPlano.textContent = "Sin plano";
			}
			row.appendChild(cellPlano);
	        
	        tBodyProductividad.appendChild(row)
	    })
		/*if(ops.length == 0) {
			console.log("No tiene piezas en el ct en proceso")
			mostrarAlert("No tiene piezas en el ct en proceso", "warning")
		}else{
		}*/		
	}
} 

function verPdf(nombreDelArchivo) {
    window.open(`http://10.75.98.3:8090/centros-trabajo/descargar-pdf/${nombreDelArchivo}.pdf`, '_blank');
}


async function mostrarOperariosCT(){
	if(centroTSelected !== null && configProceso !== null){
		let operariosCT = await obtenerOperariosCT()
		const tiemposOperarios = await obtenerTiemposOperariosProceso()
		console.log("Operario en centro de trabajo: ", operariosCT)
		console.log("Tiempos operario ct: ", tiemposOperarios)
		
	}
}

function saveToLocalStorage() {
    localStorage.setItem('turnos', JSON.stringify(turnos))
    localStorage.setItem('turnoSelected', JSON.stringify(turnoSelected))
    localStorage.setItem('centrosTrabajo', JSON.stringify(centrosTrabajo))
    localStorage.setItem('centroTSelected', JSON.stringify(centroTSelected))
    localStorage.setItem('configProceso', JSON.stringify(configProceso))
    localStorage.setItem('centrosTrabajoPrioridad', JSON.stringify([3,4,5,6,7,8,9]))
}

function loadFromLocalStorage() {
    turnos = JSON.parse(localStorage.getItem('turnos')) || []
    turnoSelected = JSON.parse(localStorage.getItem('turnoSelected')) || null
    centrosTrabajo = JSON.parse(localStorage.getItem('centrosTrabajo')) || []
    centroTSelected = JSON.parse(localStorage.getItem('centroTSelected')) || null
    configProceso = JSON.parse(localStorage.getItem('configProceso')) || null
    centrosTrabajoPrioridad = JSON.parse(localStorage.getItem('centrosTrabajoPrioridad')) || []
}

async function fetchTurnos(){
	try{
		const response = await fetch('/turnos')
		if(!response.ok){
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
		selectElement.innerHTML = ''
		
		const defaultOption = document.createElement('option')
		defaultOption.value = ''
        defaultOption.text = 'Selecciona un turno'
        selectElement.add(defaultOption)
        
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
	    /*const turnosElement = document.getElementById('turno')
	    const turnoCalculated = await calculateTurno()
	    turnosElement.value = turnoCalculated.descripcion
	    turnoSelected = turnoCalculated*/
	    if(centroTSelected !== null){
			
			document.getElementById('title-nombre-ct').textContent = centroTSelected.nombre
			
			document.getElementById('title-turno').textContent = "Turno actual " + turnoSelected.descripcion
		}
	    mostrarOperariosCT()
	    //llenarTablaProductividadOperario()
	    actualizarTablasConTiemposOperarios()
	    llenarTablaPiezasOperario()
    })()
})

document.getElementById('codigo').addEventListener('change', function () {
	(async()=>{
	    await validateCode()		
	})()
})

document.getElementById('turno').addEventListener('change', function () {
	console.log('Turno changed!')
	if (this.value === '') { 
        turnoSelected = ''
    } else {
        turnos.forEach(turno => {
            if (turno.id == this.options[this.selectedIndex].id) {
                turnoSelected = turno;
            }
        });
    }
    
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
			document.getElementById("turno").value = ""
			break
		case "USU":
			registraUsuarioCT(codigo)
			break
		case "EVE":
			validaEvento(codigo)
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
	let title = document.querySelector("#title-nombre-ct")
	let turnoTitle = document.getElementById('title-turno')
	const turnosElement = document.getElementById('turno')
	if(turnosElement.value == ""){
		mostrarAlert("Debe seleccionar un turno.","danger")
		return
	}
	for(const ct of centrosTrabajo){
		if(codigo === ct.codigoBarraHum || codigo === ct){
			centroTSelected = ct
			title.textContent = ct.nombre
			turnoTitle.textContent = "Turno actual " + turnoSelected.descripcion
			try{
				const responseConfiProceso = await fetch(`/config-procesos/centro-trabajo/${centroTSelected.id}/turno/${turnoSelected.id}`)
				if(!responseConfiProceso.ok){
					throw new Error("Error al tratar de configurar el proceso el en centro de trabajo ", centroTSelected.nombre)
				}
				configProceso = await responseConfiProceso.json()
				mostrarAlert(`Centro de trabajo ${centroTSelected.nombre} registrado exitosamente`, "success")
				saveToLocalStorage()
				llenarTablaPiezasOperario()
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
    	alertElement.style.zIndex = '10000'
        alertElement.innerHTML = `
        <strong>${mensaje}</strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>`

        document.body.appendChild(alertElement)
        
        setTimeout(() => alertElement.remove(), 5000)
}

async function registraUsuarioCT(codigo){
	if(centroTSelected === null){
		mostrarAlert("Debe seleccionar un centro de trabajo", "warning")
        return
	}
	try{
		const operario = await obtenerOperario(codigo.substring(3))
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
		if(data.includes("registrado")){
			mostrarAlert(`Operario ${operario.nombre} ${data}.`, "warning")
			actualizarTablasConTiemposOperarios()
		}else{
			mostrarAlert(`Operario ${operario.nombre} ${data}.`, "success")			
		}
		mostrarOperariosCT()
	}catch(error){
		console.log("Error al tratar de realizar la operacion ", error)
	}
	
}

async function validaEvento(codigo){
	if(codigo == "EVE0100"){
		if(configProceso === null){
			mostrarAlert("Debe existir al menos un centro de trabajo registrado para finalizar el turno", "danger")
			return
		}
		try{
			const response = await fetch(`/config-procesos/${configProceso.id}/finalizar-turno`,{
				method: 'POST',
				headers: {
                	'Content-Type': 'application/json',
            	},
			})
			if (response.ok) {
		        const data = await response.json();
		        if (data.status === "success") {
		            mostrarAlert(data.message, "success");
		        } else {
		            mostrarAlert(data.message, "danger");
		        }
		    } else {
		        const text = await response.text();
		        mostrarAlert(text, "danger");
		    }
		}catch(error){
			console.error("Error al tratar de finalizar el turno ", error)
		}
	}
}

async function obtenerOpCentroT(ct){
	spinner.removeAttribute('hidden')
	try{
		const response = await fetch(`/centros-trabajo/${ct}/ordenes-produccion`)
		const data = await response.json()
		data.sort((a, b) => b.idOp - a.idOp)
		console.log("respuesta ops CT: ",data)
		spinner.setAttribute('hidden', '')
		return data
	}catch(error){
		console.log("Error al tratar de obtener OP en centro de trabajo", error)
	}
		
}

function agregarFilaListadoItems(num, op, item, isComponente, listadoItemsTbody, papa){
	let row = document.createElement('tr')    
    let cellNum = document.createElement('td')
    cellNum.textContent = num
    row.appendChild(cellNum)
    
    let idItemOP = isComponente? papa.item_op_id: item.item_op_id
	
	const ref = isComponente?item.material_id: item.item_id
	let cellRef = document.createElement('td')
	cellRef.textContent = idItemOP+'-'+ref
	row.appendChild(cellRef)
	
	
    let cellName = document.createElement('td')
    const descripcion = isComponente? item.material_desc: item.item_desc
    cellName.textContent = descripcion 
    row.appendChild(cellName)
    
    let cellLongitud = document.createElement('td')
	cellLongitud.textContent = isComponente ? item.material_long: ''
	row.append(cellLongitud)

    let cellCliente = document.createElement('td')
    cellCliente.textContent = op.cliente
    row.appendChild(cellCliente)
    
    let cellPintura = document.createElement('td')
    cellPintura.textContent = item.item_color
    row.appendChild(cellPintura)

	const cant = isComponente ? item.material_cant : item.cant_req
    let cellCantSol = document.createElement('td')
    cellCantSol.textContent = cant
    row.appendChild(cellCantSol)
    
    let peso = isComponente ? item.material_peso: item.item_peso 
    let pesoUnitario = document.createElement('td')
	pesoUnitario.textContent = peso
	row.appendChild(pesoUnitario)
	
	let pesoTotal = document.createElement('td')
	let resultadoPeso = peso * cant
	pesoTotal.textContent = resultadoPeso.toFixed(2)
	row.appendChild(pesoTotal)
	
	if(mostrarPrioridad){
		let cellPrioridad = document.createElement('td')
		cellPrioridad.textContent = item.prioridad
		row.appendChild(cellPrioridad)
	}
	
	row.addEventListener('mouseover', function() {
        row.style.cursor = 'pointer';
    });

    row.addEventListener('mouseout', function() {
        row.style.cursor = 'default';
    });
	
    row.addEventListener('click', function(){
		const operarioSelected = document.getElementById("operario-selected").textContent
		if(!operarioSelected){
			console.log("validando operario seleccionado", operarioSelected)
			mostrarAlert("No existe operario o no se ha seleccionado.", "danger")
		}else{
			const pieza = {
				idItemOP: idItemOP,
				descripcion: descripcion,
				isComponente:  isComponente,
				idItem: ref,
			}
			confirmModal(pieza)
		
		}
	})
	
	listadoItemsTbody.appendChild(row)
}

function cargarListadoItems(){
	let esquema = document.getElementById("esquema")	
	let selectOpsCt = document.getElementById('op-selected')
    let opSelected = selectOpsCt.value
    let listadoItemsTbody = document.getElementById('listado-items')
    listadoItemsTbody.innerHTML = ''
	const ops = opsCt.filter(item => item.op === opSelected)
    esquema.value = ops[0].esquemaPintura ?? ''
	const centrosPrioridad = localStorage.getItem('centrosTrabajoPrioridad')
    mostrarPrioridad = centrosPrioridad.includes(centroTSelected.id)
    if(mostrarPrioridad){
		let thead = document.getElementById('encabezado-listado-items')
		let prioridadCell = document.createElement('th')
		prioridadCell.textContent = "PRIORIDAD"
		thead.appendChild(prioridadCell)
	}
    if (ops) {
        ops.forEach(function(op) {
			let num = 1
			for(const item of op.items){	
            	const componentes = item.componentes;
            
	            if (item.item_centro_t_id === Number(centroTSelected.id)) {
	                agregarFilaListadoItems(num, op, item, false, listadoItemsTbody);
	                num++
	            }
				if(componentes.length > 0){
		            for(const componente of componentes){
						if (componente.material_centro_t_id === Number(centroTSelected.id)) {
                        	agregarFilaListadoItems(num, op, componente, true, listadoItemsTbody, item);
                    		num++
                    	}
					}					
				}
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
	
	let container = document.createElement("div")
    container.style.display = "flex"
    container.style.alignItems = "center"
    
	let labelElement = document.createElement("label")
	labelElement.textContent = "Seleccione OP: "
	labelElement.setAttribute("for", "op-selected")
	labelElement.style.marginRight = "1rem"
	labelElement.style.whiteSpace = "nowrap"
	container.appendChild(labelElement)
	
	let inputElement = document.createElement("input");
    inputElement.setAttribute("type", "text");
    inputElement.setAttribute("id", "op-selected");
    inputElement.setAttribute("list", "opciones");
    inputElement.classList.add("form-control")
    
    let datalistElement = document.createElement("datalist");
    datalistElement.id = "opciones";
    
    if (Array.isArray(opsCt)) {
        opsCt.forEach(function(op) {
            const optionElement = document.createElement("option");
            //optionElement.value = op.idOp;
            optionElement.text = op.op;
            datalistElement.appendChild(optionElement);
        });
        inputElement.appendChild(datalistElement);
        container.appendChild(inputElement);
        opCtElement.appendChild(container);
    }
    
    inputElement.addEventListener("keydown", function(event) {
            if (event.key === 'Enter') {
                cargarListadoItems();
            }
        });
    
    inputElement.addEventListener("change", function() {
            cargarListadoItems();
        });
        
    datalistElement.addEventListener("input", function() {
            cargarListadoItems();
        });
        
    datalistElement.addEventListener("change", function() {
            cargarListadoItems();
        });
	/*let selectElement = document.createElement("select")
	selectElement.setAttribute("id", "op-selected")
	selectElement.classList.add('form-control')
	selectElement.addEventListener("change", function() {
        cargarListadoItems()        
    })
	if (Array.isArray(opsCt)) {
        opsCt.forEach(function (op) {
	        const optionElement = document.createElement("option")
	        selectElement.style.margin = "0 0 0 1rem"
	        optionElement.value = op.idOp
	        optionElement.text = op.op
	        selectElement.appendChild(optionElement)
        })
		container.appendChild(selectElement)
        opCtElement.appendChild(container)
    } else {
        console.error("La variable opsCt no es un array.")
    }*/
}

function crearSelectOperariosCt(operariosCt){
	let operariosCtElement = document.getElementById("operarios-ct")
	
	let container = document.createElement("div")
    container.style.display = "flex"
    container.style.alignItems = "center"
    
	let labelElement = document.createElement("label")
	labelElement.textContent = "Seleccione operario: "
	labelElement.setAttribute("for", "operario-selected")
	labelElement.style.marginRight = "1rem"
	labelElement.style.whiteSpace = "nowrap"
	container.appendChild(labelElement)
	
	let inputElement = document.createElement("input");
    inputElement.setAttribute("type", "text");
    inputElement.setAttribute("id", "operario-selected");
    inputElement.setAttribute("list", "operarios");
    inputElement.classList.add("form-control")
    
    let datalistElement = document.createElement("datalist");
    datalistElement.id = "operarios";
    
    if (Array.isArray(operariosCt)) {
        operariosCt.forEach(function(operario) {
            const optionElement = document.createElement("option");
            optionElement.text = operario.nombre;
            optionElement.setAttribute('data-operario', JSON.stringify({ nombre: operario.nombre, id: operario.id }));
            datalistElement.appendChild(optionElement);
        });
        inputElement.appendChild(datalistElement);
        container.appendChild(inputElement);
        operariosCtElement.appendChild(container);
    }
	
	/*let selectElement = document.createElement("select")
	selectElement.setAttribute("id", "operario-selected")
	selectElement.style.margin = "0 0 0 1rem"
	selectElement.classList.add('form-control')
	
	operariosCt.forEach(function (operario){
		const optionElement = document.createElement("option")
		optionElement.value = operario.id
		optionElement.text = operario.nombre
		selectElement.appendChild(optionElement)
	})
	container.appendChild(selectElement)
	operariosCtElement.appendChild(container)*/
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
	opsCt = await obtenerOpCentroT(centroTSelected.id)
    let operariosCt = await obtenerOperariosCT()
	
    crearSelectCT(opsCt)
    crearSelectOperariosCt(operariosCt)
    //cargarListadoItems()
    modalAsignarPieza.show()
    modalAsignarPieza._element.addEventListener('hidden.bs.modal', function () {
    limpiarElementos()
    llenarTablaPiezasOperario()
})
}

let itemAgregar
let confirm_modal
function confirmModal(item) {
	const operarioSelected = document.getElementById("operario-selected");
	const datalist = document.getElementById("operarios");
	const opcionSeleccionada = Array.from(datalist.options).find(opcion => opcion.value === operarioSelected.value);
	
	let idOperario
	let operarioSelectedName
	if (opcionSeleccionada) {
	  const operarioData = JSON.parse(opcionSeleccionada.getAttribute('data-operario'));
	  idOperario = operarioData.id;
	  operarioSelectedName = operarioData.nombre;
	}

	const descripcion = item.descripcion
	confirm_modal = new bootstrap.Modal('#modal-confirma-pieza', {
		  keyboard: false
		})
	const confirm_modal_body = confirm_modal._element.querySelector(".modal-confirm-body")

	confirm_modal_body.textContent = `Esta seguro que desea asignar la pieza ${descripcion} al operario ${operarioSelectedName}.`
	itemAgregar = item
	const btnAceptar = confirm_modal._element.querySelector(".btn-primary");

	let piezaOperario = {
		idProceso: configProceso.id,
		idOperario: idOperario,
		idItemOp: item.idItemOP,
		idItem: item.idItem,
		isComponente:  item.isComponente,
		estaActivo: true
	}
	
    btnAceptar.setAttribute("data-item", JSON.stringify(piezaOperario));
	confirm_modal.show()	
}

async function agregarPiezaOperario(event){
	let piezasOperario = []
	const btnAceptar = event.target;
    const itemToAdd = JSON.parse(btnAceptar.getAttribute("data-item"))    
    piezasOperario.push(itemToAdd)
    try{
		const response = await fetch(`/centros-trabajo/${centroTSelected.id}/asignar-pieza`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(piezasOperario),
        })
		
		if(!response){
			throw new Error("Error en la asignacion de pieza")
		}

        confirm_modal.hide();
	}catch(error){
		console.error(error)
	}
}

function clearTable(table) {
    while (table.rows.length > 1) {
        table.deleteRow(1)
    }
}

function addRowToTable(table, operario, index) {
    let newRow = table.insertRow()
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
	if(configProceso != null){
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

let modalParada
let paradaSelected
async function regActualizarParada(codigo){
	console.log("Registra una parada, id de la parada: ", codigo)
	if(configProceso == null){
		mostrarAlert("Debe seleccionar un centro de trabajo", "danger")
		return
	}
	
	let operariosCT = await obtenerOperariosCT()
	if(operariosCT.length == 0) {
		mostrarAlert("No hay operarios registrados en este centro de trabajo", "danger")
		return 
	}
	const paradas = await obtenerParadas()
	for(let parada of paradas){
		if(codigo == parada.codBarrasM){
			paradaSelected = parada
			document.getElementById("descripcion-parada").value = parada.nombre
			modalParada = new bootstrap.Modal('#modal-parada')
			modalParada.show()
		}
	}
	if(!paradaSelected){
		mostrarAlert("La parada digitada no existe.", "danger")
		return
	}
}

async function handleKeyPressParadaOperario(event){
	if (event.key === 'Enter') {
		const codigoOperElement = document.querySelector("#cod-operario-parada")
		const codigoOper = codigoOperElement.value
        const operario = await obtenerOperario(codigoOper.substring(3))
        let operariosCT = await obtenerOperariosCT()
        let isOperarioInCt = false
        for(const oper of operariosCT){
			if(oper.id == operario.id){
				isOperarioInCt = true
				const registroParadaDTO ={
					"idConfigProceso": configProceso.id,
					"idOperario": operario.id,
					"idParada": paradaSelected.id
				}
				await registrarActualizarPara(registroParadaDTO)
				modalParada.hide()
		        codigoOperElement.value = ''
			}
		}
		if(!isOperarioInCt){
			mostrarAlert("El operario digitado no se encuentra registrado en el centro de trabajo", "danger")
		}
    }
}

async function registrarActualizarPara(registroParadaDTO){
	try{
		const response = await fetch(`/centros-trabajo/${centroTSelected.id}/paradas`,
		{
			method: "POST",
			headers: {
		      'Content-Type': 'application/json'
		    },
			body: JSON.stringify(registroParadaDTO)
		})
		
		if(!response.ok){
			let error = await response.json();
            throw new Error(error.mensaje);
		}
		const data = await response.json()
		mostrarAlert(data.mensaje,"success")
	}catch(error){
		mostrarAlert(error,"danger")
	}
}

function reportePiezasNovedades(){
	if(centroTSelected){
		return true		
	}else{
		mostrarAlert("Debe seleccionar un centro de trabajo", "danger")
		return false
	}
	
}

async function obtenerPiezasOperarioCt(operarioDTO){
	try{
		const response = await fetch(`/centros-trabajo/${centroTSelected.id}/piezas-operario-proceso`,
			{
			method: 'POST',
			headers: {
		      'Content-Type': 'application/json'
		    },
			body: JSON.stringify(operarioDTO)
			})
		if(!response.ok){
			throw new Error("Error en la asignacion de pieza")
		}
		const data = await response.json()
		return data
		
	}catch (error){
		console.log(error)
	}
}

let modalReportar
async function isValid(event){
	const element = event.target.id
	const valido = reportePiezasNovedades()
	if(valido){
		if(element == "btn-reporte-piezas"){
			modalReportar = new bootstrap.Modal('#reporte-piezas', {
			  keyboard: false
			})
			document.getElementById('listado-piezas-operario').innerHTML = ''
			modalReportar.show()			
		}else{
			modalReportar = new bootstrap.Modal('#novedades', {
			  keyboard: false
			})
			document.getElementById('listado-piezas-operario-novedades').innerHTML = ''
			modalReportar.show()
		}
	}	
}

async function handleKeyPressPiezasOperario(event) {
    if (event.key === 'Enter') {
		const codigoOperElement = document.querySelector("#codigo-operario-reporte")
		const codigoOper = codigoOperElement.value
        const operario = await obtenerOperario(codigoOper.substring(3))
        document.getElementById('codigo-operario-reporte').value = ''
        const operarioDTO = {
			"idOperario": operario.id,
			"idCentroTrabajo": centroTSelected.id,
			"idConfigProceso": configProceso.id
		}
			
		
		let ops = await obtenerPiezasOperarioCt(operarioDTO)
		ops = ops.filter(op => op.cantFabricada !== op.cantReq)
		if(ops.length == 0) {
			mostrarAlert("No tiene piezas asignadas pendientes en proceso", "warning")
			modalReportar.hide()
		}else{
		console.log("Piezas asignadas al operario: ", ops)
		mostrarPiezasOperario(ops, operario, 'listado-piezas-operario')			
		}
    }
}

async function handleKeyPressNovedadesOperario(event){
	if(event.key === 'Enter'){
		const codigoOperElement = document.querySelector("#codigo-operario-novedad")
		const codigoOper = codigoOperElement.value
        const operario = await obtenerOperario(codigoOper.substring(3))
        document.getElementById('codigo-operario-novedad').value = ''
        const operarioDTO = {
			"idOperario": operario.id,
			"idCentroTrabajo": centroTSelected.id,
			"idConfigProceso": configProceso.id
		}
	
		let ops = await obtenerPiezasOperarioCt(operarioDTO)
		if(ops.length == 0) {
			mostrarAlert("No tiene piezas asignadas en proceso", "warning")
			modalReportar.hide()
		}else{
		console.log("Piezas asignadas al operario NOVEDADES: ", ops)
		mostrarPiezasOperario(ops, operario, 'listado-piezas-operario-novedades')		
		}
	}
}

function mostrarPiezasOperario(items, operario, idTbody){
	let listadoItemsTbody = document.getElementById(idTbody)
    listadoItemsTbody.innerHTML = ''
	items.forEach((item, index) => {
		if(item != null){
	    const row = crearFilaMostrarPiezas(index, item, idTbody, operario)						
	    listadoItemsTbody.appendChild(row)		                
			
		}
		})
}

document.getElementById('codigo-operario-reporte').addEventListener('change', function (event) {
	(async()=>{
	    await handleKeyPressPiezasOperario(event)		
	})()
})


function crearFilaMostrarPiezas(index, item, idTbody, operario) {
    let row = document.createElement('tr')

    let cellNum = document.createElement('td')
    cellNum.textContent = index + 1
    row.appendChild(cellNum)

    let cellName = document.createElement('td')

    let idPieza
	if(item.idItem == null){
		idPieza = 0
	}else{
    idPieza = item.idItem
		
	}

    const ref = item.idItemFab ?? item.idParte
    let cellRef = document.createElement('td')
    cellRef.textContent = idPieza
    row.appendChild(cellRef)

    //descripcion = descripcion ?? item.descripcionItem
    cellName.textContent = item.descripcionItem
    row.appendChild(cellName)

    let cellLongitud = document.createElement('td')
    cellLongitud.textContent = item.longitud // isComponente ? longitud : ''
    row.append(cellLongitud)

    let cellCliente = document.createElement('td')
    cellCliente.textContent = item.cliente
    row.appendChild(cellCliente)

    let cellProyecto = document.createElement('td')
    cellProyecto.textContent = item.co
    row.appendChild(cellProyecto)

    let cellCantSol = document.createElement('td')
    cellCantSol.textContent = item.cantReq
    row.appendChild(cellCantSol)

    let pesoUnitario = document.createElement('td')
    pesoUnitario.textContent = item.peso
    row.appendChild(pesoUnitario)

    let pesoTotal = document.createElement('td')
    let resultadoPeso = item.cantReq * item.peso
    pesoTotal.textContent = resultadoPeso.toFixed(2)
    row.appendChild(pesoTotal)

    row.addEventListener('mouseover', function() {
        row.style.cursor = 'pointer'
    })

    row.addEventListener('mouseout', function() {
        row.style.cursor = 'default'
    })

    row.addEventListener('click', function() {
        if (idTbody == 'listado-piezas-operario') {

            window.location.href = `/centros-trabajo/${centroTSelected.id}/reporte?idItem=` + idPieza +
                '&idOperario=' + operario.id
        }
        if (idTbody == 'listado-piezas-operario-novedades') {
            window.location.href = `/centros-trabajo/${centroTSelected.id}/novedades?idItem=` + idPieza +
                '&idOperario=' + operario.id
        }
    })
    return row
}
