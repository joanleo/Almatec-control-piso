let estados = document.querySelectorAll('[id^="estado"]')
estados.forEach(estado => {
	console.log(estado)
	const operarioData  = estado.getAttribute("data-operario")
	const estadoOperario = operarioData.includes("estado=true")
	console.log(estadoOperario)
	console.log("Se crea el div")
    let divBage = document.createElement('div')
    if(estadoOperario){
		divBage.classList.add("finished__badge")				
	}else{
		divBage.classList.add("user__badge")				
	}
	console.log("Texto del div")
	divBage.textContent = estadoOperario ? 'Activo': 'Inactivo'
	estado.appendChild(divBage)
})