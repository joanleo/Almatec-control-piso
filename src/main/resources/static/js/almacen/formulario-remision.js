let idOpSelected

let ops = [];
let filteredOps = [];
let currentPage = 1;
const itemsPerPage = 5;
let selectedOp = null;
let items = [];
let remisionItems = [];

document.addEventListener('DOMContentLoaded', async function() {
	fetchOps();
	document.getElementById('tab-fabricado').addEventListener('click', async function() {
        document.getElementById('tab-fabricado').classList.add('active');
        document.getElementById('tab-ferreteria').classList.remove('active');
        //const items = await obtenerItems(idOpSelected);
        renderItemTable('fabricado');
    });

    document.getElementById('tab-ferreteria').addEventListener('click', async function() {
        document.getElementById('tab-fabricado').classList.remove('active');
        document.getElementById('tab-ferreteria').classList.add('active');
        //const items = await obtenerItems(idOpSelected);
        renderItemTable('ferreteria');
    });

	document.getElementById('searchOp').addEventListener('input', handleSearch);
	
	}
)

function handleSearch(event) {
    const searchTerm = event.target.value.toLowerCase();
    filteredOps = ops.filter(op => {
		const numOpMatch = op.numOp.toString().includes(searchTerm);
        const clienteMatch = op.cliente.toLowerCase().includes(searchTerm);
        const proyectoMatch = op.proyecto.toLowerCase().includes(searchTerm);
        return numOpMatch || clienteMatch || proyectoMatch;agregarItemTableDetalleRemision}
    );
    currentPage = 1;
    renderOpTable();
}

async function fetchOps() {
    spinner.removeAttribute('hidden')
    try {
        const response = await fetch('/almacen/remisiones/ops');
        ops = await response.json();
        filteredOps = [...ops];
		
        renderOpTable();
    } catch (error) {
        console.error('Error fetching OPs:', error);
        showAlert('Error al cargar las OPs', 'danger');
    } finally {
        spinner.setAttribute("hidden", '')
    }
}

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

function renderOpTable() {
    const tbody = document.querySelector('#opTable tbody');
    tbody.innerHTML = '';
    
    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const paginatedOps = filteredOps.slice(startIndex, endIndex);
    paginatedOps.forEach(op => {
        const row = tbody.insertRow();
        row.innerHTML = `
            <td>${op.numOp}</td>
            <td>${op.cliente}</td>
            <td>${op.proyecto}</td>
            <td><button class="btn btn-primary btn-sm" onclick="selectOp(${op.idOpIa})">Seleccionar</button></td>
        `;
    });
    
    renderPagination();
}

function renderPagination() {
    const pagination = document.getElementById('opPagination');
    const pageCount = Math.ceil(filteredOps.length / itemsPerPage);
    
    let paginationHTML = '';
    for (let i = 1; i <= pageCount; i++) {
        paginationHTML += `
            <li class="page-item ${currentPage === i ? 'active' : ''}">
                <a class="page-link" href="#" onclick="changePage(${i})">${i}</a>
            </li>
        `;
    }
    
    pagination.innerHTML = paginationHTML;
}

function changePage(page) {
    currentPage = page;
    renderOpTable();
}

async function selectOp(idOpIa) {
    spinner.removeAttribute('hidden')
	document.getElementById('body-detalle-remision').innerHTML = ''
    try {
        const response = await fetch(`/almacen/remisiones/${idOpIa}`);
        items = await response.json();
        selectedOp = ops.find(op => op.idOpIa === idOpIa);
		document.getElementById('title-op').textContent = 'Items de la OP-' + selectedOp.numOp
		document.getElementById('tabs').removeAttribute('hidden')
        renderItemTable('fabricado');
    } catch (error) {
        console.error('Error fetching items:', error);
        showAlert('Error al cargar los items', 'danger');
    } finally {
		spinner.setAttribute("hidden", '') 
    }
}

function renderItemTable(type){
	if(type === 'fabricado'){
		document.getElementById('tab-fabricado').classList.toggle('active', type === 'fabricado');
		document.getElementById('tab-ferreteria').classList.toggle('active', type === 'ferreteria');		
	}else{
    	document.getElementById('tab-ferreteria').classList.toggle('active', type === 'ferreteria');
		document.getElementById('tab-fabricado').classList.toggle('active', type === 'fabricado');		
	}
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
		
		let cellButton = document.createElement('td')
		if((item.idItemFab != 0 && item.cantDespachada < item.cant && item.cantDespachada < item.cantCumplida) || (item.idItemFab == 0 && item.cantDespachada < item.cant)){
			let button = document.createElement('button')
			button.innerHTML = 'Agregar'
			button.classList.add('btn', 'btn-primary')
			button.type = 'button'
			button.addEventListener('click', function(){
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
                <td><input class="form-control cant-remisionar" type="number" min="1" ${item.idItemFab !== 0 ? `max="${cantMax}"  id="${item.id + '-' + item.idItemFab}"` : `max="${cantFerr}" id="itemFerr"`} required /></td>
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
		idOpIa: selectedOp.idOpIa,
		idUsuario: idUsuario,
		observaciones: observaciones,
		detalles: detalles
	}
	
	
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