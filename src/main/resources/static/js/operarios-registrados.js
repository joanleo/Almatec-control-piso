
document.addEventListener('DOMContentLoaded', function() {
	let estados = document.querySelectorAll('[id^="estado"]')
	estados.forEach(estado => {
		console.log(estado)
		const operarioData  = estado.getAttribute("data-operario")
		const estadoOperario = operarioData.includes("estado=true")
	    let divBage = document.createElement('div')
		divBage.classList.add(estadoOperario ? "finished__badge": "canceled__badge")				
		divBage.textContent = estadoOperario ? 'Activo': 'Inactivo'
		estado.appendChild(divBage)
	})
});