document.addEventListener('DOMContentLoaded', function(){
	
	let searchTimeout;
    const searchInput = document.getElementById('searchInput');
    const resultsList = document.getElementById('resultsList');
	let tipoActual = ''; 
	
	document.querySelectorAll('.btn-group .btn').forEach(button => {
        button.addEventListener('click', function() {
            const tipoSeleccionado = this.getAttribute('data-tipo');
            
            // Si el botón ya está activo, lo desactivamos
            if (this.classList.contains('active')) {
                this.classList.remove('active');
                tipoActual = ''; // Quitar el filtro
            } else {
                // Desactivar todos los botones primero
                document.querySelectorAll('.btn-group .btn').forEach(btn => {
                    btn.classList.remove('active');
                });
                // Activar este botón
                this.classList.add('active');
                tipoActual = tipoSeleccionado;
            }

            // Realizar búsqueda con el estado actualizado
            const query = searchInput.value.trim();
            performSearch(query, tipoActual, true);
        });
    });
	

    searchInput.addEventListener('input', function() {
        clearTimeout(searchTimeout);
        const query = this.value.trim();
        
		if (query.length >= 2 || query.length === 0) {
	        resultsList.innerHTML = '<div class="text-center py-3">Buscando...</div>';
	        searchTimeout = setTimeout(() => performSearch(query, tipoActual, true), 300);
	    } else {
            resultsList.innerHTML = '';
        }
    });

	let currentPage = 0;
	const itemsPerPage = 10;
	let lastPage = false;
	let loading = false;
	
	function performSearch(query, tipo, newSearch = false) 	{
		if (loading) return;
		
	    if (newSearch) {
	        currentPage = 0;
	        lastPage = false;
	        resultsList.innerHTML = '';
	    }
	    if (lastPage) return;

	    loading = true;
	    showLoadingIndicator();
		
		let url = `/ingenieria/items/search?query=${encodeURIComponent(query)}&page=${currentPage}&size=${itemsPerPage}`;
	    if (tipo) {
	        url += `&tipo=${encodeURIComponent(tipo)}`;
	    }
	        
		fetch(url)
	        .then(response => response.json())
	        .then(page => {
	            if (page.content.length === 0) {
	                if (currentPage === 0) {
	                    showNoResultsMessage();
	                }
	                lastPage = true;
	                return;
	            }
	
	            page.content.forEach(item => {
	                const element = createItemElement(item);
	                resultsList.appendChild(element);
	            });
	
	            lastPage = page.last;
	            if (!lastPage) {
	                currentPage++;
	            }
	        })
	        .catch(error => {
	            console.error('Error:', error);
	            showErrorMessage();
	        })
	        .finally(() => {
	            loading = false;
	            hideLoadingIndicator();
	        });
    }

	function setupInfiniteScroll() {
	    window.addEventListener('scroll', () => {
	        if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight - 100) {
	            const query = searchInput.value.trim();
	            performSearch(query, tipoActual);
	        }
	    });
	}

	// Funciones auxiliares para UI
	function showLoadingIndicator() {
	    const loader = document.getElementById('loadingIndicator') || createLoadingIndicator();
	    loader.classList.remove('d-none');
	}

	function hideLoadingIndicator() {
	    const loader = document.getElementById('loadingIndicator');
	    if (loader) {
	        loader.classList.add('d-none');
	    }
	}

	function createLoadingIndicator() {
	    const div = document.createElement('div');
	    div.id = 'loadingIndicator';
	    div.className = 'text-center py-3 d-none';
	    div.innerHTML = `
	        <div class="spinner-border text-primary" role="status">
	            <span class="visually-hidden">Cargando...</span>
	        </div>
	    `;
	    resultsList.insertAdjacentElement('afterend', div);
	    return div;
	}

	function showNoResultsMessage() {
	    resultsList.innerHTML = `
	        <div class="text-center py-4">
	            <i class="fa fa-search fa-3x text-muted mb-3"></i>
	            <p class="text-muted mb-0">No se encontraron items que coincidan con tu búsqueda</p>
	            <p class="small text-muted mt-2">Prueba con otros términos de búsqueda</p>
	        </div>
	    `;
	}

	function showErrorMessage() {
	    resultsList.innerHTML = `
	        <div class="text-center py-4 text-danger">
	            <i class="fa fa-exclamation-circle fa-3x mb-3"></i>
	            <p class="mb-0">Error al cargar los resultados</p>
	            <p class="small mt-2">Por favor, intenta nuevamente</p>
	        </div>
	    `;
	}
	
	function createItemElement(item) {
        const div = document.createElement('div');
        div.className = 'list-group-item list-group-item-action search-result';
		// Obtener el permiso del contenedor
	    const container = document.getElementById('itemContainer');
	    const hasPermission = container.dataset.hasPermission === 'true';
	    
	    // Solo agregar onclick si tiene permiso
	    if (hasPermission) {
	        div.onclick = () => window.location.href = `/ingenieria/items/${item.idItem}`;
	        div.style.cursor = 'pointer';
	    }
        
		div.innerHTML = `
	        <div class="d-flex w-100 justify-content-between align-items-start">
	            <div class="flex-grow-1">
	                <div class="d-flex align-items-center mb-1">
	                    <h6 class="mb-0">${item.descripcion}</h6>
	                </div>
	                <p class="mb-0 text-muted">
	                    <small>
	                        <i class="fa fa-hashtag me-1"></i>GY${item.idItem}
	                        ${item.plano ? `<span class="ms-2"><i class="fa fa-file-alt me-1"></i>${item.plano}</span>` : ''}
	                    </small>
	                </p>
	            </div>
				<div class="d-flex align-items-center gap-2">
                    <span class="${getBadgeColor(item.tipo)} ms-2">${item.tipo}</span>
	                <button class="btn btn-sm btn-outline-secondary" 
	                        onclick="event.stopPropagation();"
	                        data-bs-toggle="popover"
	                        data-bs-placement="left"
	                        data-bs-html="true"
	                        title="Detalles del Item"
	                        data-bs-content="
	                            <div class='p-1'>
	                                <p class='mb-2'><strong>Código:</strong> GY${item.idItem}</p>
	                                <p class='mb-2'><strong>Tipo:</strong> ${item.tipo}</p>
	                                <p class='mb-2'><strong>Plano:</strong> ${item.plano || 'N/A'}</p>
	                                <p class='mb-2'><strong>Estado:</strong> ${item.isActivo ? 'Activo': 'Inactivo'}</p>
	                                <p class='mb-2'><strong>Peso:</strong> ${item.pesoNeto ? item.pesoNeto + ' kg' : 'N/A'}</p>
	                                <p class='mb-0'><strong>Longitud:</strong> ${item.longitud ? item.longitud + ' ml': 'N/A'}</p>
	                            </div>
	                        ">
	                    <i class="fa fa-info-circle"></i>
	                </button>
	            </div>
	        </div>
	    `;
        
		const popoverTriggerList = [].slice.call(div.querySelectorAll('[data-bs-toggle="popover"]'));
		    popoverTriggerList.map(function (popoverTriggerEl) {
		        return new bootstrap.Popover(popoverTriggerEl, {
		            trigger: 'focus'
		        });
	    });
		
        return div;
    }
	
	function getBadgeColor(tipo) {
        switch(tipo) {
            case 'CONJUNTO': return 'me-2 badge ms-2 bg-success';
            case 'ELEMENTO': return 'me-2 badge ms-2 bg-primary';
            case 'PARTE': return 'me-2 badge ms-2 bg-info';
            default: return 'me-2 badge ms-2 secondary';
        }
    }
	
	setupInfiniteScroll();	
	
	}
)