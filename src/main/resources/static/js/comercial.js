async function buscarPedidos(){      
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
        const orders = await getOrders(filtro);
        const listOrders = createListObjectsOrder(orders);
        fillTableOrders(listOrders);
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

async function getOrders(filtro) {
    const response = await fetch('/comercial/pedidos/filtrar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(filtro)
    })

	if (!response.ok) {
	        throw new Error(`HTTP error! status: ${response.status}`);
	    }

    return await response.json();
}
