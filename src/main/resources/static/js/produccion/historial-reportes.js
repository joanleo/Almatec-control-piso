
function reenviarReporte(button) {
    const reporteId = button.getAttribute('data-reporte-id');
	showLoader('Reenviando reporte de produccion...');
    fetch(`/produccion/reenviar-reporte/${reporteId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(async response => {
        const data = await response.json();
        
        if (response.ok) {
            mostrarAlert(data.mensaje, data.tipo);
            setTimeout(() => {
                location.reload();
            }, 2000);
        } else {
            throw new Error(data.mensaje || 'Error al reenviar el reporte');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        mostrarAlert(error.message, 'danger');
    })
	.finally(() => hideLoader()) ;
}

function anularReporte(button) {
    // Get the report ID from the button's data attribute
    const reporteId = button.getAttribute('data-reporte-id');
    
    // Store the ID in the hidden input field
    document.getElementById('anularReporteId').value = reporteId;
    
    // Show the confirmation modal
    const anularModal = new bootstrap.Modal(document.getElementById('anularModal'));
    anularModal.show();
}

function confirmAnular() {
    // Get the report ID from the hidden input field
    const reporteId = document.getElementById('anularReporteId').value;
	console.log(reporteId)
    
    // Show the loader
    showLoader('Anulando reporte de producción...');
    
    // Hide the modal
    const anularModal = bootstrap.Modal.getInstance(document.getElementById('anularModal'));
    anularModal.hide();
    
    // Make the API call to annul the report
    fetch(`/produccion/anular-reporte/${reporteId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(async response => {
        const data = await response.json();
        
        if (response.ok) {
            mostrarAlert(data.mensaje, 'success');
            setTimeout(() => {
                location.reload();
            }, 2000);
        } else {
            throw new Error(data.mensaje || 'Error al anular el reporte');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        mostrarAlert(error.message, 'danger');
    })
    .finally(() => hideLoader());
}