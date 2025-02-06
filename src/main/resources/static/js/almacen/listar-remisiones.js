let currentPage = 0;
let pageSize = 5;
let totalPages = 0;

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
	
	const searchInput = document.getElementById('searchInput');
	const searchButton = document.getElementById('searchButton');

	searchButton.addEventListener('click', function() {
		currentPage = 0;
		buscarRemisiones();
	});

	searchInput.addEventListener('keyup', function(event) {
		if (event.key === 'Enter') {
			currentPage = 0;
			buscarRemisiones();
		}
	});

	// Cargar remisiones iniciales
	buscarRemisiones();
})

async function buscarRemisiones() {
	const searchTerm = document.getElementById('searchInput').value;
	const spinner = document.getElementById('spinner');
	spinner.removeAttribute('hidden');

	try {
		const response = await fetch(`/almacen/remisiones/buscar?termino=${searchTerm}&page=${currentPage}&size=${pageSize}`);
		if (!response.ok) {
			throw new Error('Error en la búsqueda de remisiones');
		}
		const data = await response.json();
		actualizarTablaRemisiones(data.content);
		actualizarPaginacion(data);
	} catch (error) {
		console.error('Error:', error);
	} finally {
		spinner.setAttribute('hidden', '');
	}
}

function actualizarTablaRemisiones(remisiones) {
	const tbodyRemisiones = document.getElementById('body-remisiones');
	tbodyRemisiones.innerHTML = '';

	remisiones.forEach(rem => {
		const row = document.createElement('tr');
		
		row.style.cursor = 'pointer';
		
		row.innerHTML = `
			<td>RM-${rem.idRemision}</td>
			<td>${rem.op}</td>
			<td>${rem.cliente}</td>
			<td>${rem.proyecto}</td>
			<td>${new Date(rem.fechaCreacion).toLocaleDateString()}</td>
		`;
		row.addEventListener('click', async function() {
			const idRemision = rem.idRemision;
			document.getElementById('noRm').value = idRemision;
			document.getElementById('fechaCreacion').value = new Date(rem.fechaCreacion).toLocaleDateString();
			document.getElementById('opSel').value = rem.op;
			document.getElementById('cliente').value = rem.cliente;
			
			document.getElementById('remSel').removeAttribute('hidden');
			
			const detallesRemision = await obtenerDetalleRemision(idRemision);
			fillTableDetalleRemision(detallesRemision);
		});
		tbodyRemisiones.appendChild(row);
	});
}

function actualizarPaginacion(data) {
	totalPages = data.totalPages;
	const pagination = document.getElementById('pagination');
	pagination.innerHTML = '';

	// Botón "Anterior"
	const prevLi = document.createElement('li');
	prevLi.className = `page-item ${currentPage === 0 ? 'disabled' : ''}`;
	prevLi.innerHTML = '<a class="page-link" href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>';
	prevLi.addEventListener('click', () => {
		if (currentPage > 0) {
			currentPage--;
			buscarRemisiones();
		}
	});
	pagination.appendChild(prevLi);

	// Números de página
	for (let i = 0; i < totalPages; i++) {
		const li = document.createElement('li');
		li.className = `page-item ${i === currentPage ? 'active' : ''}`;
		li.innerHTML = `<a class="page-link" href="#">${i + 1}</a>`;
		li.addEventListener('click', () => {
			currentPage = i;
			buscarRemisiones();
		});
		pagination.appendChild(li);
	}

	// Botón "Siguiente"
	const nextLi = document.createElement('li');
	nextLi.className = `page-item ${currentPage === totalPages - 1 ? 'disabled' : ''}`;
	nextLi.innerHTML = '<a class="page-link" href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>';
	nextLi.addEventListener('click', () => {
		if (currentPage < totalPages - 1) {
			currentPage++;
			buscarRemisiones();
		}
	});
	pagination.appendChild(nextLi);
}


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
}

function mostrarModal() {
    const modalBody = document.getElementById('datosTransportadoraBody');
    modalBody.innerHTML = '';

    // Creamos un contenedor principal con grid para mejor organización
    const container = document.createElement('div');
    container.className = 'container-fluid px-4';

    // Sección 1: Datos del Transportista
    const seccionTransportista = document.createElement('div');
    seccionTransportista.className = 'mb-4';
    seccionTransportista.innerHTML = `
        <h6 class="border-bottom pb-2 mb-3">Datos del Transportista</h6>
        <div class="row g-3">
            <div class="col-md-6">
                <div class="form-floating">
                    <input type="text" class="form-control" id="transportadora" placeholder="Nombre transportadora">
                    <label for="transportadora">Transportadora</label>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-floating">
                    <input type="text" class="form-control" id="conductor" placeholder="Nombre conductor">
                    <label for="conductor">Conductor</label>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-floating">
                    <input type="text" class="form-control" id="cedula" placeholder="Número de cédula">
                    <label for="cedula">Cédula</label>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-floating">
                    <input type="tel" class="form-control" id="celular" placeholder="Número de celular">
                    <label for="celular">Celular</label>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-floating">
                    <input type="text" class="form-control" id="pase" placeholder="Número de pase">
                    <label for="pase">Pase</label>
                </div>
            </div>
        </div>
    `;

    // Sección 2: Datos del Vehículo
    const seccionVehiculo = document.createElement('div');
    seccionVehiculo.className = 'mb-4';
    seccionVehiculo.innerHTML = `
        <h6 class="border-bottom pb-2 mb-3">Datos del Vehículo</h6>
        <div class="row g-3">
            <div class="col-md-6">
                <div class="form-floating">
                    <input type="text" class="form-control" id="vehiculo" placeholder="Tipo de vehículo">
                    <label for="vehiculo">Vehículo</label>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-floating">
                    <input type="text" class="form-control" id="placa" placeholder="Número de placa">
                    <label for="placa">Placa</label>
                </div>
            </div>
        </div>
    `;

    // Sección 3: Datos de Peso y Hora
    const seccionPesoHora = document.createElement('div');
    seccionPesoHora.className = 'mb-4';
    seccionPesoHora.innerHTML = `
        <h6 class="border-bottom pb-2 mb-3">Datos de Peso y Hora</h6>
        <div class="row g-3">
            <div class="col-md-6">
                <div class="form-floating">
                    <input type="number" step="0.01" class="form-control" id="pesoEntrada" placeholder="Peso de entrada">
                    <label for="pesoEntrada">Peso Entrada (Kg)</label>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-floating">
                    <input type="number" step="0.01" class="form-control" id="pesoSalida" placeholder="Peso de salida">
                    <label for="pesoSalida">Peso Salida (Kg)</label>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-floating">
                    <input type="time" class="form-control" id="horaEntrada">
                    <label for="horaEntrada">Hora Entrada</label>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-floating">
                    <input type="time" class="form-control" id="horaSalida">
                    <label for="horaSalida">Hora Salida</label>
                </div>
            </div>
        </div>
    `;

    // Agregamos todas las secciones al contenedor principal
    container.appendChild(seccionTransportista);
    container.appendChild(seccionVehiculo);
    container.appendChild(seccionPesoHora);
    modalBody.appendChild(container);

    // Manejamos los botones del modal
    const modalFooter = document.querySelector('#datosTransportadora .modal-footer');
	modalFooter.innerHTML = '';
	
	const buttons = [
        {
            text: 'Cerrar',
            className: 'btn btn-secondary',
            onClick: null,  // El botón de cerrar usa data-bs-dismiss
            attributes: { 'data-bs-dismiss': 'modal' }
        },
        {
            text: 'Previsualizar',
            className: 'btn btn-info',
            onClick: mostrarPreview
        },
        {
            text: 'Generar PDF',
            className: 'btn btn-primary',
            onClick: imprimirDatos
        }
    ];
    // Limpiamos los botones existentes excepto el de cerrar
    const botonesExistentes = modalFooter.querySelectorAll('button');
    botonesExistentes.forEach(boton => {
        if (boton.classList.contains('btn-secondary')) return; // Mantenemos el botón de cerrar
        modalFooter.removeChild(boton);
    });

	buttons.forEach(buttonConfig => {
	    const button = document.createElement('button');
	    button.textContent = buttonConfig.text;
	    button.className = buttonConfig.className;
	    button.type = 'button';
	    
	    if (buttonConfig.onClick) {
	        button.onclick = buttonConfig.onClick;
	    }
	    
	    // Agregamos cualquier atributo adicional que necesite el botón
	    if (buttonConfig.attributes) {
	        Object.entries(buttonConfig.attributes).forEach(([attr, value]) => {
	            button.setAttribute(attr, value);
	        });
	    }
	    
	    modalFooter.appendChild(button);
	});

    // Mostramos el modal
	const modalElement = document.getElementById('datosTransportadora');
    const modal = bootstrap.Modal.getInstance(modalElement) || new bootstrap.Modal(modalElement);
    modal.show();
}

/**
 * Función que muestra la vista previa de la remisión
 * Esta función se llama cuando el usuario hace clic en el botón de previsualizar
 */
async function mostrarPreview() {
    try {
        // Obtenemos el ID de la remisión y otros datos básicos
        const idRemision = document.getElementById('noRm').value;
        const fechaCreacion = document.getElementById('fechaCreacion').value;
        const opSel = document.getElementById('opSel').value;
        const cliente = document.getElementById('cliente').value;

        // Recopilamos todos los datos del formulario de transporte
        const datosTransporte = {
            nombre: document.getElementById('transportadora').value || '',
            vehiculo: document.getElementById('vehiculo').value || '',
            conductor: document.getElementById('conductor').value || '',
            placa: document.getElementById('placa').value || '',
            cedula: document.getElementById('cedula').value || '',
            pase: document.getElementById('pase').value || '',
            celular: document.getElementById('celular').value || '',
            pesoEntrada: document.getElementById('pesoEntrada').value || '0',
            pesoSalida: document.getElementById('pesoSalida').value || '0',
            horaEntrada: document.getElementById('horaEntrada').value || '',
            horaSalida: document.getElementById('horaSalida').value || ''
        };

        // Actualizamos la información básica en el modal de vista previa
        actualizarInformacionBasica(idRemision, fechaCreacion, opSel, cliente);

        // Actualizamos la información del transporte
        actualizarInformacionTransporte(datosTransporte);

        // Actualizamos la tabla de detalles
        await actualizarTablaDetalles(idRemision);
		
		const previewModalFooter = document.querySelector('#previewRemision .modal-footer');
        previewModalFooter.innerHTML = `
            <button type="button" class="btn btn-secondary" onclick="regresarADatosTransporte()">
                <i class="fas fa-arrow-left"></i> Regresar a Datos
            </button>
            <button type="button" class="btn btn-success" onclick="imprimirDatos()">
                <i class="fas fa-file-pdf"></i> Generar PDF
            </button>
        `;

        // Ocultamos el modal de datos de transporte
        const modalTransporte = bootstrap.Modal.getInstance(document.getElementById('datosTransportadora'));
        modalTransporte.hide();

        // Mostramos el modal de vista previa
		const previewModalElement = document.getElementById('previewRemision');
        if (previewModalElement) {
            // Si ya existe una instancia del modal, la usamos
            const existingModal = bootstrap.Modal.getInstance(previewModalElement);
            if (existingModal) {
                existingModal.show();
            } else {
                // Si no existe una instancia, creamos una nueva
                const newModal = new bootstrap.Modal(previewModalElement, {
                    backdrop: true,
                    keyboard: true,
                    focus: true
                });
                newModal.show();
            }
        }

    } catch (error) {
        console.error('Error al mostrar la vista previa:', error);
    }
}

// Función para regresar al modal de datos de transporte
function regresarADatosTransporte() {
    // Ocultamos el modal de previsualización
    const previewModal = bootstrap.Modal.getInstance(document.getElementById('previewRemision'));
    previewModal.hide();

    // Mostramos nuevamente el modal de datos de transporte
    // Usamos setTimeout para evitar problemas con la animación de los modales
    setTimeout(() => {
        const modalTransporte = bootstrap.Modal.getInstance(document.getElementById('datosTransportadora'));
        if (modalTransporte) {
            modalTransporte.show();
        } else {
            // Si por alguna razón el modal no existe, lo creamos nuevamente
            mostrarModal();
        }
    }, 150);
}

// Función auxiliar para actualizar el contenido de la previsualización
function actualizarContenidoPreview(idRemision, fechaCreacion, opSel, cliente, datosTransporte) {
    // Actualizamos todos los campos de la previsualización como antes...
    document.getElementById('preview-remision-number').textContent = `RM-${idRemision}`;
    document.getElementById('preview-cliente').textContent = cliente;
    document.getElementById('preview-op').textContent = opSel;
    document.getElementById('preview-fecha').textContent = fechaCreacion;

    // Información del transporte
    document.getElementById('preview-transportadora').textContent = datosTransporte.nombre;
    document.getElementById('preview-conductor').textContent = datosTransporte.conductor;
    document.getElementById('preview-cedula').textContent = datosTransporte.cedula;
    document.getElementById('preview-celular').textContent = datosTransporte.celular;
    document.getElementById('preview-vehiculo').textContent = datosTransporte.vehiculo;
    document.getElementById('preview-placa').textContent = datosTransporte.placa;
    document.getElementById('preview-pase').textContent = datosTransporte.pase;
    
    // Datos de peso y hora
    document.getElementById('preview-peso-entrada').textContent = datosTransporte.pesoEntrada;
    document.getElementById('preview-peso-salida').textContent = datosTransporte.pesoSalida;
    document.getElementById('preview-hora-entrada').textContent = datosTransporte.horaEntrada;
    document.getElementById('preview-hora-salida').textContent = datosTransporte.horaSalida;

    // Calculamos y mostramos el peso neto
    const pesoNeto = (parseFloat(datosTransporte.pesoSalida) - parseFloat(datosTransporte.pesoEntrada)).toFixed(2);
    document.getElementById('preview-peso-neto').textContent = pesoNeto;
}


/**
 * Actualiza la información básica en el modal de vista previa
 */
function actualizarInformacionBasica(idRemision, fecha, op, cliente) {
    document.getElementById('preview-remision-number').textContent = `RM-${idRemision}`;
    document.getElementById('preview-cliente').textContent = cliente;
    document.getElementById('preview-op').textContent = op;
    document.getElementById('preview-fecha').textContent = fecha;
}

/**
 * Actualiza la información del transporte en el modal de vista previa
 */
function actualizarInformacionTransporte(datosTransporte) {
    // Información del transportista
    document.getElementById('preview-transportadora').textContent = datosTransporte.nombre;
    document.getElementById('preview-conductor').textContent = datosTransporte.conductor;
    document.getElementById('preview-cedula').textContent = datosTransporte.cedula;
    document.getElementById('preview-celular').textContent = datosTransporte.celular;

    // Información del vehículo
    document.getElementById('preview-vehiculo').textContent = datosTransporte.vehiculo;
    document.getElementById('preview-placa').textContent = datosTransporte.placa;
    document.getElementById('preview-pase').textContent = datosTransporte.pase;

    // Información de pesos y horarios
    document.getElementById('preview-peso-entrada').textContent = datosTransporte.pesoEntrada;
    document.getElementById('preview-peso-salida').textContent = datosTransporte.pesoSalida;
    document.getElementById('preview-hora-entrada').textContent = datosTransporte.horaEntrada;
    document.getElementById('preview-hora-salida').textContent = datosTransporte.horaSalida;

    // Calculamos y mostramos el peso neto
    const pesoNeto = (parseFloat(datosTransporte.pesoSalida) - parseFloat(datosTransporte.pesoEntrada)).toFixed(2);
    document.getElementById('preview-peso-neto').textContent = pesoNeto;
}

/**
 * Actualiza la tabla de detalles en el modal de vista previa
 */
async function actualizarTablaDetalles(idRemision) {
    // Obtenemos los detalles de la remisión
    const detalles = await obtenerDetalleRemision(idRemision);
    
    // Obtenemos la referencia a la tabla de detalles
    const tablaDetalles = document.getElementById('preview-detalles');
    
    // Limpiamos la tabla existente
    tablaDetalles.innerHTML = '';

    // Agregamos cada detalle a la tabla
    detalles.forEach((detalle, index) => {
        const fila = document.createElement('tr');
        fila.innerHTML = `
            <td class="text-center">${index + 1}</td>
            <td class="text-center">${detalle.idItemOp}</td>
            <td class="text-center">${detalle.marca || '-'}</td>
            <td>${detalle.descripcion}</td>
            <td class="text-center">${detalle.cant}</td>
            <td class="text-center">${detalle.undEmpaque || '-'}</td>
            <td class="text-center">${detalle.peso.toFixed(3)}</td>
            <td class="text-center">${(detalle.peso * detalle.cant).toFixed(3)}</td>
        `;
        tablaDetalles.appendChild(fila);
    });
}

/**
 * Función auxiliar para formatear números con separador de miles y decimales
 */
function formatearNumero(numero, decimales = 2) {
    return numero.toLocaleString('es-CO', {
        minimumFractionDigits: decimales,
        maximumFractionDigits: decimales
    });
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
    fetch(`/almacen/remisiones/${idRemision}/pdf/generar`, {
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