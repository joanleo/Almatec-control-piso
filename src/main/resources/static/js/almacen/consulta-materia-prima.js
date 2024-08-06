document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('searchForm');
    const resultados = document.getElementById('resultados');
    const pagination = document.getElementById('pagination');
    let currentPage = 0;

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

        fetch(`/almacen/buscar-materia-prima?${searchParams.toString()}`)
            .then(response => response.json())
            .then(data => {
                mostrarResultados(data.content);
                actualizarPaginacion(data);
            })
            .catch(error => console.error('Error:', error));
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
            for (let i = 0; i < data.totalPages; i++) {
                const button = document.createElement('button');
                button.innerText = i + 1;
                button.classList.add('btn', 'btn-secondary', 'm-1');
                if (i === currentPage) {
                    button.classList.add('active');
                }
                button.addEventListener('click', () => {
                    currentPage = i;
                    buscarMateriaPrima();
                });
                pagination.appendChild(button);
            }
        }
    }
});
