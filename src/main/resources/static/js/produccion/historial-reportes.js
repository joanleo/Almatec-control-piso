
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