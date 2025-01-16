// Variables globales
let confirmModalAction = null;

// Event listeners y código de inicialización
document.addEventListener('DOMContentLoaded', async function() {
    // Event listeners para las filas de la tabla
    const filas = document.querySelectorAll('#body-solicitudes-materia-prima tr');
    filas.forEach(function(row) {
        row.addEventListener('mouseover', function() {
            row.style.cursor = 'pointer'
        });

        row.addEventListener('mouseout', function() {
            row.style.cursor = 'default'
        });

        row.addEventListener('click', async function() {
            const idSolic = row.children[0].textContent;
            document.getElementById("solSel").removeAttribute("hidden");
            document.getElementById("noSolSel").value = idSolic;
            document.getElementById("fechaSolSel").value = row.children[1].textContent;
            document.getElementById("opSel").value = row.children[2].textContent;
            document.getElementById("usuSolSel").value = row.children[3].textContent;
            const detalleSolic = await obtenerDetalleSolicitud(idSolic);
            llenarTablaDetalle(detalleSolic);
        });
    });
    
    // Event listener para el modal de respuesta
    const respuestaModal = document.getElementById('respuestaModal');
    if (respuestaModal) {
        respuestaModal.addEventListener('hidden.bs.modal', function () {
            window.location.reload();
        });
    }

    // Event listener para el botón de confirmación
    const confirmModalYesBtn = document.getElementById('confirmModalYes');
    if (confirmModalYesBtn) {
        confirmModalYesBtn.addEventListener('click', function() {
            if (confirmModalAction) {
                confirmModalAction();
            }
            bootstrap.Modal.getInstance(document.getElementById('confirmModal')).hide();
        });
    }
});

// Funciones de utilidad
async function obtenerDetalleSolicitud(idSolic) {    
    const response = await fetch(`/almacen/detalle/solicitud/${idSolic}`);    
    const data = await response.json();
    console.log(data);
    return data;
}

function llenarTablaDetalle(detalleSolic) {
    let tbody = document.getElementById("body-detalle-materia-prima");
    tbody.innerHTML = "";
    detalleSolic.forEach(item => {
        let row = document.createElement('tr');
        
        let cellCodigo = document.createElement('td');
        cellCodigo.textContent = item.codigoErp;
        row.appendChild(cellCodigo);
        
        let cellDescripcion = document.createElement('td');
        cellDescripcion.textContent = item.descripcionItem;
        row.appendChild(cellDescripcion);
        
        let cellUm = document.createElement('td');
        cellUm.textContent = item.undErp;
        row.appendChild(cellUm);
        
        let cellBodegaOrigen = document.createElement('td');
        cellBodegaOrigen.textContent = item.bodegaOrigen;
        row.appendChild(cellBodegaOrigen);
        
        let cellBodegaDestino = document.createElement('td');
        cellBodegaDestino.textContent = item.bodegaDestino;
        row.appendChild(cellBodegaDestino);
        
        let cellLote = document.createElement('td');
        cellLote.textContent = item.loteErp;
        row.appendChild(cellLote);
        
        let cellSolic = document.createElement('td');
        cellSolic.textContent = item.cantSol;
        row.appendChild(cellSolic);
               
        tbody.appendChild(row);        
    });
}

function showConfirmModal(message, action) {
    const modal = new bootstrap.Modal(document.getElementById('confirmModal'));
    document.querySelector('#confirmModal .modal-body').textContent = message;
    confirmModalAction = action;
    modal.show();
}

function mostrarModal(mensaje) {
    const modalBody = document.getElementById('respuestaModalBody');
    modalBody.innerHTML = mensaje;
    const modal = new bootstrap.Modal(document.getElementById('respuestaModal'));
    modal.show();
}

/*function mostrarAlert(mensaje, tipo) {
    const alertElement = document.createElement('div');
    alertElement.className = `alert alert-${tipo} alert-dismissible fade show fixed-top`;
    alertElement.role = 'alert';
    alertElement.style.zIndex = '10000';
    alertElement.innerHTML = `
        <strong>${mensaje}</strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>`;

    document.body.appendChild(alertElement);
    
    setTimeout(() => alertElement.remove(), 7000);
}*/

// Funciones de manejo de eventos
function rechazarTransferencia(event) {
    event.preventDefault();
    const idSol = document.getElementById("noSolSel").value;
    if (!idSol) {
        mostrarAlert("Por favor, seleccione una solicitud antes de rechazar.", 'warning');
        return;
    }

    showConfirmModal("¿Está seguro de que desea rechazar esta solicitud?", function() {
        solicitarRechazo(idSol);
    });
}

async function solicitarRechazo(idSol) {
    const spinner = document.getElementById('spinner');
    spinner.removeAttribute('hidden');
    
    const btnTransferir = document.getElementById('btn-tranferir');
    const btnRechazar = document.getElementById('btn-rechazar');
    btnTransferir.disabled = true;
    btnRechazar.disabled = true;
    btnRechazar.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Rechazando...';
    
    try {
        const response = await fetch(`/almacen/solicitudes/${idSol}/rechazar`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        
        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Error al rechazar la solicitud');
        }

        mostrarAlert('Solicitud rechazada', 'success');
        setTimeout(() => window.location.reload(), 2000);
        
    } catch (error) {
        console.error('Ocurrió un error al rechazar la solicitud:', error);
        mostrarAlert('Ocurrió un error al rechazar la solicitud. Por favor, intente nuevamente.', 'danger');
    } finally {
        spinner.setAttribute('hidden', '');
    }
}

function crearTransferencia(event) {
    event.preventDefault();
    const idSol = document.getElementById("noSolSel").value;
    if (!idSol) {
        mostrarAlert("Por favor, seleccione una solicitud antes de transferir.", 'warning');
        return;
    }
    const detalleBody = document.getElementById("body-detalle-materia-prima");
    if (detalleBody.children.length === 0) {
        mostrarAlert("La solicitud debe tener al menos un ítem para poder transferir.", 'warning');
        return;
    }
    console.log("Se hace la solicitud para crear la transferencia");
    solicitarTransferencia(idSol);
}

async function solicitarTransferencia(idSol) {
    const spinner = document.getElementById('spinner');
    spinner.removeAttribute('hidden');
    
    const btnTransferir = document.getElementById('btn-tranferir');
    btnTransferir.disabled = true;
    btnTransferir.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Guardando...';
    
    const btnRechazar = document.getElementById('btn-rechazar');
    btnRechazar.disabled = true;
    btnRechazar.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Guardando...';
    
    try {
        const solicitud = await fetch(`/api/transferencia/${idSol}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });
            
        const response = await solicitud.json();
        if(!solicitud.ok) {
            //console.log(response);
            throw new Error(response.message);
        }
        mostrarModal(response.message);
        
    } catch(error) {
        console.error('Ocurrió un error al tratar de crear la transferencia:', error);
		mostrarAlert(error.message, 'danger');
        //mostrarAlert('Ocurrió un error al crear la transferencia. Por favor, intente nuevamente.', 'danger');
    } finally {
        spinner.setAttribute('hidden', '');
		btnTransferir.disabled = false;
        btnTransferir.innerHTML = 'Transferir'; // O el texto original que tenía
        btnRechazar.disabled = false;
        btnRechazar.innerHTML = 'Rechazar';
    }
}