
google.charts.load('current', {'packages':['corechart', 'gauge']});
google.charts.setOnLoadCallback(initDashboard);

async function initDashboard() {
    const centrosTrabajo = await fetchCentrosT();
    const dashboardContainer = document.getElementById('dashboard-container');
    
	const configProcesos = await obtenerConfigProcesosDia()
		
    centrosTrabajo.forEach(centro => {
        
		const configProceso = configProcesos.find(cp => cp.idCentroTrabajo.id === centro.id);
		        
        if (configProceso) {
	        const centroElement = createCentroElement(centro);
	        dashboardContainer.appendChild(centroElement);
	        drawProductividadGauge(configProceso, centro.id);
			actualizarKilosTotales(configProceso, centro.id);
	        drawParadasPieChart(configProceso, centro.id);
        }
		
    });
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

async function obtenerTiemposOperariosProceso(configProceso){
	if(configProceso != null){
		try{
			const response = await fetch(`/centros-trabajo/${configProceso.id}/tiempos_operarios`)
			if(!response.ok){
				const error = await response.json()
				throw new Error(error)
			}
			return await response.json()
		}catch(error){
			console.error(error)
		}	
	}
	
}

function createCentroElement(centro) {
    const centroDiv = document.createElement('div');
    centroDiv.className = 'centro-trabajo';
    centroDiv.innerHTML = `
        <h2 >${centro.nombre}</h2>
		<div class="main-charts-container">
	        <div class="chart-container">
	            <div id="productividad-${centro.id}" style="width: 150px; height: 150px;"></div>
	            <h3>Disponibilidad</h3>
	        </div>
	        <div class="card-container">
	            <div class="card" style="width: 150px; height: 150px;">
	                <div class="value" id="kilosTotales-${centro.id}">0</div>
	            </div>
	            <h3>Kilos Fabricados</h3>
	        </div>
	        <div class="chart-container">
	            <div id="paradas-${centro.id}" style="width: 200px; height: 150px;"></div>
	            <h3>Paradas</h3>
	        </div>
		</div>
    `;
    return centroDiv;
}

async function obtenerConfigProcesosDia(){
	try{
		console.log("obteneniendo configs")
		const response = await fetch(`/config-procesos/hoy`)
		if(!response.ok){
			const error = await response.json()
			throw new Error(error)
		}
		return await response.json()
	}catch(error){
		console.error(error)
	}
}

function cacularProductividad(tiemposOperarios) {
    let productividad = 1;
    let tiemposImproductivos = 0;
    let tiemposProductivos = 0;
	if(tiemposOperarios){
	    for (const operario of tiemposOperarios) {
	        tiemposImproductivos += operario.improductivo;
	        tiemposProductivos += operario.productivo;
	    }		
	}

    const totalTiempos = tiemposImproductivos + tiemposProductivos;
    productividad = Number(((1 - tiemposImproductivos / totalTiempos) * 100).toFixed(2));
    if (totalTiempos === 0) {
        productividad = 0;
    }
    return productividad;
}

async function obtenerDatosKilosFabricados(configProceso) {
    try {
        const piezasCT = await obtenerPiezasCtProceso(configProceso);
        console.log(`Piezas CT ${configProceso.idCentroTrabajo.id} configProceso ${configProceso.id}`, piezasCT);
        return piezasCT;
    } catch (error) {
        console.error('Error al obtener datos de kilos fabricados:', error);
        return [];
    }
}

async function obtenerPiezasCtProceso(configProceso){
	if(configProceso != null){
		try{
			const response = await fetch(`/centros-trabajo/${configProceso.idCentroTrabajo.id}/${configProceso.id}/piezas-operarios-ct-proceso`)
			if(!response.ok){
				console.error("Error en la solicitud:", response.statusText);
				throw new Error("Error en la asignacion de pieza")
			}
			const data = await response.json()
			return data
			
		}catch (error){
			console.log(error)
		}			
	}
}

function calcularKilosTotales(datos) {
	
    return datos.reduce((total, item) => total + (item.peso * item.cantFabricada), 0);
}

async function actualizarKilosTotales(configProceso) {
    try {
        const datos = await obtenerDatosKilosFabricados(configProceso);
        if (datos && datos.length > 0) {
            const kilosTotales = calcularKilosTotales(datos);
            const elementoKilosTotales = document.getElementById(`kilosTotales-${configProceso.idCentroTrabajo.id}`);
            if (elementoKilosTotales) {
                elementoKilosTotales.textContent = kilosTotales.toFixed(2);
            } else {
                console.error('Elemento con ID "kilosTotales" no encontrado');
            }
        } else {
            console.warn('No se obtuvieron datos para calcular los kilos totales');
        }
    } catch (error) {
        console.error('Error al actualizar kilos totales:', error);
    }
}

async function drawProductividadGauge(configProceso, centroId) {
	const tiemposOperarios = await obtenerTiemposOperariosProceso(configProceso);
	
	let disponibillidad = cacularProductividad(tiemposOperarios);
	
    const data = google.visualization.arrayToDataTable([
        ['Label', 'Value'],
        ['', disponibillidad]
    ]);

    const options = {
      width: 400, height: 120,
      redFrom: 0, redTo: 50,
      yellowFrom: 50, yellowTo: 75,
      greenFrom: 75, greenTo: 100,
      minorTicks: 5,
      animation: {
          duration: 400,
          easing: 'out',
      },
      labelFontSize: 9
    };

    const chart = new google.visualization.Gauge(document.getElementById(`productividad-${centroId}`));
    
    google.visualization.events.addListener(chart, 'ready', function() {
        setTimeout(function() {
            modifyGaugeStyles(centroId);
        }, 100);
    });

    chart.draw(data, options);
}

function modifyGaugeStyles(centroId) {
    const gaugeElement = document.getElementById(`productividad-${centroId}`);
    if (!gaugeElement) {
        console.error(`No se encontró el elemento para el centro ${centroId}`);
        return;
    }
    const svgElement = gaugeElement.querySelector('svg');
    if (svgElement) {
        // Modificar el primer texto (que debería ser 'Disponibilidad')
        const firstText = svgElement.querySelector('text');
        if (firstText) {
            firstText.setAttribute('font-size', '14');
            firstText.setAttribute('font-weight', 'bold');
            firstText.setAttribute('y', parseFloat(firstText.getAttribute('y')) - 5);
        } else {
            console.log(`No se encontró el texto principal para centro ${centroId}`);
        }

        // Modificar los otros textos si es necesario
        const otherTexts = svgElement.querySelectorAll('text:not(:first-child)');
        otherTexts.forEach(text => {
            text.setAttribute('font-size', '8');
        });
    } else {
        console.error(`No se encontró el SVG para el centro ${centroId}`);
    }
}

function getChartOptions() {
    return {
        title: 'Distribución de Tiempos por Parada',
        pieSliceText: 'percentage',
        legend: 'right',
        chartArea: {width: '80%', height: '80%'},
        backgroundColor: {fill: '#F7F7F7'},
        is3D: true
    };
}

async function obtenerInfoParadas(configProceso){
    if(typeof configProceso !== 'undefined' && configProceso != null){
        try{
            const response = await fetch(`/centros-trabajo/proceso/${configProceso.id}/paradas`);
            if(!response.ok){
                const error = await response.json();
                throw new Error(error);
            }
            return await response.json();
        } catch(error) {
            console.error(error);
            return null;
        }
    }
    return null;
}

const baseColors = ['#4285F4', '#34A853', '#FBBC05', '#EA4335', '#FF6D01'];

function generateColors(numColors) {
    let colors = [...baseColors];
    while (colors.length < numColors) {
        let newColor = '#' + Math.floor(Math.random()*16777215).toString(16);
        if (!colors.includes(newColor)) {
            colors.push(newColor);
        }
    }
    return colors;
}

function prepareData(infoParadas) {
    let data = new google.visualization.DataTable();
    data.addColumn('string', 'Parada');
    data.addColumn('number', 'Tiempo');
    
    if (infoParadas && infoParadas.length > 0) {
        for (const registro of infoParadas) {
            data.addRow([registro.nombre, registro.tiempo]);
        }
    } else {
        data.addRow(['Sin datos', 1]);
    }
    
    return data;
}

async function drawParadasPieChart(configProceso, centroId) {
    const chart = new google.visualization.PieChart(document.getElementById(`paradas-${centroId}`));
	let options = getChartOptions();
	
	async function updateChart() {
        let infoParadas = await obtenerInfoParadas(configProceso);
        let data = prepareData(infoParadas);
        
        if (infoParadas && infoParadas.length > 0) {
            options.colors = generateColors(infoParadas.length);
            options.title = 'Distribución de Tiempos por Parada';
            options.pieSliceText = 'percentage';
            options.slices = {};
        } else {
            options.pieSliceText = 'label';
            options.slices = {0: {color: '#CCCCCC'}};
            options.title = 'No hay datos disponibles';
        }
        
        chart.draw(data, options);
    }
	
	updateChart();
}