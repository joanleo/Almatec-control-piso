let idOpSelected
document.addEventListener('DOMContentLoaded', async function() {
	document.getElementById("id-op").addEventListener('change', async function(){
		if (this.value !== '') {
			const seleccion = this.options[this.selectedIndex]
			const op = seleccion.getAttribute('data-op')
	        document.getElementById("cliente").value = op.split(',')[0]
	        document.getElementById("proyecto").value = op.split(',')[1]
	        document.getElementById('solSel').removeAttribute('hidden')
	        idOpSelected = seleccion.value
	        console.log(idOpSelected)
	        const items = await obtenerItems(idOpSelected)
	        document.getElementById('tabs').removeAttribute('hidden')
	        fillTableRemision(items, 'fabricado')
	        
	        document.getElementById('tab-fabricado').addEventListener('click', async function() {
		        document.getElementById('tab-fabricado').classList.add('active');
		        document.getElementById('tab-ferreteria').classList.remove('active');
		        const items = await obtenerItems(idOpSelected);
		        fillTableRemision(items, 'fabricado');
		    });
		
		    document.getElementById('tab-ferreteria').addEventListener('click', async function() {
		        document.getElementById('tab-fabricado').classList.remove('active');
		        document.getElementById('tab-ferreteria').classList.add('active');
		        const items = await obtenerItems(idOpSelected);
		        fillTableRemision(items, 'ferreteria');
		    });
	                 
	    }
	})
	}
)

async function obtenerItems(idOpIa){
	spinner.removeAttribute('hidden')
	try{
		const request = await fetch(`/almacen/remisiones/${idOpIa}`)
		if (request.ok) {
			return await request.json();
		} else {
			throw new Error('Eror al atratar de obtener los items.');
		}
	}catch (error){
		console.log(error)
	}finally{
		spinner.setAttribute("hidden", '')
	}
}

function fillTableRemision(items, type){
	let tbody = document.getElementById('body-items-op')
	tbody.innerHTML = ''
	items.filter(item => (type === 'fabricado' && item.idItemFab !== 0) || (type === 'ferreteria' && item.codigoErp !== '0'))
	.forEach((item, index)=>{
		let row = document.createElement('tr')
		
		let cellNumber = document.createElement('td')
		cellNumber.textContent = index + 1
		row.appendChild(cellNumber)
		
		let cellRef = document.createElement('td')
		cellRef.textContent = item.id + '-' + item.idItemFab
		row.appendChild(cellRef)
		
		let cellMarca = document.createElement('td')
		cellMarca.textContent = item.marca
		row.appendChild(cellMarca)
		
		let cellDescripcion = document.createElement('td')
		cellDescripcion.textContent = item.descripcion
		row.appendChild(cellDescripcion)
		
		let cellCantSol = document.createElement('td')
		cellCantSol.textContent = item.cant
		row.appendChild(cellCantSol)
		
		let cellCantCumplida = document.createElement('td')
		cellCantCumplida.textContent = item.cantCumplida
		row.appendChild(cellCantCumplida)
		
		let cellCantEntregada = document.createElement('td')
		cellCantEntregada.textContent = item.cantDespachada
		row.appendChild(cellCantEntregada)
		
		/*const cantMax = item.cantCumplida - item.cantDespachada
		let cellCant = document.createElement('td')
		let input = document.createElement('input');
		input.type = 'number';
		input.max = cantMax;
		input.min = 1;
		input.id = index;
		input.addEventListener('input', function() {
		  if (this.value < 0)  this.value = ''
		  
		  if(this.value > cantMax) this.value = cantMax
		});
		cellCant.appendChild(input);
		row.appendChild(cellCant)*/
		
		let cellButton = document.createElement('td')
		console.log(item)
		if((item.idItemFab != 0 && item.cantDespachada < item.cant && item.cantDespachada < item.cantCumplida) || (item.idItemFab == 0 && item.cantDespachada < item.cant)){
			let button = document.createElement('button')
			button.innerHTML = 'Agregar'
			button.classList.add('btn', 'btn-primary')
			button.type = 'button'
			button.addEventListener('click', function(){
				console.log("mover item a tabla detalle remision")
				agregarItemTableDetalleRemision(item)
			})			
			cellButton.appendChild(button)
		}
		row.appendChild(cellButton)
		
		
		tbody.appendChild(row)
	})
}

function agregarItemTableDetalleRemision(item){
	let tbody = document.getElementById('body-detalle-remision')
	
	const cantMax = item.cantCumplida - item.cantDespachada
	const cantFerr = item.cant - item.cantDespachada
	let newRow = document.createElement('tr')
	newRow.innerHTML = `
                <td>${item.id + '-' + item.idItemFab}</td>
                <td>${item.marca}</td>
                <td>${item.descripcion}</td>
                <td>${item.cant}</td>
                <td>${item.cantCumplida}</td>
                <td>${item.cantDespachada}</td>
                <td><input class="form-control cant-remisionar" type="number" min="1" ${item.idItemFab !== 0 ? `max="${cantMax}"  id="itemFab"` : `max="${cantFerr}" id="itemFerr"`} required /></td>
                <td><button class="btn btn-danger" onclick=eliminarFila(this)>Eliminar</button></td>
            `
    tbody.appendChild(newRow)
    
    newRow.querySelector('.cant-remisionar').addEventListener('input', function() {
        if (this.value < 0) this.value = ''
        if(this.id == "itemFab" && this.value > cantMax) this.value = cantMax
        if(this.id == "itemFerr" && this.value > cantFerr) this.value = cantFerr
    });
}

function eliminarFila(button){
	let row = button.parentNode.parentNode
	row.parentNode.removeChild(row)
}

async function guardarRemision(){
	spinner.removeAttribute('hidden')
	console.log("guardando remision")
	
	const button = document.getElementById('submitForm');
    button.disabled = true;
    
	const idUsuario = document.getElementById('usuarioId').value
	const observaciones = document.getElementById('observaciones').value
	const rows = document.querySelectorAll('#body-detalle-remision tr')
	
	if (rows.length === 0) {
		mostrarAlert('La remision debe tener al menos un elemento.','danger')
		button.disabled = false
		spinner.setAttribute('hidden', '')
		return
	}
	
	let detalles = []
	for(const row of rows){
		const item = row.cells[0].textContent
		const detalle = {
			itemOp: parseInt(item.split('-')[0]),
			cantidad: parseFloat(row.cells[6].querySelector('input').value)
		}
		detalles.push(detalle) 
	}
	const remision = {
		idOpIa: idOpSelected,
		idUsuario: idUsuario,
		observaciones: observaciones,
		detalles: detalles
	}
	
	console.log(remision)
	
	try{
		const request = await fetch(`/almacen/remisiones`,{
			method: 'POST',
			headers: {
				'Content-type': 'application/json'
			},
			body: JSON.stringify(remision)
		})
		const response = await request.json()
		//mostrarAlert(`Se creo la remision RM-${response.idRemision}`,'success')
		localStorage.setItem('alertMessage', JSON.stringify({
            message: `Se creo la remision RM-${response.idRemision}`,
            type: 'success'
        }))
		//mostrarModal(mensaje)
		window.location.reload()		
	}catch(error){
		console.log(error)
	}finally{
		spinner.setAttribute('hidden', '')
		button.disabled = false
		}
}

document.getElementById("remisionForm").addEventListener('submit', function(event){
event.preventDefault()})

function mostrarAlert(mensaje, tipo){
	const alertElement = document.createElement('div')
    	alertElement.className = `alert alert-${tipo} alert-dismissible fade show fixed-top`
    	alertElement.role = 'alert'
    	alertElement.style.zIndex = '10000'
        alertElement.innerHTML = `
        <strong>${mensaje}</strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>`

        document.body.appendChild(alertElement)
}

function checkAndShowAlert() {
    const alertData = localStorage.getItem('alertMessage')
    console.log(alertData)
    if (alertData) {
        const { message, type } = JSON.parse(alertData)
        mostrarAlert(message, type);
        localStorage.removeItem('alertMessage');
    }
}

document.addEventListener('DOMContentLoaded', checkAndShowAlert)

function mostrarModal(mensaje) {
    const modalBody = document.getElementById('respuestaModalBody');
    modalBody.innerHTML = mensaje;
    const modal = new bootstrap.Modal(document.getElementById('respuestaModal'));
    modal.show();
}