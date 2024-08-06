let ordenes
document.addEventListener('DOMContentLoaded', async function(){
	
	ordenes = await obtenerOrdenes()
	//console.log(ordenes)
	
	ordenes = filtrarOrdenesUnicas(ordenes);
	console.log(ordenes);
	
	google.charts.load('current', {'packages':['gantt']});
	google.charts.setOnLoadCallback(drawChart);
	
	

})

async function obtenerOrdenes(){
	try{
		const response = await fetch(`/programacion/ordenes`)
		
		if(!response.ok){
			throw new Error("Problemas al obtener las ordenes activas")
		}
		const data = await response.json()
		return data
		
	}catch(error){
		console.log(error)
	}	
}


function filtrarOrdenesUnicas(ordenes) {
	const ordenesUnicas = [];
	const idsVistos = new Set();

	for (const orden of ordenes) {
		if (orden.fecRealIngR !== null &&!idsVistos.has(orden.id)) {
			ordenesUnicas.push(orden);
			idsVistos.add(orden.id);
		}
	}

	return ordenesUnicas;
}

function formatDate(dateString) {
	const date = new Date(dateString);
	const year = date.getFullYear();
	const month = ('0' + (date.getMonth() + 1)).slice(-2);
	const day = ('0' + date.getDate()).slice(-2);
	return `${year}-${month}-${day}`;
}

function parseDate(dateString) {
    const matches = dateString.match(/\d+/g);
    if (matches && matches.length >= 3) {
        return new Date(matches[0], matches[1] - 1, matches[2]);
    }
    return new Date(); // Fecha por defecto si hay un error
}

function drawChart() {
	
	let data = new google.visualization.DataTable();
	data.addColumn('string', 'Task ID');
	data.addColumn('string', 'Task Name');
	data.addColumn('date', 'Start Date');
	data.addColumn('date', 'End Date');
	data.addColumn('number', 'Duration');
	data.addColumn('number', 'Percent Complete');
	data.addColumn('string', 'Dependencies');
	data.addColumn({'type': 'string', 'role': 'tooltip'});

	  
	const lastTenOrders = ordenes.slice(-10);
	console.log(lastTenOrders)
	  
	const rows = lastTenOrders.map(orden => [
		orden.id.toString(),
	    orden.centroOperaciones.split('-')[0] + ' -  ' + orden.observaciones,
	    parseDate(orden.fecRealIngR),
	    parseDate(orden.fechaEntrega),
	    null,
	    30,
	    null,
	    'Prueba tooltip'
  	]);
	console.log(rows)
	
	data.addRows(rows);

	let options = {
		height: 400,
	    gantt: {
	      trackHeight: 30
	    },
		focusTarget: 'category',
		tooltip: { 
		    isHtml: true,
		    //trigger: 'selection'
		}
	  };

  	let chart = new google.visualization.Gantt(document.getElementById('chart_div'));
	
	/*google.visualization.events.addOneTimeListener(chart, 'ready', function () {
	    var observer = new MutationObserver(function (nodes) {
	      Array.prototype.forEach.call(nodes, function(node) {
	        if (node.addedNodes.length > 0) {
	          Array.prototype.forEach.call(node.addedNodes, function(addedNode) {
	            if ((addedNode.tagName === 'rect') && (addedNode.getAttribute('fill') === 'white')) {
	              addedNode.setAttribute('fill', 'transparent');
	              addedNode.setAttribute('stroke', 'transparent');
	              Array.prototype.forEach.call(addedNode.parentNode.getElementsByTagName('text'), function(label) {
	                label.setAttribute('fill', 'transparent');
	              });
	            }
	          });
	        }
	      });
	    });
	    observer.observe(container, {
	      childList: true,
	      subtree: true
	    });
	  });*/
	//console.log(data.toJSON())
  	chart.draw(data , options);
}

function createCustomTooltip(orden) {
    return `<div style="padding:10px; background-color: #f0f0f0; border: 1px solid #999; border-radius: 3px;">
        <strong>${orden.id}</strong><br>
        <strong>Inicio:</strong> ${formatDate(parseDate(orden.fecRealIngR))}<br>
        <strong>Fin:</strong> ${formatDate(parseDate(orden.fechaEntrega))}<br>
        <strong>Porcentaje Completo:</strong> 30%
    </div>`;
}