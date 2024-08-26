async function buscarPedidos(page = 0, size = 10, sortField = 'fecha', sortDirection = 'desc') {
    const filtro = {
        un: document.getElementById('un').value,
        estado: document.getElementById('estado').value,
        asesor: document.getElementById('asesor').value,
        descripcion: document.getElementById('descripcion').value,
        cliente: document.getElementById('cliente').value,
        tipo: document.getElementById('tipo').value,
        fechaInicio: formatDate(document.getElementById('start_date').value),
        fechaFin: formatDate(document.getElementById('end_date').value)
    };

    try {
        const orders = await getOrders(filtro, page, size, sortField, sortDirection);
        const listOrders = createListObjectsOrder(orders.content);
        fillTableOrders(listOrders);
        setupPagination(orders.totalPages, page, size, sortField, sortDirection);
    } catch (error) {
        console.error('Error al buscar pedidos:', error);
    }
}

const formatDate = (date) => date ? new Date(date).toISOString().slice(0, 10) : null;

const createListObjectsOrder = (orders) => {
    return orders.map(item => ({
        un: item.co,
        descripcion: item.descripcion,
        cliente: item.razonSocial,
        kgTotal: item.cantPedida,
        kgCumplidos: item.kgCumplidos,
        estado: item.estado,
        asesor: item.vendedor,
        fecha: new Date(item.fecha + "T00:00:00").toLocaleDateString("es-CO", {
            year: "numeric",
            month: "2-digit",
            day: "2-digit",
        })
    }));
};

const fillTableOrders = (listOrders) => {
    const tbodyOrders = document.getElementById("orders");
    tbodyOrders.innerHTML = listOrders.map(order => `
        <tr>
            <td>${order.un}</td>
            <td>${order.descripcion}</td>
            <td>${order.cliente}</td>
            <td><div class="${getStatusClass(order.estado)}">${order.estado}</div></td>
            <td>${(order.kgTotal).toFixed(2)}</td>
            <td>${(order.kgTotal - order.kgCumplidos).toFixed(2)}</td>
            <td>${((order.kgCumplidos / order.kgTotal) * 100).toFixed(2)}</td>
            <td>${order.fecha}</td>
            <td></td>
            <td></td>
        </tr>
    `).join('');
};

const getStatusClass = (estado) => {
    switch(estado) {
        case "Cumplido": return "finished__badge";
        case "Aprobado": return "approved__badge";
        default: return "user__badge";
    }
};

async function getOrders(filtro, page, size, sortField, sortDirection) {
    const response = await fetch(`/comercial/pedidos/filtrar?page=${page}&size=${size}&sort=${sortField}&order=${sortDirection}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(filtro)
    });

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }

    return await response.json();
}

const setupPagination = (totalPages, currentPage, size, sortField, sortDirection) => {
    const pagination = document.getElementById('pagination');
    pagination.innerHTML = '';

    const maxVisiblePages = 5;
    let startPage = Math.max(0, currentPage - Math.floor(maxVisiblePages / 2));
    let endPage = Math.min(totalPages - 1, startPage + maxVisiblePages - 1);

    // Ajustar startPage si estamos cerca del final
    startPage = Math.max(0, Math.min(startPage, totalPages - maxVisiblePages));

    // Función para crear un elemento de página
    const createPageItem = (pageNum, text, isActive = false, isDisabled = false) => {
        const pageItem = document.createElement('li');
        pageItem.className = `page-item${isActive ? ' active' : ''}${isDisabled ? ' disabled' : ''}`;

        const pageLink = document.createElement('a');
        pageLink.className = 'page-link';
        pageLink.href = '#';
        pageLink.textContent = text;
        if (!isDisabled) {
            pageLink.onclick = (event) => {
                event.preventDefault();
                buscarPedidos(pageNum, size, sortField, sortDirection);
            };
        }

        pageItem.appendChild(pageLink);
        return pageItem;
    };

    // Botón "Primera página"
    pagination.appendChild(createPageItem(0, '«', false, currentPage === 0));

    // Botón "Anterior"
    pagination.appendChild(createPageItem(Math.max(0, currentPage - 1), '‹', false, currentPage === 0));

    // Páginas numeradas
    for (let i = startPage; i <= endPage; i++) {
        pagination.appendChild(createPageItem(i, i + 1, i === currentPage));
    }

    // Botón "Siguiente"
    pagination.appendChild(createPageItem(Math.min(totalPages - 1, currentPage + 1), '›', false, currentPage === totalPages - 1));

    // Botón "Última página"
    pagination.appendChild(createPageItem(totalPages - 1, '»', false, currentPage === totalPages - 1));
};

document.querySelectorAll('th.sortable').forEach(header => {
    header.onclick = () => {
        const sortField = header.dataset.sortField;
        const sortDirection = header.dataset.sortDirection === 'asc' ? 'desc' : 'asc';
        header.dataset.sortDirection = sortDirection;

        // Update icons
        document.querySelectorAll('th.sortable i').forEach(icon => icon.className = 'fa fa-sort');
        header.querySelector('i').className = sortDirection === 'asc' ? 'fa fa-sort-up' : 'fa fa-sort-down';

        buscarPedidos(0, 10, sortField, sortDirection);
    };
})

