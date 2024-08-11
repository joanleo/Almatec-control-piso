// Carga de la biblioteca de Google Charts
if (typeof google !== 'undefined' && google.charts) {
    google.charts.load('current', {'packages':['corechart']});
}

// Función principal para dibujar el gráfico
function initializeChart(elementId, updateInterval = 2 * 60 * 1000) {
    if (typeof google !== 'undefined' && google.charts) {
        google.charts.setOnLoadCallback(() => drawChart(elementId, updateInterval));
    } else {
        console.error('Google Charts no está cargado. Asegúrate de incluir el script de Google Charts.');
    }
}

async function drawChart(elementId, updateInterval) {
    let chart = new google.visualization.PieChart(document.getElementById(elementId));
    let options = getChartOptions();

    async function updateChart() {
        let infoParadas = await obtenerInfoParadas();
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
    setInterval(updateChart, updateInterval);
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

async function obtenerInfoParadas(){
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

// Exportar para uso como módulo ES6
//export { initializeChart };

// Exponer para uso como script tradicional
if (typeof window !== 'undefined') {
    window.googleChartsPersonalizado = window.googleChartsPersonalizado || {};
    window.googleChartsPersonalizado.initializeChart = initializeChart;
}