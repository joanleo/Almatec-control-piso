async function buscarPedidos(){
	const un = document.getElementById('un').value
    const estado = document.getElementById('estado').value
    const asesor = document.getElementById('asesor').value
    const descripcion = document.getElementById('descripcion').value
    const cliente = document.getElementById('cliente').value
    const tipo = document.getElementById('tipo').value
    const fechaInicio = document.getElementById('start_date').value
    const fechaFin = document.getElementById('end_date').value
    
    const fechaInicioFormateada = fechaInicio ? new Date(fechaInicio).toISOString().slice(0, 10) : null;
  	const fechaFinFormateada = fechaFin ? new Date(fechaFin).toISOString().slice(0, 10) : null;
           
    const filtro = {
		"un": un,
		"estado": estado,
		"asesor": asesor,
		"descripcion": descripcion,
		"cliente": cliente,
		"tipo": tipo,
		"fechaInicio": fechaInicioFormateada,
		"fechaFin": fechaFinFormateada
	}
	
	const orders = await getOrders(filtro)
	console.log(orders)
	let listOrders = createListObjetsOrder(orders)
	
	fillTableOrders(listOrders)	
	
}

function fillTableOrders(listOrders) {
    let tbodyOrders = document.getElementById("orders")
	tbodyOrders.innerHTML = ''
    listOrders.forEach(order => {
        let row = document.createElement('tr')

        let cellUn = document.createElement('td')
        cellUn.textContent = order.un
        row.appendChild(cellUn)

        let cellDescripcion = document.createElement('td')
        cellDescripcion.textContent = order.descripcion
        row.appendChild(cellDescripcion)

        let cellCliente = document.createElement('td')
        cellCliente.textContent = order.cliente
        row.appendChild(cellCliente)

        let cellEstado = document.createElement('td')
        let divBage = document.createElement('div')
        if(order.estado == "Cumplido"){
			divBage.classList.add("finished__badge")
		}else if(order.estado == "Aprobado"){
	        divBage.classList.add("approved__badge")			
		}else{
			divBage.classList.add("user__badge")
		}
        divBage.textContent = order.estado
        cellEstado.appendChild(divBage)
        /*
        <div class="user__badge" th:text="${proyecto.estadoDoc}"></div>
        */
        row.appendChild(cellEstado)
		
        let cellPesoTotal = document.createElement('td')
        cellPesoTotal.textContent = order.kgTotal
        row.appendChild(cellPesoTotal)

		let pesoPendiente = order.kgTotal - order.kgCumplidos
        let cellPesoPendiente = document.createElement('td')
        cellPesoPendiente.textContent = pesoPendiente.toFixed(3)
        row.appendChild(cellPesoPendiente)

		let avance = (order.kgCumplidos / order.kgTotal) * 100
        let cellAvance = document.createElement('td')
        cellAvance.textContent = avance.toFixed(2)
        row.appendChild(cellAvance)

        let cellFechaPedido = document.createElement('td')
        cellFechaPedido.textContent = order.fecha
        row.appendChild(cellFechaPedido)

        let cellFechaInicioProd = document.createElement('td')
        row.appendChild(cellFechaInicioProd)

        let cellFinalProd = document.createElement('td')
        row.appendChild(cellFinalProd)

        tbodyOrders.appendChild(row)

    })
}

function createListObjetsOrder(orders) {
    let listOrders = []
    for (const item of orders) {
        const un = item.co
        const descripcion = item.descripcion
        const cliente = item.razonSocial
        const kgTotal = item.cantPedida
        const kgCumplidos = item.kgCumplidos
        const estado = item.estado
        const asesor = item.vendedor
        const fecha = new Date(item.fecha + "T00:00:00")
        const fechaFormateada = fecha.toLocaleDateString("es-CO", {
            year: "numeric",
            month: "2-digit",
            day: "2-digit",
        })
        const order = {
            "un": un,
            "descripcion": descripcion,
            "cliente": cliente,
            "kgTotal": kgTotal,
            "kgCumplidos": kgCumplidos,
            "estado": estado,
            "asesor": asesor,
            "fecha": fechaFormateada
        }
        listOrders.push(order)
    }
    return listOrders
}

async function getOrders(filtro) {
    const response = await fetch('/comercial/pedidos/filtrar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(filtro)
    })

    const data = await response.json()
    return data
}
