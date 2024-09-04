document.addEventListener('DOMContentLoaded', async function () {
	await obtenerParametros()
	
	document.getElementById('guardarCambios').addEventListener('click', async function () {
        const parametros = [];
        document.querySelectorAll('#parametros-container input').forEach(input => {
            parametros.push({
                id: input.getAttribute('data-id'),
                nombre: input.getAttribute('data-nombre'),
                valor: input.value
            })
        })
        console.log('Guardando cambios...')
        await guardarCambios(parametros)
	})
	
	window.addEventListener('resize', ajustarLayout)
})

async function obtenerParametros() {
    spinner.removeAttribute('hidden')
    const response = await fetch('/parametros')
    const data = await response.json();

    const parametrosContainer = document.getElementById('parametros-container')
	parametrosContainer.innerHTML = '';
	
    data.forEach((parametro, index) => {
        const div = document.createElement('div');
        div.classList.add('input-group', 'my-3', 'mx-3', 'parametro-item')
        //div.style.width = '46%'
		div.classList.add(index % 2 === 0 ? 'item-left' : 'item-right');
        div.innerHTML = `
		                <span class="input-group-text">${parametro.nombre}</span>
						<input type="text" class="form-control" style="text-align: right;" value="${parametro.valor}" data-id="${parametro.id}" data-nombre="${parametro.nombre}"/>
		            `;
        parametrosContainer.appendChild(div)
    })
	
	ajustarLayout()
	
    spinner.setAttribute('hidden','')
}

function ajustarLayout() {
    const parametrosContainer = document.getElementById('parametros-container');
    parametrosContainer.classList.toggle('dos-columnas', window.innerWidth > 768);
}

async function guardarCambios(parametros){
	spinner.removeAttribute('hidden')
	const response = await fetch('/parametros', {
		                method: 'POST',
		                headers: {
		                    'Content-Type': 'application/json'
		                },
		                body: JSON.stringify(parametros)
		            })
	const dataResponse = await response.json()
    console.log(dataResponse)
    spinner.setAttribute('hidden','')
    mostrarAlert(dataResponse.message, 'success')
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
        
        setTimeout(() => alertElement.remove(), 5000)
}

