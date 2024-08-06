document.addEventListener('DOMContentLoaded', function() {
    const seleccionarTodos = document.getElementById('seleccionarTodos');
    const checkboxes = document.querySelectorAll('.seleccionar-formulario');
    const btnExportarExcel = document.getElementById('exportarExcel');
    const btnExportarPdf = document.getElementById('exportarPdf');

    seleccionarTodos.addEventListener('change', function() {
        checkboxes.forEach(checkbox => checkbox.checked = this.checked);
    });

    function getSelectedIds() {
        return Array.from(checkboxes)
            .filter(checkbox => checkbox.checked)
            .map(checkbox => checkbox.value);
    }

  btnExportarExcel.addEventListener('click', function() {
        const ids = getSelectedIds();
        if (ids.length === 0) {
            alert('Por favor, seleccione al menos un formulario.');
            return;
        }
        exportarExcel('/calidad/exportar-excel', ids);
    })

    btnExportarPdf.addEventListener('click', function() {
        const ids = getSelectedIds();
        if (ids.length === 0) {
            alert('Por favor, seleccione al menos un formulario.');
            return;
        }
        exportar('/calidad/exportar-pdf', ids);
    });
	
	function exportarExcel(url, ids) {
	        fetch(url, {
	            method: 'POST',
	            headers: {
	                'Content-Type': 'application/json',
	            },
	            body: JSON.stringify(ids)
	        })
	        .then(response => {
	            if (!response.ok) {
	                throw new Error('Network response was not ok');
	            }
	            return response.blob();
	        })
	        .then(blob => {
	            const url = window.URL.createObjectURL(blob);
	            const a = document.createElement('a');
	            a.style.display = 'none';
	            a.href = url;
	            a.download = 'reporte_calidad.xlsx';
	            document.body.appendChild(a);
	            a.click();
	            window.URL.revokeObjectURL(url);
	        })
	        .catch(error => console.error('Error:', error));
	    }

    function exportar(url, ids) {
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(ids)
        })
        .then(response => response.blob())
        .then(blob => {
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.style.display = 'none';
            a.href = url;
            a.download = 'formularios_calidad.zip';
            document.body.appendChild(a);
            a.click();
            window.URL.revokeObjectURL(url);
        })
        .catch(error => console.error('Error:', error));
    }
});