
async function actualizaEstado(event){
	spinner.removeAttribute('hidden')
	const idNovedad = event.target.id
	console.log(idNovedad)
	try{
		const response = await fetch(`/produccion/novedades/${idNovedad}/actualiza`,{
			method: 'POST',
			headers: {
			'Content-type': 'application/json'
			}
		})
		if(!response.ok){
			throw new Error()
		}
		
		const data = await response.json()
		console.log(data)

		
		localStorage.setItem('alertMessage', JSON.stringify({
            message: `Se ha actualizado con exito el estado para la novedad N-${data.idNovedad}`,
            type: 'success'
        }))
        
        window.location.reload()
		
	}catch (error){
		console.log(error)
	} finally{
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
    console.log(alertData)
    if (alertData) {
        const { message, type } = JSON.parse(alertData)
        mostrarAlert(message, type);
        localStorage.removeItem('alertMessage');
    }
}

document.addEventListener('DOMContentLoaded', checkAndShowAlert)