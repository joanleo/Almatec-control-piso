document.addEventListener("DOMContentLoaded", function() {
	
	document.getElementById('id-op').addEventListener('change', async function () {
		if (this.value !== '') { 
			const idOP = this.options[this.selectedIndex].value
	        const descripcion = this.options[this.selectedIndex].getAttribute('data-op');

	        document.getElementById('descripcion').value = descripcion;
	        document.getElementById('descripcionDiv').hidden = false;
			
	        const lista = await obtenerListaMateriales(idOP)
	        fillTableListaMateriales(lista)
	    }
	})
	
	document.getElementById('exportarExcel').addEventListener('click', function() {
	    const idOP = document.getElementById('id-op').value;
		const descripcion = document.getElementById('descripcion').value
	    if (idOP) {
	        window.location.href = `/programacion/descargar-lista-materiales?idOP=${idOP}&descripcion=${descripcion}`;
	    } else {
	        alert('Por favor, seleccione una OP primero.');
	    }
	});
	
	}
)

async function obtenerListaMateriales(idOP){
	spinner.removeAttribute('hidden')
	const response = await fetch(`/produccion/listas-materiales/ordenes-produccion/${idOP}`)
	const data = await response.json()
	spinner.setAttribute('hidden', '')
    return data
}

function fillTableListaMateriales(lista) {
    let tbody = document.getElementById("body-lista-materiales")
    tbody.innerHTML = ''
    lista.forEach((item) => {
        let row = document.createElement('tr')

        let cellCodigo = document.createElement('td')
        cellCodigo.textContent = item.codigoErp
        row.appendChild(cellCodigo)
        
        let cellDescripcion = document.createElement('td')
        cellDescripcion.textContent = item.descripcion
        row.appendChild(cellDescripcion)

        let cellUm = document.createElement('td')
        cellUm.textContent = item.um
        row.appendChild(cellUm)

        let cellCantReq = document.createElement('td')
        cellCantReq.textContent = item.cantRequeridaActualizada
        row.appendChild(cellCantReq)

        tbody.appendChild(row)
    })
}