async function getItemsDispon(filtro){
	const response = await fetch('/produccion/items/disponibilidad', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(filtro)
    })

    const data = await response.json()
    return data
}

async function buscarItems(){
	const codigo = document.getElementById("codigoErp").value
	const um = document.getElementById("undErp").value
	const lote = document.getElementById("lote").value
	
	const filtro = {
		"codigo": codigo,
		"um": um,
		"lote": lote
	}
	
	console.log(codigo)
	if(codigo == '' && um == '' && lote == ''){
		document.getElementById("disponible").innerHTML = ''
	}else{
		const items = await getItemsDispon(filtro)
		fillTableDisponible(items)		
	}
	
}
let itemsSeleccionados = new Set()
function fillTableDisponible(items) {
    let tbody = document.getElementById("disponible")
    tbody.innerHTML = ''
    
    items.forEach(item => {
	    
		let row = document.createElement('tr')
		
		let cellCodigo = document.createElement('td')
        cellCodigo.textContent = item.idItem
        row.appendChild(cellCodigo)

        let cellDescripcion = document.createElement('td')
        cellDescripcion.textContent = item.descripcionItem
        row.appendChild(cellDescripcion)
        
        let cellUm = document.createElement('td')
        cellUm.textContent = item.um
        row.appendChild(cellUm)
        
        let cellBodega = document.createElement('td')
        cellBodega.textContent = item.bodega
        row.appendChild(cellBodega)
        
        let cellLote = document.createElement('td')
        cellLote.textContent = item.lote
        row.appendChild(cellLote)
        
        let cellDisponible = document.createElement('td')
        cellDisponible.textContent = item.disponible
        row.appendChild(cellDisponible)
        
        let cellCheck = document.createElement('td')
		let selectButton = document.createElement('button')
	    selectButton.textContent = 'Agregar al listado'
	    selectButton.classList.add('btn', 'btn-primary')
	    selectButton.addEventListener('click', function() {
			event.preventDefault()
		    if (itemsSeleccionados.has(item.idItem)) {
		        console.log('El item ya ha sido seleccionado:', item);
		        return; 
		    }
		    
	        console.log('Item seleccionado:', item)
            let codigo = item.idItem;
            let descripcion = item.descripcionItem;
            let um = item.um;
            let bodega = item.bodega;
            let lote = item.lote;
            let disponible = item.disponible;

            let newRow = document.createElement('tr');
			let uniqueId = 'cantSol_' + lote + disponible
		    itemsSeleccionados.add(uniqueId);
			
            newRow.innerHTML = `
                <td>${codigo}</td>
                <td>${descripcion}</td>
                <td>${um}</td>
                <td>${bodega}</td>
                <td>${lote}</td>
                <td>${disponible}</td>
                <td><input type='number' id='${uniqueId}' required /></td>
            `;

            document.getElementById('detalle-solicitud').appendChild(newRow);
	    });
	    cellCheck.appendChild(selectButton);
		
	    row.appendChild(cellCheck)
        tbody.appendChild(row)
	})
    
}
