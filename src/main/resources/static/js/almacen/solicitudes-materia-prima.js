document.addEventListener('DOMContentLoaded', async function() {
	console.log("Se cargo el html")
    const filas = document.querySelectorAll('#body-solicitudes-materia-prima tr');
    filas.forEach(function(row) {
		row.addEventListener('mouseover', function() {
            row.style.cursor = 'pointer'
        })

        row.addEventListener('mouseout', function() {
            row.style.cursor = 'default'
        })
        row.addEventListener('click', async function() {
			console.log(row.children[2].textContent)
            const idSolic = row.children[0].textContent
			document.getElementById("solSel").removeAttribute("hidden")
			document.getElementById("noSolSel").value = idSolic
			document.getElementById("fechaSolSel").value = row.children[1].textContent
			document.getElementById("opSel").value = row.children[2].textContent
			document.getElementById("usuSolSel").value = row.children[3].textContent
            console.log("solicitud: ", idSolic)
            const detalleSolic = await obtenerDetalleSolicitud(idSolic)
            llenarTablaDetalle(detalleSolic)
        })
    })
    
    const respuestaModal = document.getElementById('respuestaModal')
    respuestaModal.addEventListener('hidden.bs.modal', function () {
        window.location.reload()
    })
})

async function obtenerDetalleSolicitud(idSolic){	
	const response = await fetch(`/almacen/detalle/solicitud/${idSolic}`)	
	const data = await response.json()
	console.log(data)
	return data
}

function llenarTablaDetalle(detalleSolic){
	let tbody = document.getElementById("body-detalle-materia-prima")
	tbody.innerHTML = ""
	detalleSolic.forEach(item => {
		let row = document.createElement('tr')
		
		let cellCodigo = document.createElement('td')
		cellCodigo.textContent = item.codigoErp
		row.appendChild(cellCodigo)
		
		let cellDescripcion = document.createElement('td')
        cellDescripcion.textContent = item.descripcionItem
        row.appendChild(cellDescripcion)
        
        let cellUm = document.createElement('td')
        cellUm.textContent = item.undErp
        row.appendChild(cellUm)
        
        let cellBodegaOrigen = document.createElement('td')
        cellBodegaOrigen.textContent = item.bodegaOrigen
        row.appendChild(cellBodegaOrigen)
        
        let cellBodegaDestino = document.createElement('td')
        cellBodegaDestino.textContent = item.bodegaDestino
        row.appendChild(cellBodegaDestino)
        
        let cellLote = document.createElement('td')
        cellLote.textContent = item.loteErp
        row.appendChild(cellLote)
        
        let cellSolic = document.createElement('td')
        cellSolic.textContent = item.cantSol
        row.appendChild(cellSolic)
        
		/*const idSol = item.idSol
        let cellTransfer = document.createElement('td')
		let selectButton = document.createElement('button')
	    selectButton.textContent = 'Tranferir'
	    selectButton.classList.add('btn', 'btn-primary')
	    selectButton.addEventListener('click', function(event) {
			event.preventDefault()
			console.log("Se hace la solicitud para crear la transferencia")
			crearTransferencia(idSol)
		})
		cellTransfer.appendChild(selectButton)
		row.appendChild(cellTransfer)*/
        
        tbody.appendChild(row)        
        
	})
}

function rechazarTransferencia(event){
	event.preventDefault()
	console.log("Logica cuando se rechaza la transferencia")
}

function crearTransferencia(event){
	event.preventDefault()
	const idSol = document.getElementById("noSolSel").value 
	console.log(idSol)
	console.log("Se hace la solicitud para crear la transferencia")
	solicitarTransferencia(idSol)
	
}

async function solicitarTransferencia(idSol){
	spinner.removeAttribute('hidden')
	try{
		const solicitud = await fetch(`/api/transferencia/${idSol}`, {
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/json'
	        	}
	    	})
	    	
		const response = await solicitud.json()
	    if(!solicitud.ok){
			console.log(response)
			mostrarAlert(response.message, 'danger')
			throw new Error(response.message)
		}
		console.log(response)
		//mostrarAlert(response.message, 'success')
		mostrarModal(response.message)
		
	}catch(error){
		console.error('Ocuriio un error al tratar de crear la transferencia:', error)
		
	}finally{
		spinner.setAttribute('hidden', '')		
	}
	
	console.log(response)
	
}

function mostrarModal(mensaje) {
    const modalBody = document.getElementById('respuestaModalBody');
    modalBody.innerHTML = mensaje;
    const modal = new bootstrap.Modal(document.getElementById('respuestaModal'));
    modal.show();
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
        
        //setTimeout(() => alertElement.remove(), 5000)
}

