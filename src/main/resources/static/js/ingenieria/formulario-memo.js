let itemsOp
let opSelected
const spinner = document.getElementById('spinner');


document.getElementById('id-op').addEventListener('change', async function () {
	if (this.value !== '') { 
		const idOP = this.options[this.selectedIndex].value
        opSelected = idOP
        document.getElementById("body-detalle-memo").innerHTML = ''
        const listaItems = await obtenerItemsOp(idOP)
        itemsOp = listaItems
        showSelect(listaItems)
    }
})

async function obtenerItemsOp(idOp){
	spinner.removeAttribute('hidden')
	try{
		const request = await fetch(`/items-op/id-op-ia/${idOp}`)
		
		if(!request.ok){
			throw new Error(request)
		}
		const response = await request.json()
		return response
	}catch(error){
		console.log(error)
	}finally{
		spinner.setAttribute('hidden', '')
	}
}

function showSelect(listaItems){
	let divContent = document.getElementById("items-op")
	divContent.innerHTML = ""
	
	let divGroup = document.createElement("div")
	divGroup.classList.add("input-group", "size-xxl")
	
	let spanElement = document.createElement("span")
	spanElement.classList.add("input-group-text")
	spanElement.textContent = "Items:"
	divGroup.appendChild(spanElement)
		
	let inputElement = document.createElement("input");
    inputElement.setAttribute("type", "text");
    inputElement.setAttribute("id", "item-selected");
    inputElement.setAttribute("list", "itemsOp");
    inputElement.classList.add("form-control")
	
	let dataListElement= document.createElement('datalist')
	dataListElement.setAttribute('id', 'itemsOp')
	
	listaItems.forEach(item => {
		console.log(item)
	    const option = document.createElement('option')
	    option.textContent = item.descripcion
	    option.setAttribute('id-item', item.id)
	    dataListElement.appendChild(option)
	})
	inputElement.appendChild(dataListElement)
	
	inputElement.addEventListener("keydown", function(event) {
            if (event.key === 'Enter') {
				console.log("key enter")
				const inputValue = event.target.value;
		    	const matchingOption = Array.from(dataListElement.options).find(option => option.textContent === inputValue);
				const idItem = matchingOption.getAttribute('id-item');
                fillTableItems(idItem);
            }
        });
    
    inputElement.addEventListener("change", function(event){
		const inputValue = event.target.value;
    	const matchingOption = Array.from(dataListElement.options).find(option => option.textContent === inputValue);
		const idItem = matchingOption.getAttribute('id-item');
		fillTableItems(idItem)
		})
        	
	
	divGroup.appendChild(inputElement)
	
	divContent.appendChild(divGroup)
}

function agregarItem(){
	let idItem = document.getElementById("idItem").value
	console.log(idItem)
}

function fillTableItems(idItem) {
    let tbody = document.getElementById("body-detalle-memo")
    const item = itemsOp.find(item => item.id == parseInt(idItem))
    
    // Crear una fila con atributos de datos para facilitar el acceso
    let row = document.createElement("tr")
    row.dataset.itemId = item.id
    row.dataset.itemRef = 'GY' + item.idItemFab
    row.dataset.itemDescripcion = item.descripcion
    row.dataset.itemCantOp = item.cant
    
    // Crear celdas con clases que las identifiquen
    let cellId = document.createElement('td')
    cellId.classList.add('cell-id')
    cellId.textContent = item.id
    row.appendChild(cellId)
    
    let cellRef = document.createElement('td')
    cellRef.classList.add('cell-ref')
    cellRef.textContent = 'GY' + item.idItemFab
    row.appendChild(cellRef)
    
    let cellDescripcion = document.createElement('td')
    cellDescripcion.classList.add('cell-descripcion')
    cellDescripcion.textContent = item.descripcion
    row.appendChild(cellDescripcion)
    
    let cellCantOp = document.createElement('td')
    cellCantOp.classList.add('cell-cant-op', 'text-nowrap')
    cellCantOp.textContent = item.cant
    row.appendChild(cellCantOp)
    
    let cellCantMemo = document.createElement("td")
    cellCantMemo.classList.add('cell-cant-memo')
    let inputCantMemo = document.createElement("input")
    inputCantMemo.classList.add("form-control")
    inputCantMemo.type = "number"
    inputCantMemo.min = "1"
    inputCantMemo.oninput = function() {
        if (this.value <= 0) this.value = ''
    }
    cellCantMemo.appendChild(inputCantMemo)
    row.appendChild(cellCantMemo)
    
    let cellAccion = document.createElement("td")
    cellAccion.classList.add('cell-accion')
    let selectAccion = document.createElement("select")
    selectAccion.classList.add("form-control")
    selectAccion.required = true
    
    let optionDefault = document.createElement("option")
    optionDefault.value = ""
    optionDefault.disabled = true
    optionDefault.selected = true
    optionDefault.hidden = true
    optionDefault.textContent = "Seleccionar.."
    
    let optionAdicionar = document.createElement("option")
    optionAdicionar.value = "Adicionar"
    optionAdicionar.textContent = "Adicionar"
    
    let optionRestar = document.createElement("option")
    optionRestar.value = "Restar"
    optionRestar.textContent = "Restar"
    
    selectAccion.appendChild(optionDefault)
    selectAccion.appendChild(optionAdicionar)
    selectAccion.appendChild(optionRestar)
    cellAccion.appendChild(selectAccion)
    row.appendChild(cellAccion)
    
    let cellButton = document.createElement("td")
    cellButton.classList.add('cell-button')
    let button = document.createElement("button")
    button.setAttribute("type", "button")
    button.classList.add("btn", "btn-danger")
    button.onclick = eliminarFila
    button.textContent = "Remover"
    cellButton.appendChild(button)
    row.appendChild(cellButton)
    
    tbody.appendChild(row)
}

function eliminarFila(event){
	event.preventDefault()
	let row = event.target.parentNode.parentNode
	row.parentNode.removeChild(row)
}

document.getElementById("memoForm").addEventListener('submit', function(event){
	event.preventDefault();
	if (validarItems()) {
	    const submitButton = document.getElementById("submitMemo");
	    submitButton.disabled = true;
	    submitButton.textContent = "Creando memorando...";
	    crearMemo();
	} else {
	    mostrarModal('Por favor, asegúrese de agregar al menos un item con cantidad y acción seleccionadas antes de crear el memorando.');
	}
})

function validarItems() {
    const tbody = document.getElementById("body-detalle-memo");
    const rows = tbody.querySelectorAll('tr');
    
    if (rows.length === 0) {
        return false;
    }
    
    for (let row of rows) {
        const cantidad = row.querySelector('.cell-cant-memo input').value;
        const accion = row.querySelector('.cell-accion select').value;
        
        if (cantidad === '' || accion === '') {
            return false;
        }
    }
    
    return true;
}

async function crearMemo() {
    spinner.removeAttribute('hidden');
    console.log("Creando memo");
    const idUsuario = document.getElementById('usuarioId').value;
    let tbody = document.getElementById("body-detalle-memo");
    const rows = tbody.querySelectorAll('tr');
    let items = [];
    
    rows.forEach(row => {
        const item = {
            idItemOp: row.querySelector('.cell-id').textContent,
            cantidad: row.querySelector('.cell-cant-memo input').value,
            operacion: row.querySelector('.cell-accion select').value
        };
        items.push(item);
    });
    
    const observacion = document.getElementById('observaciones').value;
    
    const memo = {
        idOpIntegrapps: opSelected,
        idUsuario: idUsuario,
        observacion: observacion,
        detalles: items
    };
    
    try {
        const response = await fetch(`/ingenieria/memos`, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(memo)
        });
        
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        
        const data = await response.json();
        console.log(data);
        const mensaje = `El memorando con el número de documento M-${data.id} se ha creado correctamente.`;
        mostrarModal(mensaje);
    } catch (error) {
        console.error('Error:', error);
        mostrarModal('Hubo un error al crear el memorando. Por favor, intente nuevamente.');
    } finally {
        spinner.setAttribute('hidden', '');
    }
}

function mostrarModal(mensaje) {
    const modalBody = document.getElementById('respuestaModalBody');
    modalBody.innerHTML = mensaje;
    const modal = new bootstrap.Modal(document.getElementById('respuestaModal'));
    modal.show();
    
	if (mensaje.includes('se ha creado correctamente')) {
        document.getElementById('respuestaModal').addEventListener('hidden.bs.modal', function () {
            spinner.removeAttribute('hidden');
            window.location.reload();
        });
    }
}
