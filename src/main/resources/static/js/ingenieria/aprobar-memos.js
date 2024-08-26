let idMemoSelected
document.addEventListener('DOMContentLoaded', function(){
	let tbodyRemisiones = document.getElementById('body-memos')
	let rows = tbodyRemisiones.querySelectorAll('tr')
	rows.forEach(row=>{
		row.addEventListener('click', async function(){
			idMemoSelected = parseInt(row.cells[0].textContent.split('-')[1])
			document.getElementById('noMemo').value = 'M-'+idMemoSelected
			document.getElementById('proyecto').value = row.cells[2].textContent 
			document.getElementById('opSel').value = row.cells[3].textContent
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
	
	detallesMemo.forEach(detalle=>{
		let row = document.createElement('tr')
		
		let cellItem = document.createElement('td')
		cellItem.textContent = detalle.itemOp.id
		row.appendChild(cellItem)
		
		let cellDescripcion = document.createElement('td')
		cellDescripcion.textContent = detalle.itemOp.descripcion
		row.appendChild(cellDescripcion)
		
		let cellCant = document.createElement('td')
		cellCant.textContent = detalle.cantidad
		row.appendChild(cellCant)
		
		let cellAccion = document.createElement('td')
		cellAccion.textContent = detalle.operacion
		row.appendChild(cellAccion)
		
		tbody.appendChild(row)
	})	
	
	const observacion = document.getElementById('observaciones')
	observacion.value = detallesMemo[0].observacion
	document.getElementById('obsContainer').removeAttribute('hidden')
	spinner.setAttribute('hidden', '')
	
}

async function aprobarMemo(){
	spinner.removeAttribute('hidden')
	try{
		console.log(idMemoSelected)
		const response = await fetch(`/ingenieria/memos/${idMemoSelected}/aprobar-memo`,{
			method: 'POST',
			headers:{
				'Content-Type': 'application/json'
			}
		})
		
	    if(!response.ok){
			console.log(response)
			mostrarAlert(response.message, 'danger')
			throw new Error(response.message)
		}
		const memo = await response.json()
		
		localStorage.setItem('alertMessage', JSON.stringify({
            message: `Se aprobó con éxito el memorando M-${memo.id}`,
            type: 'success'
        }))
        
        window.location.reload()
	}catch (error){
		console.error('Ocuriio un error al tratar de aprobar el memo:', error)
	}finally{
		spinner.setAttribute('hidden', '')
	}
}

function mostrarAlert(mensaje, tipo){
	const alertElement = document.createElement('div')
    	alertElement.className = `alert alert-${tipo} alert-dismissible fade show fixed-top`
    	alertElement.role = 'alert'
    	alertElement.style.zIndex = '10000'
        alertElement.innerHTML = `
        <strong>${mensaje}</strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>`

        document.body.appendChild(alertElement)
}

function checkAndShowAlert() {
    const alertData = localStorage.getItem('alertMessage')
    if (alertData) {
        const { message, type } = JSON.parse(alertData)
        mostrarAlert(message, type);
        localStorage.removeItem('alertMessage');
    }
}

document.addEventListener('DOMContentLoaded', checkAndShowAlert)