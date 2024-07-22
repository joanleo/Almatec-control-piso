async function buscarPedidos(page = 0, size = 10, sortField = 'co', sortDirection = 'asc') {
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
            <td>${order.kgTotal}</td>
            <td>${(order.kgTotal - order.kgCumplidos).toFixed(3)}</td>
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

    for (let i = 0; i < totalPages; i++) {
        const pageItem = document.createElement('li');
        pageItem.className = 'page-item' + (i === currentPage ? ' active' : '');

        const pageLink = document.createElement('a');
        pageLink.className = 'page-link';
        pageLink.href = '#';
        pageLink.textContent = i + 1;
        pageLink.onclick = (event) => {
            event.preventDefault();
            buscarPedidos(i, size, sortField, sortDirection);
        };

        pageItem.appendChild(pageLink);
        pagination.appendChild(pageItem);
    }
}

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

