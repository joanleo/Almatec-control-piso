
const fechaDocInput = document.getElementById('fechaDoc')
const fechaActual = new Date().toISOString().split('T')[0]
fechaDocInput.value = fechaActual

document.getElementById('id-op').addEventListener('change', async function () {
	if (this.value !== '') { 
		const idOP = this.options[this.selectedIndex].value
        console.log(idOP)
        const lista = await obtenerListaMateriales(idOP)
        fillTableListaMateriales(lista)
        console.log(lista)           
    }
})

function fillTableListaMateriales(lista) {
    let tbody = document.getElementById("body-lista-materiales")
    tbody.innerHTML = ''
    lista.forEach((item, index) => {
        let row = document.createElement('tr')

        let cellIndex = document.createElement('td')
        cellIndex.textContent = index + 1
        row.appendChild(cellIndex)

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

        let cellCantPend = document.createElement('td')
        cellCantPend.textContent = item.cantRequeridaActualizada - item.cantEntregada - item.cantExistencia
        row.appendChild(cellCantPend)

        row.addEventListener('mouseover', function() {
            row.style.cursor = 'pointer'
        })

        row.addEventListener('mouseout', function() {
            row.style.cursor = 'default'
        })

        row.addEventListener('click', async function() {
            console.log(tbody)
            if (tbody.id == 'body-lista-materiales') {
				document.getElementById("select").removeAttribute("hidden")
				document.getElementById("itemSelec").value = item.descripcion
				document.getElementById("cantItemSelec").value = item.cantRequeridaActualizada - item.cantEntregada - item.cantExistencia
				document.getElementById("umSelec").textContent = item.um
                console.log("Debera actualizar tabla de disponible")
                const filtro = {
                    "codigo": item.codigoErp,
                    "um": '',
                    "lote": ''
                }
                const items = await getItemsDispon(filtro)
                fillTableDisponible(items)
            }
        })

        tbody.appendChild(row)
    })
}

async function obtenerListaMateriales(idOP){
	spinner.removeAttribute('hidden')
	const response = await fetch(`/produccion/listas-materiales/ordenes-produccion/${idOP}`)
	const data = await response.json()
	spinner.setAttribute('hidden', '')
    return data
}
 

async function getItemsDispon(filtro){
	spinner.removeAttribute('hidden')
	const response = await fetch('/produccion/items/disponibilidad', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(filtro)
    })

    const data = await response.json()
    spinner.setAttribute('hidden', '')
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

        /*let cellDescripcion = document.createElement('td')
        cellDescripcion.textContent = item.descripcionItem
        row.appendChild(cellDescripcion)*/
        
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
	    selectButton.textContent = 'Agregar'
	    selectButton.classList.add('btn', 'btn-primary')
	    selectButton.addEventListener('click', function(event) {
			event.preventDefault()
		    if (itemsSeleccionados.has(item.idItem)) {
		        console.log('El item ya ha sido seleccionado:', item)
		        return 
		    }
		    
	        console.log('Item seleccionado:', item)
            let codigo = item.idItem
            let descripcion = item.descripcionItem
            let um = item.um
            let bodega = item.bodega
            let lote = item.lote
            let disponible = item.disponible
            const idBodega = item.idBodega

            let newRow = document.createElement('tr')
			let uniqueId = 'cantSol_' + lote + disponible
		    itemsSeleccionados.add(uniqueId)
		       
			
            newRow.innerHTML = `
                <td>${codigo}</td>
                <td>${descripcion}</td>
                <td>${um}</td>
                <td data-idBodega="${idBodega}">${bodega}</td>
                <td>${lote}</td>
                <td>${disponible}</td>
                <td><input class="form-control" type='number' id='${uniqueId}' min="1" max="${disponible}" step="0.001"required /></td>
                <td><button class="btn btn-danger" onclick=eliminarFila(this)>Eliminar</button></td>
            `

            document.getElementById('detalle-solicitud').appendChild(newRow)
	    })
	    cellCheck.appendChild(selectButton)
		
	    row.appendChild(cellCheck)
        tbody.appendChild(row)
	})
    
}

function eliminarFila(button){
	let row = button.parentNode.parentNode
	row.parentNode.removeChild(row)
}


document.getElementById("submitForm").addEventListener('submit', function(event){
event.preventDefault()})

function enviarSolicitud() {
    // Obtén los datos del formulario
    const idusuario = document.querySelector('#usuarioId').value
    const fechaSol = document.querySelector('#fechaDoc').value
    let dto = {
		solicitud:{
	        //fechaDoc: fechaSol,
	        consecutivo: document.querySelector('#noSol').value,
	        idOp: document.querySelector('#id-op').value,
	        idUsuarioSol: idusuario,
	        bodegaErp:""	
		},
        detalles: []
    }

    const rows = document.querySelectorAll('#detalle-solicitud tr')
    let bodega
    for (const element of rows) {
        const cells = element.querySelectorAll('td')
        bodega = element.cells[3].dataset.idbodega
        let detalle = {
            codigoErp: element.cells[0].textContent,
            descripcion: element.cells[1].textContent,
            undErp: element.cells[2].textContent,
            bodegaEntrega: element.cells[3].dataset.idbodega,
            loteErp: element.cells[4].textContent,
            disponible: parseInt(element.cells[5].textContent),
            cantSol: parseFloat(element.cells[6].querySelector('input').value),
            idUsuarioSol: idusuario,
        }
        console.log(detalle.bodegaEntrega)
        dto.solicitud.bodegaErp = bodega
        dto.detalles.push(detalle)
    }

	console.log(dto)
    fetch('/produccion/materia-prima/solicitud', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dto)
    })
    .then(response => response.json())
    .then(data => {
        console.log(data)
    })
    .catch(error => {
        console.error(error)
    })
}

