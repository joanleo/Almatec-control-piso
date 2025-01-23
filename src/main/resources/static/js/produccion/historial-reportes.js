
function reenviarReporte(button) {
        const reporteId = button.getAttribute('data-reporte-id');
        //if (confirm('¿Está seguro que desea reenviar este reporte?')) {
            fetch(`/produccion/reenviar-reporte/${reporteId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    // Add CSRF token if you're using Spring Security
                    //'X-CSRF-TOKEN': document.querySelector("meta[name='_csrf']").content
                }
            })
            .then(response => {
                if (response.ok) {
                    alert('Reporte reenviado exitosamente');
                    location.reload();
                } else {
                    alert('Error al reenviar el reporte');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Error al reenviar el reporte');
            });
       // }
    }