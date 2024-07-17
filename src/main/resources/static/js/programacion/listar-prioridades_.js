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

function mostrarPagina(page) {
	console.log("Uso de mostrar pagina")
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
      cellDescripcion.textContent = item.item.itemDescripcion;
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
            item.item.itemDescripcion.toLowerCase().includes(descripcion) &&
            (cantidad === '' || item.item.cant_req.toString().includes(cantidad)) &&
            item.proyecto.toLowerCase().includes(proyecto) &&
            item.zona.toLowerCase().includes(zona) &&
            (prioridad === '' || (item.item.prioridad !== null && item.item.prioridad.toString().includes(prioridad)))
        );
    console.log(itemsFiltrados)
  	
    llenarTabla(itemsFiltrados)
  }
