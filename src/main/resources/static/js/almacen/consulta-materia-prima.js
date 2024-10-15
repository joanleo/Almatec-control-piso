document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('searchForm');
    const resultados = document.getElementById('resultados');
    const pagination = document.getElementById('pagination');
    let currentPage = 0;
	const spinner = document.getElementById('spinner');

    form.addEventListener('submit', function(e) {
        e.preventDefault();
        currentPage = 0;
        buscarMateriaPrima();
    });

    function buscarMateriaPrima() {
        const formData = new FormData(form);
		console.log(form)
        const searchParams = new URLSearchParams(formData);
        searchParams.append('page', currentPage);
		spinner.removeAttribute('hidden');
        fetch(`/almacen/buscar-materia-prima?${searchParams.toString()}`)
            .then(response => response.json())
            .then(data => {
                mostrarResultados(data.content);
                actualizarPaginacion(data);
				spinner.setAttribute('hidden', '');
            })
            .catch(error => {
				console.error('Error:', error)
				spinner.setAttribute('hidden', '')
			})
			
    }

    function mostrarResultados(items) {
        resultados.innerHTML = '';
        items.forEach(item => {
            resultados.innerHTML += `
                <tr>
                    <td>${item.idItem}</td>
                    <td>${item.descripcionItem}</td>
                    <td>${item.um}</td>
                    <td>${item.idBodega}</td>
                    <td>${item.lote}</td>
                    <td>${item.disponible}</td>
                </tr>
            `;
        });
    }

	function actualizarPaginacion(data) {
	        pagination.innerHTML = '';
	        if (data.totalPages > 1) {
	            const totalPages = data.totalPages;
	            const maxVisiblePages = 5;
	            let startPage = Math.max(0, currentPage - Math.floor(maxVisiblePages / 2));
	            let endPage = Math.min(totalPages - 1, startPage + maxVisiblePages - 1);

	            startPage = Math.max(0, Math.min(startPage, totalPages - maxVisiblePages));

	            // Botón "Inicio"
	            pagination.appendChild(agregarBotonPaginacion('«', 0, false, currentPage === 0));

	            // Botón "Anterior"
	            pagination.appendChild(agregarBotonPaginacion('‹', currentPage - 1, false, currentPage === 0));

	            // Botones de página
	            for (let i = startPage; i <= endPage; i++) {
	                pagination.appendChild(agregarBotonPaginacion(i + 1, i, i === currentPage));
	            }

	            // Botón "Siguiente"
	            pagination.appendChild(agregarBotonPaginacion('›', currentPage + 1, false, currentPage === totalPages - 1));

	            // Botón "Final"
	            pagination.appendChild(agregarBotonPaginacion('»', totalPages - 1, false, currentPage === totalPages - 1))	;
	        }
	    }

	    function agregarBotonPaginacion(texto, pagina, isActive = false, isDisabled = false) {
	        const pageItem = document.createElement('li');
			pageItem.className = `page-item${isActive ? ' active' : ''}${isDisabled ? ' disabled' : ''}`;
			const pageLink = document.createElement('a');
			pageLink.className = 'page-link';
	        pageLink.href = '#';
	        pageLink.textContent = texto;
			if (!isDisabled) {
	            pageLink.onclick = (event) => {
					event.preventDefault();
					currentPage = pagina;
		            buscarMateriaPrima();
	            };
	        }
			
			pageItem.appendChild(pageLink);
	        return pageItem;
			
			
	        /*pageItem.classList.add('btn', 'btn-primary', 'm-1');
	        pageItem.innerText = texto;
	        if (activo) pageItem.classList.add('active');
	        if (!habilitado) pageItem.disabled = true;
	        pageItem.addEventListener('click', () => {
	            currentPage = pagina;
	            buscarMateriaPrima();
	        });
	        pagination.appendChild(pageItem);*/
	    }
});
