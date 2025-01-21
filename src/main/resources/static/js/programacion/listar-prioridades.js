let itemsGlobal = []
let itemsOriginales = []
const pageSize = 10
let centrosTrabajoPrioridad = []
let centroTrabajoSelectedId
let spinner
document.addEventListener('DOMContentLoaded', async function(){
	spinner = document.getElementById('spinner')
	spinner.removeAttribute('hidden')
	const centrosTrabajoFilter = [3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 17];

	const centrosTrabajo = await fetchCentrosT()
	
	centrosTrabajoPrioridad = centrosTrabajo.filter(centro=>centrosTrabajoFilter.includes(centro.id))
	const select = document.getElementById('centroTrabajoSelect')
	select.innerHTML = '';
	
	const optionDefault = document.createElement('option')
	optionDefault.value = ''
	optionDefault.text = 'Seleccione un centro de trabajo'
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
		document.getElementById('filtroForm').reset();
		centroTrabajoSelectedId = this.value;
        if (centroTrabajoSelectedId) {
            cargarDatos(centroTrabajoSelectedId);
        } else {
            document.getElementById('resultadosContainer').style.display = 'none';
        }
	  })
	  
	  addSortLinkListeners();
		  
	  document.addEventListener('click', function(e) {
          if (e.target.matches('.page-link')) {
              e.preventDefault();
              const url = new URL(e.target.href);
              const centroTrabajoId = document.getElementById('centroTrabajoSelect').value;
              cargarDatos(
                  centroTrabajoId,
                  url.searchParams.get('page'),
                  url.searchParams.get('size'),
                  url.searchParams.get('sortField'),
                  url.searchParams.get('sortDir')
              )
          }
      })
	  const checkAllElement = document.getElementById('checkAll');
      if (checkAllElement) {
          checkAllElement.addEventListener('change', handleSelectAll);
      }

      document.addEventListener('change', function(e) {
          if (e.target && (e.target.classList.contains('item-checkbox') || e.target.id === 'checkAll')) {
              updateAsignarButtonVisibility();
          }
      });

	  document.getElementById('btnAsignarMultiple').addEventListener('click', function() {
	      const selectedItems = getSelectedItems();
	      console.log('Elementos seleccionados:', selectedItems); // Para depuración
	      if (selectedItems.length > 0) {
	          showPrioridadModal(selectedItems);
	      } else {
	          console.log('No se seleccionaron elementos');
	          alert('Por favor, seleccione al menos un elemento.');
	      }
	  });	  			  
})

function addSortLinkListeners() {
	document.querySelectorAll('.sort-link').forEach(link => {
    	link.addEventListener('click', handleSortClick);
	});
}

function handleSortClick(e) {
      e.preventDefault();
      const url = new URL(e.currentTarget.href);
      const centroTrabajoId = document.getElementById('centroTrabajoSelect').value;
      cargarDatos(
          centroTrabajoId,
          url.searchParams.get('page'),
          url.searchParams.get('size'),
          url.searchParams.get('sortField'),
          url.searchParams.get('sortDir')
      );
  }

  function showSpinner() {
  	spinner.removeAttribute('hidden')
  }

  function hideSpinner() {
  	spinner.setAttribute('hidden', '')
  }
  
function cargarDatos(centroTrabajoId, page = 0, size = 10, sortField = 'idOpIntegrapps', sortDir = 'desc') {
	showSpinner()
	
	const descripcion = document.getElementById('descripcion').value;
	const cantidad = document.getElementById('cantidad').value;
	const proyecto = document.getElementById('proyecto').value;
	const zona = document.getElementById('zona').value;
	const prioridad = document.getElementById('prioridad').value;
	const op = document.getElementById('op').value;
	const marca = document.getElementById('marca').value;

	const url = `/programacion/prioridades?page=${page}&size=${size}&sortField=${sortField}&sortDir=${sortDir}&centroTrabajoId=${centroTrabajoId}&descripcion=${encodeURIComponent(descripcion)}&cantidad=${encodeURIComponent(cantidad)}&proyecto=${encodeURIComponent(proyecto)}&zona=${encodeURIComponent(zona)}&prioridad=${encodeURIComponent(prioridad)}&op=${encodeURIComponent(op)}&marca=${encodeURIComponent(marca)}`;
   
		fetch(url, {
	    	headers: {
	        	'X-Requested-With': 'XMLHttpRequest'
            }
        })
		.then(response => response.text())
	    .then(html => {
	    	document.getElementById('resultsTable').innerHTML = html;
	        document.getElementById('resultadosContainer').style.display = 'block';
				  
			addSortLinkListeners();
				  
			const checkAllElement = document.getElementById('checkAll');
	        if (checkAllElement) {
	        	checkAllElement.addEventListener('change', handleSelectAll);
        	}
        })
		.finally(() => {
			hideSpinner();
		})
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

async function obtenerOpCentroT(ct){
	showSpinner()
	try{
		const response = await fetch(`/programacion/centros-trabajo/${ct}/ordenes-produccion-prioridad`)
		const data = await response.json()
		data.sort((a, b) => b.idOp - a.idOp)
		//console.log("respuesta ops CT: ",data)
		return data
	}catch(error){
		console.log("Error al tratar de obtener OP en centro de trabajo", error)
	}finally{
		hideSpinner()		
	}
		
}

function handleSelectAll() {
    console.log("check clickeado");
    const checkboxes = document.querySelectorAll('.item-checkbox');
    const selectAllCheckbox = document.querySelector('#checkAll');
    checkboxes.forEach(checkbox => {
        checkbox.checked = selectAllCheckbox.checked;
    });
    updateAsignarButtonVisibility();
}
  
function updateAsignarButtonVisibility() {
    const checkboxes = document.querySelectorAll('.item-checkbox');
    const anyChecked = Array.from(checkboxes).some(checkbox => checkbox.checked);
    const btnAsignarMultiple = document.querySelector('#btnAsignarMultiple');
    btnAsignarMultiple.style.display = anyChecked ? 'block' : 'none';
}
  
 document.addEventListener('change', function(e) {
      if (e.target && e.target.classList.contains('item-checkbox')) {
          updateAsignarButtonVisibility();
      }
  })
  


function buscar() {
	showSpinner();
	let centroTrabajoId = document.getElementById("centroTrabajoSelect").value
    let form = document.getElementById('filtroForm');
	const formData = new FormData(form);
	formData.append('centroTrabajoId', centroTrabajoId);
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
	        const newTable = doc.getElementById('resultsTable');
	        document.getElementById('resultsTable').innerHTML = newTable.innerHTML;
			
			const checkAllElement = document.getElementById('checkAll');
	        if (checkAllElement) {
	        	checkAllElement.addEventListener('change', handleSelectAll);
        	}
	    })
	    .catch(error => console.error('Error:', error))
		.finally(() => {
			hideSpinner();
		})
}

const opInput = document.getElementById('op');
const marcaInput = document.getElementById('marca');
const descripcionInput = document.getElementById('descripcion');
const cantidadInput = document.getElementById('cantidad');
const proyectoInput = document.getElementById('proyecto');
const zonaInput = document.getElementById('zona');
const prioridadInput = document.getElementById('prioridad');

opInput.addEventListener('input', buscar);
marcaInput.addEventListener('input', buscar);
descripcionInput.addEventListener('input', buscar);
cantidadInput.addEventListener('input', buscar);
proyectoInput.addEventListener('input', buscar);
zonaInput.addEventListener('input', buscar);
prioridadInput.addEventListener('input', buscar);


function editarItem(button) {
	const itemId = button.getAttribute('data-item-id');
    const row = button.closest('tr');
    const cells = row.children;

    const item = {
        item_op_id: itemId,
        proyecto: cells[6].textContent,
        zona: cells[7].textContent,
        descripcion: cells[4].textContent,
        prioridad: cells[8].textContent
    };

    showPrioridadModal([item]);

}



function getSelectedItems() {
    // Excluimos explícitamente el checkbox de "seleccionar todos"
    const checkboxes = document.querySelectorAll('.item-checkbox:checked:not(#checkAll)');
    return Array.from(checkboxes).map(checkbox => {
        const row = checkbox.closest('tr');
        if (!row) {
            console.error('No se pudo encontrar la fila para el checkbox:', checkbox);
            return null;
        }
        const cells = row.cells;
        return {
            item_op_id: Number(cells[1].textContent), // Asumiendo que el ID está en la segunda celda
            proyecto: cells[6].textContent,
            zona: cells[7].textContent,
            descripcion: cells[4].textContent,
            prioridad: cells[8].textContent
        };
    }).filter(item => item !== null);
}

function showPrioridadModal(items) {
    console.log('Mostrando modal para:', items); // Para depuración
    document.getElementById('proyectoModal').value = items.map(item => item.proyecto).join(', ');
    document.getElementById('zonaModal').value = items.map(item => item.zona).join(', ');
    document.getElementById('descripcionModal').value = items.map(item => item.descripcion).join(', ');
    document.getElementById('prioridadModal').value = items[0].prioridad || '';
    
    const ctSelected = centrosTrabajoPrioridad.find(ct => ct.id == centroTrabajoSelectedId);
    document.getElementById('centroTrabajoModal').value = ctSelected ? ctSelected.nombre : '';
    document.getElementById('centroTrabajoModal').dataset.centrotrabajoId = centroTrabajoSelectedId;

    document.getElementById('modal-prioridad').dataset.items = JSON.stringify(items);

    const modal = new bootstrap.Modal(document.getElementById('modal-prioridad'));
    modal.show();
}

document.getElementById('guardarPrioridad').addEventListener('click', function() {
	showSpinner()
	const modal = bootstrap.Modal.getInstance(document.getElementById('modal-prioridad'));
	modal.hide()
    const prioridad = document.getElementById('prioridadModal').value;
    const centroTrabajo = document.getElementById('centroTrabajoModal').dataset.centrotrabajoId;
    const items = JSON.parse(document.getElementById('modal-prioridad').dataset.items);

    const data = {
        itemsId: items.map(item => item.item_op_id),
		prioridad: Number(prioridad),
        centroTrabajoId: Number(centroTrabajo)
    };
	
    fetch('/programacion/guardar-prioridad-multiple', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
    .then(response => response.json())
    .then(data => {
		if(data.status == 400){
			throw new Error(data.errors[0].defaultMessage)
		}
        console.log('Success:', data);     
        
		mostrarAlert(data.mensaje, 'success')
		cargarDatos(centroTrabajoSelectedId)
    })
    .catch((error) => {
        console.error(error);
		mostrarAlert(error, 'danger')
    })
	.finally(() => {
		const btnAsignarMultiple = document.querySelector('#btnAsignarMultiple');
	    btnAsignarMultiple.style.display = 'none';
		hideSpinner()
		}
	)
		
	
});
