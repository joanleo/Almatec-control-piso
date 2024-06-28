document.addEventListener('DOMContentLoaded', function(){
	let tbodyRemisiones = document.getElementById('body-remisiones')
	let rows = tbodyRemisiones.querySelectorAll('tr')
	rows.forEach(row=>{
		row.addEventListener('click', async function(event){
			const idRemision = parseInt(row.cells[0].textContent.split('-')[1])
			document.getElementById('noRm').value = idRemision
			document.getElementById('fechaCreacion').value = row.cells[4].textContent
			document.getElementById('opSel').value = row.cells[1].textContent
			document.getElementById('cliente').value = row.cells[2].textContent
			
			document.getElementById('remSel').removeAttribute('hidden')
			
			const detallesRemision = await obtenerDetalleRemision(idRemision)
			fillTableDetalleRemision(detallesRemision)
			
		})
	})
})

async function obtenerDetalleRemision(idRemision){
	try{
		const request = await fetch(`/almacen/remisiones/${idRemision}/detalle-remision`)
		if(!request.ok){
			console.log(await request.json())
			throw new Error("Error al tratar de obtener el detalle de la solicitud")
		}
		const response = await request.json()
		console.log(response)
		return response
	}catch (error){
		console.log(error)
	}
}

function fillTableDetalleRemision(detalles){
	let tbodyDetalle = document.getElementById('body-detalle-remision')
	tbodyDetalle.innerHTML = ''
	
	detalles.forEach(item =>{
		let row = document.createElement('tr')
		
		let cellItem = document.createElement('td')
		cellItem.textContent = item.idItemOp
		row.appendChild(cellItem)
		
		let cellDescripcion = document.createElement('td')
		cellDescripcion.textContent = item.descripcion
		row.appendChild(cellDescripcion)
		
		let cellCant = document.createElement('td')
		cellCant.textContent = item.cant
		row.appendChild(cellCant)
		
		let cellPeso = document.createElement('td')
		cellPeso.textContent = item.peso
		row.appendChild(cellPeso)
		
		const pesoTtl = item.cant * item.peso
		let cellPesoTtl = document.createElement('td')
		cellPesoTtl.textContent = pesoTtl.toFixed(3)
		row.appendChild(cellPesoTtl)
		
		
		tbodyDetalle.appendChild(row)
	})
}

function imprimirRemision(){
	mostrarModal()
	/*const idRemision = document.getElementById('noRm').value
	
	spinner.removeAttribute('hidden')
    fetch(`/almacen/remisiones/${idRemision}/pdf/generar`)
	.then(response => response.blob())
    .then(blob => {
        const url = window.URL.createObjectURL(blob)
        const a = document.createElement('a')
        a.href = url
        a.download = `RM-${idRemision}.pdf`
        document.body.appendChild(a)
        a.click()
        window.URL.revokeObjectURL(url)
        spinner.setAttribute('hidden', '')
    })
	.catch(error => {
	    console.error('Error:', error)
	})*/
	
}

function mostrarModal() {
    const modalBody = document.getElementById('datosTransportadoraBody');
    modalBody.innerHTML = '';

    let divWrapper = document.createElement('div');

    const inputs = [
        { label: 'Transportadora', id: 'transportadora', type: 'text' },
        { label: 'Vehiculo', id: 'vehiculo', type: 'text' },
        { label: 'Conductor', id: 'conductor', type: 'text' },
        { label: 'Placa', id: 'placa', type: 'text' },
        { label: 'Cedula', id: 'cedula', type: 'text' },
        { label: 'Pase', id: 'pase', type: 'text' },
        { label: 'Celular', id: 'celular', type: 'tel' },
    ];

    const twoColumnInputs = [
        { label: 'Peso Entrada', id: 'pesoEntrada', type: 'number' },
        { label: 'Peso Salida', id: 'pesoSalida', type: 'number' },
        { label: 'Hora Entrada', id: 'horaEntrada', type: 'time' },
        { label: 'Hora Salida', id: 'horaSalida', type: 'time' },
    ];

    inputs.forEach(inputData => {
        let divInput = document.createElement('div');
        divInput.classList.add('input-group', 'my-3');

        let spanLabel = document.createElement('span');
        spanLabel.classList.add('input-group-text');
        spanLabel.textContent = inputData.label;

        let inputElement = document.createElement('input');
        inputElement.type = inputData.type;
        inputElement.classList.add('form-control');
        inputElement.id = inputData.id;
        inputElement.style.textAlign = 'right';

        divInput.appendChild(spanLabel);
        divInput.appendChild(inputElement);
        divWrapper.appendChild(divInput);
    });

    let divTwoColumnWrapper = document.createElement('div');
    divTwoColumnWrapper.classList.add('grid-container');

    twoColumnInputs.forEach(inputData => {
        let divInput = document.createElement('div');
        divInput.classList.add('input-group', 'my-3');

        let spanLabel = document.createElement('span');
        spanLabel.classList.add('input-group-text');
        spanLabel.textContent = inputData.label;

        let inputElement = document.createElement('input');
        inputElement.type = inputData.type;
        inputElement.classList.add('form-control');
        inputElement.id = inputData.id;
        inputElement.style.textAlign = 'right';

        divInput.appendChild(spanLabel);
        divInput.appendChild(inputElement);
        divTwoColumnWrapper.appendChild(divInput);
    });

    divWrapper.appendChild(divTwoColumnWrapper);
    modalBody.appendChild(divWrapper);

    const modal = new bootstrap.Modal(document.getElementById('datosTransportadora'));
    modal.show();
}

function imprimirDatos(){
	const datosTransportadora = {
	        nombre: document.getElementById('transportadora').value,
	        vehiculo: document.getElementById('vehiculo').value,
	        conductor: document.getElementById('conductor').value,
	        placa: document.getElementById('placa').value,
	        cedula: document.getElementById('cedula').value,
	        pase: document.getElementById('pase').value,
	        celular: document.getElementById('celular').value,
	        pesoEntrada: document.getElementById('pesoEntrada').value,
	        pesoSalida: document.getElementById('pesoSalida').value,
	        horaEntrada: document.getElementById('horaEntrada').value,
	        horaSalida: document.getElementById('horaSalida').value,
	    }
		
		const idRemision = document.getElementById('noRm').value
			
			spinner.removeAttribute('hidden')
		    fetch(`/almacen/remisiones/${idRemision}/pdf/generar`			, {
			        method: 'POST',
			        headers: {
			            'Content-Type': 'application/json',
			        },
			        body: JSON.stringify(datosTransportadora),
			    })
			.then(response => response.blob())
		    .then(blob => {
		        const url = window.URL.createObjectURL(blob)
		        const a = document.createElement('a')
		        a.href = url
		        a.download = `RM-${idRemision}.pdf`
		        document.body.appendChild(a)
		        a.click()
		        window.URL.revokeObjectURL(url)
		        spinner.setAttribute('hidden', '')
				const modal = bootstrap.Modal.getInstance(document.getElementById('datosTransportadora'))
				modal.hide()
		    })
			.catch(error => {
			    console.error('Error:', error)
			})
}