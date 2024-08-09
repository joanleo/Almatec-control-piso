let currentPage = 0;
let totalPages = 0;
let sortBy = 'nombre';
let sortDir = 'asc';
let pageSize = 10;
let searchTerm = '';

document.addEventListener('DOMContentLoaded', function() {
    loadOperarios();
    setupSortingListeners();
	setupSearchListener();
})

function setupSearchListener() {
    const searchInput = document.getElementById('searchInput');
    searchInput.addEventListener('input', function() {
        searchTerm = this.value;
        currentPage = 0;
        loadOperarios();
    });
}

function loadOperarios() {
    fetch(`/operarios?page=${currentPage}&size=${pageSize}&sortBy=${sortBy}&sortDir=${sortDir}&search=${searchTerm}`)
        .then(response => response.json())
        .then(data => {
            displayOperarios(data.content, data.number * data.size);
            updatePagination(data.number, data.totalPages);
            totalPages = data.totalPages;
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al cargar los operarios');
        });
}

function displayOperarios(operarios, startIndex) {
    const tableBody = document.querySelector('#operariosTable tbody');
    tableBody.innerHTML = '';
    operarios.forEach((operario, index) => {
        const row = `
            <tr>
                <td>${startIndex + index + 1}</td>
                <td>${operario.nombres}</td>
                <td>${operario.apellidos}</td>
                <td>${operario.numDoc}</td>
                <td>${operario.isActivo ? 'Activo' : 'Inactivo'}</td>
                <td>
                    <a class="btn btn-primary btn-sm" href="/operarios/editar/${operario.id}" role="button">Editar</a>
                    <button class="btn btn-danger btn-sm" onclick="confirmModal(this)" data-operario-id="${operario.id}">${operario.isActivo ? 'Desactivar' : 'Activar'}</button>
                </td>
            </tr>
        `;
        tableBody.innerHTML += row;
    });
}

function updatePagination(currentPage, totalPages) {
    const paginationElement = document.getElementById('pagination');
    let paginationHTML = '';
    for (let i = 0; i < totalPages; i++) {
        paginationHTML += `<li class="page-item ${i === currentPage ? 'active' : ''}">
            <a class="page-link" href="#" onclick="goToPage(${i})">${i + 1}</a>
        </li>`;
    }
    paginationElement.innerHTML = paginationHTML;
}

function goToPage(page) {
    currentPage = page;
    loadOperarios();
}

function setupSortingListeners() {
    document.querySelectorAll('th[data-sort]').forEach(th => {
        th.addEventListener('click', () => {
            const column = th.dataset.sort;
            if (sortBy === column) {
                sortDir = sortDir === 'asc' ? 'desc' : 'asc';
            } else {
                sortBy = column;
                sortDir = 'asc';
            }
            currentPage = 0;
            loadOperarios();
        });
    });
}

// Otras funciones (confirmDelete, deleteOperario) permanecen iguales...

function confirmDelete(id) {
    const modal = new bootstrap.Modal(document.getElementById('confirmDeleteModal'));
    modal.show();
    document.getElementById('confirmDeleteBtn').onclick = function() {
        deleteOperario(id);
        modal.hide();
    };
}

function eliminarOperario(){
    const id = parseInt(id_operario_eliminar);
    if (isNaN(id)) {
        console.error('El ID del operario no es un número válido.');
        return;
    }
    const urlDesactivar = `/operarios/${id}`;
    fetch(urlDesactivar, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {
        if (!data.error) {
            mostrarAlert(data.mensaje, 'success');
            loadOperarios(); // Recargar la tabla
            bootstrap.Modal.getInstance(document.getElementById('modal')).hide();
        } else {
            throw new Error(data.mensaje);
        }
    })
    .catch(error => {
        console.error('Error:', error);
        mostrarAlert('Error al desactivar el operario', 'danger');
    });
}

let id_operario_eliminar;
function confirmModal(button) {
    const operarioId = button.getAttribute("data-operario-id");
    id_operario_eliminar = operarioId;
    const confirm_modal = new bootstrap.Modal('#modal', {
        keyboard: false
    });
    const confirm_modal_body = confirm_modal._element.querySelector(".modal-confirm-body");
    const accion = button.textContent.trim();
    confirm_modal_body.textContent = `¿Está seguro que desea ${accion.toLowerCase()} este operario?`;
    
    // Cambia el texto del botón de confirmación
    const confirmButton = confirm_modal._element.querySelector(".modal-footer .btn-danger");
    confirmButton.textContent = accion;
    
    confirm_modal.show();
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