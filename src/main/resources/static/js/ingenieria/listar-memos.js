let idMemoSelected
document.addEventListener('DOMContentLoaded', function(){
	let tbodyMemos = document.getElementById('body-memos')
	let rows = tbodyMemos.querySelectorAll('tr')
	rows.forEach(row=>{
		row.addEventListener('click', async function(){
			idMemoSelected = parseInt(row.cells[0].textContent.split('-')[1])
			document.getElementById('noMemo').value = 'M-'+idMemoSelected
			document.getElementById('proyecto').value = row.cells[2].textContent 
			document.getElementById('opSel').value = row.cells[4].textContent
			document.getElementById('cliente').value = row.cells[1].textContent 
			
			document.getElementById('memoSel').removeAttribute('hidden')
			
			const detallesMemo = await obtenerDetalleMemo(idMemoSelected)
			fillTableDetalleMemo(detallesMemo)
			
		})
	})
})

async function obtenerDetalleMemo(idMemo){
	spinner.removeAttribute('hidden')
	try{
		const request = await fetch(`/ingenieria/memos/${idMemo}/detalle-memo`)
		if(!request.ok){
			console.log(await request.json())
			throw new Error("Error al tratar de obtener el detalle del memo")
		}
		const response = await request.json()
		spinner.setAttribute('hidden', '')
		return response
	}catch (error){
		console.log(error)
	}
}

function fillTableDetalleMemo(detallesMemo){
	spinner.removeAttribute('hidden')
	let tbody = document.getElementById('body-detalle-memo')
	tbody.innerHTML = ''
	
	if (detallesMemo && detallesMemo.length > 0) {
		detallesMemo.forEach(detalle=>{
			let row = document.createElement('tr')
			
			let cellItem = document.createElement('td')
			cellItem.textContent = detalle.idItemOp
			row.appendChild(cellItem)
			
			let cellDescripcion = document.createElement('td')
			cellDescripcion.textContent = detalle.descripcion
			row.appendChild(cellDescripcion)
			
			let cellCant = document.createElement('td')
			cellCant.textContent = detalle.cantidad
			row.appendChild(cellCant)
			
			let cellAccion = document.createElement('td')
			cellAccion.textContent = detalle.operacion
			row.appendChild(cellAccion)
			
			tbody.appendChild(row)
		})
		
		const observacion = document.getElementById('observaciones');
        observacion.value = detallesMemo[0].observacion || '';
        document.getElementById('obsContainer').removeAttribute('hidden');
		
	}else {
        let row = document.createElement('tr');
        let cell = document.createElement('td');
        cell.colSpan = 4;
        cell.textContent = 'No hay detalles disponibles para este memo.';
        row.appendChild(cell);
        tbody.appendChild(row);
        
        document.getElementById('obsContainer').setAttribute('hidden', '');
    }
    
    spinner.setAttribute('hidden', '');
	
	
}