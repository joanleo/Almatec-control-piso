let centrosTrabajoPrioridad = []
let centroTrabajoSelectedId
let spinner

document.addEventListener("DOMContentLoaded", function() {
	spinner = document.getElementById('spinner')
	spinner.removeAttribute('hidden')
		
	let numberInputs = document.querySelectorAll("input[type='number']");

	for (const element of numberInputs) {
	    element.addEventListener("input", function() {
	      if (this.value < 1) {
	        this.value = '';
	      }
	    });
	}
	
	spinner.setAttribute('hidden', '')
})

document.addEventListener('DOMContentLoaded',  function() {
    const form = document.getElementById('formCalidad');
    form.addEventListener('submit', async function(e) {
        e.preventDefault();
        
        const formData = new FormData(form);
        const jsonData = {};
                
        for (let [key, value] of formData.entries()) {
			if (key === 'fechaDoc') {
                const date = new Date(value)
                jsonData[key] = date.toISOString()
			}
            jsonData[key] = value			
        }
		jsonData['centroTrabajo'] = document.querySelector('.centro-trabajo').value
		jsonData['idCentroTrabajo'] = Number(document.querySelector('.id-centro-trabajo').value) 
		jsonData['idItem'] = Number(document.querySelector('.item').value)
		jsonData['id'] = document.querySelector('#formId').value
		
        try{
	        const response = await fetch('/calidad/formulario/guardar', {
	            method: 'POST',
	            headers: {
	                'Content-Type': 'application/json',
	            },
	            body: JSON.stringify(jsonData)
	        })
	
			
	        if (!response.ok) {
				const text = await response.text();
                throw new Error(text);
	        }
			
			let data;
            try {
                data = await response.json();
            } catch (jsonError) {
                throw new Error('Error parsing JSON response: ' + jsonError.message);
            }

            console.log(data);
	
			
            if (document.querySelector('#formId').value != '') {
               window.location.href = '/calidad/formularios'
            }else{
				window.location.href = `/produccion/home?alert=success&mensaje=Formulario de calidad guardado exitosamente con numero de documento ${data.id}`	
			} 
			
		}catch(error){
            console.error('Error:', error);
        }
    })
});
			
document.addEventListener('DOMContentLoaded', async function(){
			
	const idcentroTrabajoElement = document.querySelector('.id-centro-trabajo')
	const centroTranajoName = document.querySelector('.centro-trabajo')
	const centroTrabajo = {
		id: Number(idcentroTrabajoElement.value),
		nombre: centroTranajoName.value
	}
	
	console.log(centroTrabajo)
	const form = document.getElementById('formCalidad')
	const titulo = document.createElement('h2')
	titulo.textContent = 'REPORTE CALIDAD ' + centroTrabajo.nombre
	
	document.getElementById('mainContent').insertBefore(titulo, form)
	
	switch(centroTrabajo.id){
		case 2:
		case 3:
			console.log("Punzonadora y omegas")
			mostrarPunzonadoraYOmegas()
			break;
		case 4:
			console.log("Formadora 1a Vigas")
			mostrarVigas()
			break;
		case 5:
			mostrarRefuerzos()
			console.log("Formadora 2 omega refuerzos")
			break;
		case 7:
			mostraCZ()
			console.log("Formadora cz")
			break;
		case 8:
			mostrarBox()
			console.log("Cerradora 1 box")
			break;
		case 16:
		case 17:
			mostrarGranalladoPintura()
			console.log("Granallado y pintura")
			break;
		default:
			break;
	}		 
	 const fechaDocInput = document.getElementById('fechaDoc')
	 const fechaActual = new Date();
     const formattedDate = formateaFecha(fechaActual)
     fechaDocInput.value = formattedDate;
	 console.log(formattedDate)
})

function formateaFecha(fechaActual) {
    const year = fechaActual.getFullYear()
    const month = ('0' + (fechaActual.getMonth() + 1)).slice(-2)
    const day = ('0' + fechaActual.getDate()).slice(-2)
    const hours = ('0' + fechaActual.getHours()).slice(-2)
    const minutes = ('0' + fechaActual.getMinutes()).slice(-2)

    const formattedDate = `${year}-${month}-${day}T${hours}:${minutes}`
    return formattedDate
}

async function fetchCentrosT(){
	try{
		const response = await fetch('/centros-trabajo/listar')
		if(!response.ok){
			throw new Error("Error al obtener los Centros de trabajo")
		}
		const data = await response.json()
		return data
	}catch(error){
		console.error("Error al obtener los Centros de trabajo: ", error)
	}
}

function mostrarPunzonadoraYOmegas(){
	document.getElementById('ralContainer').setAttribute('hidden','')
	document.getElementById('perfBordeContainer').setAttribute('hidden','')
	document.getElementById('perforacionesContainer').setAttribute('hidden','')
	document.getElementById('troqueladoContainer').setAttribute('hidden','')
	document.getElementById('granalladoContainer').setAttribute('hidden','')
	document.getElementById('pasaPruebaContainer').setAttribute('hidden','')
	
	document.getElementById('parametros').removeAttribute('hidden')
}

function mostrarVigas(){
	
	document.getElementById('ralContainer').setAttribute('hidden','')
	document.getElementById('perfBordeContainer').setAttribute('hidden','')
	document.getElementById('perforacionesContainer').setAttribute('hidden','')
	document.getElementById('troqueladoContainer').setAttribute('hidden','')
	document.getElementById('coronaContainer').setAttribute('hidden','')
	document.getElementById('corteContainer').setAttribute('hidden','')
	document.getElementById('punzonadoContainer').setAttribute('hidden','')
	document.getElementById('granalladoContainer').setAttribute('hidden','')
	document.getElementById('pasaPruebaContainer').setAttribute('hidden','')
	
	document.getElementById('parametros').removeAttribute('hidden')
}

function mostrarRefuerzos(){
	document.getElementById('ralContainer').setAttribute('hidden','')
	//document.getElementById('pestanaContainer').setAttribute('hidden','')
	//document.getElementById('aletaContainer').setAttribute('hidden','')
	//document.getElementById('perfBordeContainer').setAttribute('hidden','')
	document.getElementById('perforacionesContainer').setAttribute('hidden','')
	document.getElementById('troqueladoContainer').setAttribute('hidden','')
	document.getElementById('cuadraturaContainer').setAttribute('hidden','')
	document.getElementById('coronaContainer').setAttribute('hidden','')
	document.getElementById('corteContainer').setAttribute('hidden','')
	document.getElementById('granalladoContainer').setAttribute('hidden','')
	document.getElementById('pasaPruebaContainer').setAttribute('hidden','')	
	
	document.getElementById('parametros').removeAttribute('hidden')	
}

function mostraCZ(){
	document.getElementById('ralContainer').setAttribute('hidden','')
	document.getElementById('troqueladoContainer').setAttribute('hidden','')
	document.getElementById('corteContainer').setAttribute('hidden','')
	document.getElementById('punzonadoContainer').setAttribute('hidden','')
	document.getElementById('granalladoContainer').setAttribute('hidden','')
	document.getElementById('pasaPruebaContainer').setAttribute('hidden','')
	
	
	document.getElementById('parametros').removeAttribute('hidden')	
}

function mostrarBox(){
	document.getElementById('ralContainer').setAttribute('hidden','')
	document.getElementById('perfBordeContainer').setAttribute('hidden','')
	document.getElementById('punzonadoContainer').setAttribute('hidden','')
	document.getElementById('troqueladoContainer').setAttribute('hidden','')
	document.getElementById('granalladoContainer').setAttribute('hidden','')
	document.getElementById('pasaPruebaContainer').setAttribute('hidden','')
	
	document.getElementById('parametros').removeAttribute('hidden')	
}

function mostrarCodigo() {
    const select = document.getElementById('loteSelect')
    let codigo = document.getElementById('codErpMatriaPrima')
    const loteSeleccionado = select.options[select.selectedIndex].value
	const lotesConCodigos = JSON.parse(lotesConCodigosString)
    const loteConCodigo = lotesConCodigos.find(function(lote) {
        return lote.loteErp === loteSeleccionado;
    })

    codigo.value = loteConCodigo ? loteConCodigo.codErp : ''
}

function mostrarGranalladoPintura(){
	let loteLabel = document.getElementById('loteLabel')
	if (loteLabel) {
        loteLabel.textContent = 'LOTE PINTURA: '
    }
	document.getElementById('anchoContainer').setAttribute('hidden','')
	document.getElementById('alturaContainer').setAttribute('hidden','')
	document.getElementById('longitudContainer').setAttribute('hidden','')
	document.getElementById('pestanaContainer').setAttribute('hidden','')
	document.getElementById('aletaContainer').setAttribute('hidden','')
	document.getElementById('perfBordeContainer').setAttribute('hidden','')
	document.getElementById('perforacionesContainer').setAttribute('hidden','')
	document.getElementById('punzonadoContainer').setAttribute('hidden','')
	document.getElementById('troqueladoContainer').setAttribute('hidden','')
	document.getElementById('cuadraturaContainer').setAttribute('hidden','')
	document.getElementById('coronaContainer').setAttribute('hidden','')
	document.getElementById('flechaHContainer').setAttribute('hidden','')
	document.getElementById('flechaVContainer').setAttribute('hidden','')
	document.getElementById('corteContainer').setAttribute('hidden','')
	document.getElementById('punzonadoContainer').setAttribute('hidden','')
	
	document.getElementById('espesores').removeAttribute('hidden')	
	document.getElementById('parametros').removeAttribute('hidden')	
}