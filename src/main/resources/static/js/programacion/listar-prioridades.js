let itemsGlobal = []
let itemsOriginales = []
const pageSize = 10
document.addEventListener('DOMContentLoaded', async function(){
	spinner.removeAttribute('hidden')
	const centrosTrabajoFilter = [3, 4, 5, 6, 7, 8, 9, 17];

	const centrosTrabajo = await fetchCentrosT()
	
	const centrosTrabajoPrioridad = centrosTrabajo.filter(centro=>centrosTrabajoFilter.includes(centro.id))
	const select = document.getElementById('centroTrabajoSelect')
	
	const optionDefault = document.createElement('option')
	optionDefault.value = ''
	optionDefault.text = ''
	optionDefault.disabled = true
	optionDefault.selected = true
	select.appendChild(optionDefault)
	
	centrosTrabajoPrioridad.forEach(centro => {
	  const option = document.createElement('option')
	  option.value = centro.id
	  option.text = centro.nombre
	  select.appendChild(option)
	})
	spinner.setAttribute('hidden', '')
	
	select.addEventListener('change', async function() {
		console.log(this.value)
	    const idCentroTrabajo = this.value;
	    const ordenesProduccionCentroTrabajo = await obtenerOpCentroT(idCentroTrabajo);
	    
		mostrarData(ordenesProduccionCentroTrabajo)
	  })
})

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

async function obtenerOpCentroT(ct){
	spinner.removeAttribute('hidden')
	try{
		const response = await fetch(`/centros-trabajo/${ct}/ordenes-produccion`)
		const data = await response.json()
		data.sort((a, b) => b.idOp - a.idOp)
		//console.log("respuesta ops CT: ",data)
		return data
	}catch(error){
		console.log("Error al tratar de obtener OP en centro de trabajo", error)
	}finally{
		spinner.setAttribute('hidden', '')		
	}
		
}

function mostrarData(data){
	console.log(data);
	let items = data.flatMap(function(op) {
	    return op.items.map(function(item) {
	      return {
	        item: item,
	        cliente: op.cliente,
	        proyecto: op.proyecto,
	        zona: op.zona
	      };
	    });
	  });

	itemsOriginales = [...items]
	llenarTabla(items)
}

function llenarTabla(items) {
  console.log(items);

  // Crea una copia de la lista de items para no modificar el original
  itemsGlobal = items

  // Define el tamaño de página y calcula el número de páginas  
  const numPages = Math.ceil(itemsGlobal.length / pageSize);

  // Crea la paginación
  const pagination = document.createElement('ul');
  pagination.classList.add('pagination', 'justify-content-center');
  for (let i = 1; i <= numPages; i++) {
    const li = document.createElement('li');
    li.classList.add('page-item');
    const a = document.createElement('a');
    a.classList.add('page-link');
    a.href = '#';
    a.textContent = i;
    a.dataset.page = i;
    li.appendChild(a);
    pagination.appendChild(li);
  }
  const paginationDiv = document.getElementById('pagination')
  paginationDiv.innerHTML = ''
  paginationDiv.appendChild(pagination);

  // Agrega un controlador de eventos para detectar clics en los enlaces de paginación
  pagination.addEventListener('click', function(event) {
    event.preventDefault();
    if (event.target.tagName === 'A') {
      const page = parseInt(event.target.dataset.page);
      mostrarPagina(page);
    }
  });

  // Muestra la primera página
  mostrarPagina(1);
  
  document.getElementById("resultsTable").removeAttribute('hidden')
  document.getElementById("filtroForm").removeAttribute('hidden')
  // Función para mostrar una página
  
}

function filtrarTabla() {
  // Obtiene los valores de los campos de búsqueda
  const descripcion = document.getElementById('descripcion').value.toLowerCase();
  const cantidad = document.getElementById('cantidad').value || '';
  const proyecto = document.getElementById('proyecto').value.toLowerCase();
  const zona = document.getElementById('zona').value.toLowerCase();
  const prioridad = document.getElementById('prioridad').value || '';
  
  console.log(descripcion)
  const itemsFiltrados = itemsOriginales.filter(item =>
          item.item.item_desc.toLowerCase().includes(descripcion) &&
          (cantidad === '' || item.item.cant_req.toString().includes(cantidad)) &&
          item.proyecto.toLowerCase().includes(proyecto) &&
          item.zona.toLowerCase().includes(zona) &&
          (prioridad === '' || (item.item.prioridad !== null && item.item.prioridad.toString().includes(prioridad)))
      );
  console.log(itemsFiltrados)
	
  llenarTabla(itemsFiltrados)
}

function mostrarPagina(page) {
    // Calcula el índice de inicio y fin de la página actual
    const start = (page - 1) * pageSize;
    const end = start + pageSize;

    // Limpia el cuerpo de la tabla
    const body = document.getElementById("body-detalle-programacion");
    body.innerHTML = '';

    // Agrega las filas de la página actual a la tabla
    for (let i = start; i < end && i < itemsGlobal.length; i++) {
      const item = itemsGlobal[i];

      const row = document.createElement('tr');

      const cellCheck = document.createElement('td');
      const divCheck = document.createElement('div');
      divCheck.classList.add('form-check');
      const check = document.createElement('input');
      check.classList.add('form-check-input', 'item-checkbox');
      check.type = 'checkbox';
      check.id = item.item.item_op_id + '-' + item.item.item_id;
      divCheck.appendChild(check);
      cellCheck.appendChild(divCheck);
      row.appendChild(cellCheck);

      const cellDescripcion = document.createElement('td');
      cellDescripcion.textContent = item.item.item_desc;
      row.appendChild(cellDescripcion);

      const cellCant = document.createElement('td');
      cellCant.textContent = item.item.cant_req;
      row.appendChild(cellCant);

      const cellProyecto = document.createElement('td');
      cellProyecto.textContent = item.proyecto;
      row.appendChild(cellProyecto);

      const cellZona = document.createElement('td');
      cellZona.textContent = item.zona;
      row.appendChild(cellZona);

      const cellPrioridad = document.createElement('td');
      cellPrioridad.textContent = item.item.prioridad;
      row.appendChild(cellPrioridad);
	  
	  const cellBoton = document.createElement('td');
      const boton = document.createElement('button');
      boton.classList.add('btn', 'btn-sm');
      if (item.item.prioridad !== null) {
          boton.textContent = 'Editar';
          boton.classList.add('btn-primary');
      } else {
          boton.textContent = 'Asignar';
          boton.classList.add('btn-success');
      }
      boton.onclick = function() {
          // Aquí puedes añadir la lógica para editar o asignar
          console.log('Botón clickeado para el item:', item);
      };
      cellBoton.appendChild(boton);
      row.appendChild(cellBoton);

      body.appendChild(row);
    }

	const currentPage = document.querySelector('.page-item.active .page-link');
	  if (currentPage) {
	    currentPage.parentNode.classList.remove('active');
	  }
	  
    // Resalta el enlace de la página actual
    const pageLink = document.querySelector(`a[data-page="${page}"]`);
    pageLink.parentNode.classList.add('active');
	
	const tableContainer = document.querySelector('#resultsTable').parentNode;
    if (!document.querySelector('#btnAsignarMultiple')) {
        const btnAsignarMultiple = document.createElement('button');
        btnAsignarMultiple.id = 'btnAsignarMultiple';
        btnAsignarMultiple.textContent = 'Asignar seleccionados';
        btnAsignarMultiple.classList.add('btn', 'btn-primary', 'mb-3');
        btnAsignarMultiple.style.display = 'none';
        btnAsignarMultiple.onclick = function() {
            // Aquí puedes añadir la lógica para asignar múltiples items
            console.log('Asignar múltiples items');
        };
        tableContainer.insertBefore(btnAsignarMultiple, tableContainer.firstChild);
    }
	
	document.getElementById('checkAll').addEventListener('change', handleSelectAll);
  }

  function handleSelectAll() {
	console.log("check clickeado")
      const checkboxes = document.querySelectorAll('.item-checkbox');
      const selectAllCheckbox = document.querySelector('#checkAll');
      checkboxes.forEach(checkbox => {
          checkbox.checked = selectAllCheckbox.checked;
      });
      updateAsignarButtonVisibility();
  }
  
  function updateAsignarButtonVisibility() {
      const checkboxes = document.querySelectorAll('.item-checkbox');
      const btnAsignarMultiple = document.querySelector('#btnAsignarMultiple');
      const anyChecked = Array.from(checkboxes).some(checkbox => checkbox.checked);
      btnAsignarMultiple.style.display = anyChecked ? 'block' : 'none';
  }
  
  document.addEventListener('change', function(e) {
      if (e.target && e.target.classList.contains('item-checkbox')) {
          updateAsignarButtonVisibility();
      }
  })
/*function debounce(func, delay) {
    let timeout;
    return function(...args) {
        clearTimeout(timeout);
        timeout = setTimeout(() => func.apply(this, args), delay);
    };
}

function buscar() {
    let form = document.getElementById('filtroForm');
	const formData = new FormData(form);
    const params = new URLSearchParams(formData).toString();
    const url = `${form.action}?${params}`;
	
	fetch(url, {
	        method: 'GET',
	        headers: {
	            'X-Requested-With': 'XMLHttpRequest'
	        }
	    })
	    .then(response => response.text())
	    .then(html => {
	        const parser = new DOMParser();
	        const doc = parser.parseFromString(html, 'text/html');
			console.log(doc)
	        const newTable = doc.getElementById('resultsTable');
	        document.getElementById('resultsTable').innerHTML = newTable.innerHTML;
	    })
	    .catch(error => console.error('Error:', error));
}

const descripcionInput = document.getElementById('descripcion');
const cantidadInput = document.getElementById('cantidad');
const proyectoInput = document.getElementById('proyecto');
const zonaInput = document.getElementById('zona');
const prioridadInput = document.getElementById('prioridad');

descripcionInput.addEventListener('input', buscar);
cantidadInput.addEventListener('input', buscar);
proyectoInput.addEventListener('input', buscar);
zonaInput.addEventListener('input', buscar);
prioridadInput.addEventListener('input', buscar);
*/

function editarItem(button) {
	let itemId = button.getAttribute('data-item-id');
  	const itemsJson = JSON.parse(itemsJsonString)
  	let item = itemsJson.find(function(item) {
    	return item.idItem == itemId;
  	});
	document.getElementById("proyectoModal").value = item.centroOperacionNombre
	document.getElementById("zonaModal").value = item.zona
	document.getElementById("descripcionModal").value = item.descripcion
	document.getElementById("prioridadModal").value = "Selecionar prioridad definir"
	document.getElementById("centroTrabajoModal").value = "Seleccionar centro de trabajo"
  	console.log(item);
  
  	const modalPrioridad = new bootstrap.Modal('#modal-prioridad')

	modalPrioridad.show()
	modalPrioridad._element.addEventListener('hidden.bs.modal', function () {
		console.log("Limpiar modal")
	})

}