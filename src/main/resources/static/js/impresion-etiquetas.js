let listCheck
document.addEventListener("DOMContentLoaded", function() {
	document.getElementById("id-op").addEventListener('change', async function(){
		if (this.value !== '') { 
			const idOp = this.value
			const itemsOp = await obtenerItemsOp(idOp)
			llenarTablaDetalleOp(itemsOp)
			listCheck = document.querySelectorAll('input[type=checkbox][id^="checkbox_"]')
			adjustColumnWidths()       
	    }
	})
	
	let selectAllCheckbox = document.getElementById('select-all')
	selectAllCheckbox.addEventListener('change', function(){
		let isChecked = selectAllCheckbox.checked
		    for(let checkbox of listCheck){
				checkbox.checked = isChecked
			}
	})
});

async function obtenerItemsOp(idOp){
	try {
		const response = await fetch(`op/${idOp}/items`)
		const data = await response.json()
		return data
	} catch (error) {
		console.error(error)
	}
}


function llenarTablaDetalleOp(itemsOp){
	let tBody = document.getElementById('body-detalle-materia-prima')
	tBody.innerHTML = ''
	itemsOp.forEach((item, index)=>{
		let row = document.createElement('tr')
		
		let cellNum = document.createElement('td')
		cellNum.innerHTML = `<input type="checkbox" value="${index + 1}" id="checkbox_${index + 1}" name="checkbox_${index + 1}">`
		row.appendChild(cellNum)
		
		let cellRefItem = document.createElement('td')
		cellRefItem.textContent = item.id + '-' + item.idItemFab
		cellRefItem.setAttribute("data-idOp", item.idOpIntegrapps)
		row.appendChild(cellRefItem)
		
		let cellDescripcion = document.createElement('td')
		cellDescripcion.textContent = item.descripcion
		row.appendChild(cellDescripcion)
				
		let cellCant = document.createElement('td')
		cellCant.textContent = item.cant
		row.appendChild(cellCant)
		
		let cellNoEtiquetas = document.createElement('td')
		cellNoEtiquetas.innerHTML = `<input type=number class="form-control" min="1" oninput="validarValor(this)">`
		cellNoEtiquetas.classList.add('size-s')
		row.appendChild(cellNoEtiquetas)	
		
		tBody.appendChild(row)
	})
}

function validarValor(input) {
  if (input.value < 1) {
    input.value = ''; // Limpia el campo si el valor es menor que 1
  }
}

let modal
function mostrarModal(dataItems) {    
    modal = new bootstrap.Modal('#detalleImpresionModal', {
		  keyboard: false
		})
	document.getElementById("tipo-etiqueta").value = ''
	//document.getElementById('cant-etiquetas').value = ''
	document.getElementById('btn-imprimir').addEventListener('click', function() {
		imprimirEtiquetas(dataItems);
	});
	modal.show()
    
}

async function imprimirEtiquetas(dataItems){
	const tipoEtiqueta = document.getElementById("tipo-etiqueta").value
	//const cantEtiquetas = Number(document.getElementById('cant-etiquetas').value)
	
	if(tipoEtiqueta == ''){
		mostrarAlert("Debe seleccionar un tipo de etiqueta antes de imprimir.", "danger")
		return
	}

	dataItems.forEach(item => {
		item.tipoEtiqueta = tipoEtiqueta
	})
	
	console.log(dataItems)
	const request = await fetch(`imprimir-etiquetas`,{
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(dataItems),
	})
	
	const response = await request.text()
	console.log(response)
	if(request.ok){
		window.location.reload()
	} else {
		console.error("Error al enviar la solicitud:", request.status)
	}
	
	
	
	modal.hide()
}

function obteneSeleccion(){
	console.log("imprimiendo")
	const checkboxesMarcadosTodos = document.querySelectorAll('input[type="checkbox"]:checked')
	const checkboxesMarcados = [...checkboxesMarcadosTodos].slice(1, -1)
	let listCheckboxes = []
	for (const element of checkboxesMarcados) {
		const check = element
		const fila = check.closest('tr')
		const idItemOp = fila.cells[1].textContent.split('-')[0]
		const idItemFab = fila.cells[1].textContent.split('-')[1]
		const idOp = fila.cells[1].getAttribute("data-idOp")
		const inputCantEtiquetas = fila.cells[4].querySelector('input') != null ? fila.cells[4].querySelector('input'): null
		if(inputCantEtiquetas == null || inputCantEtiquetas.value == ''){
			mostrarAlert("Debe digitar las cantidades de etiquetas a imprimir de todos los elementos seleccionados.", "danger")
			return
		}else{
			const dataImpresion = {
				idItemFab: idItemFab,
				idItemOp: idItemOp,
				idOp: idOp,
				cantEtiquetas: inputCantEtiquetas.value
			}
			listCheckboxes.push(dataImpresion)
		}				
	}
	mostrarModal(listCheckboxes)
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

function adjustColumnWidths() {
  const table = document.getElementById('detalle-op');
  const columns = table.querySelectorAll('th, td');

  // Calculate maximum width for each column
  const maxColumnWidths = {};
  for (const column of columns) {
    const columnIndex = column.cellIndex;
    const currentWidth = column.offsetWidth;
    if (!maxColumnWidths[columnIndex]) {
      maxColumnWidths[columnIndex] = 0;
    }
    maxColumnWidths[columnIndex] = Math.max(maxColumnWidths[columnIndex], currentWidth);
  }

  // Set adjusted widths back to the columns
  for (const columnIndex in maxColumnWidths) {
    const width = maxColumnWidths[columnIndex];
    const selector = `th:nth-child(${columnIndex + 1}), td:nth-child(${columnIndex + 1})`;
    const cells = table.querySelectorAll(selector);
    for (const cell of cells) {
      cell.style.width = `${width}px`;
    }
  }
}