

  google.charts.load('current', {'packages':['gauge']});
  google.charts.setOnLoadCallback(drawIndi);
  


  async function drawIndi() {
	  
	let productividad = await cacularProductividad();
	    
	let data_1  = new google.visualization.DataTable();
	data_1.addColumn('number', '');
	data_1.addRows(1);
	data_1.setCell(0, 0, productividad);

    let options = {
      width: 600, height: 200,
      redFrom: 0, redTo: 50,
      yellowFrom:50, yellowTo: 75,
      greenFrom:75, greenTo:100,
      minorTicks: 5
    };

    let chart_ind = new google.visualization.Gauge(document.getElementById('indicador_div'));

    chart_ind.draw(data_1, options);

    /*setInterval(async function() {
      data_1.setValue(0, 0, await cacularProductividad());
      console.log("Graficando productividad")
      chart_ind.draw(data_1, options)
      llenarTablaProductividadOperario()
    }, 13000)*/

  }

async function cacularProductividad() {
    let productividad = 1;
    const tiemposOperarios = await obtenerTiemposOperariosProceso();
    console.log(tiemposOperarios);

    let tiemposImproductivos = 0;
    let tiemposProductivos = 0;

    for (const operario of tiemposOperarios) {
        tiemposImproductivos += operario.improductivo;
        tiemposProductivos += operario.productivo;
    }

    const totalTiempos = tiemposImproductivos + tiemposProductivos;
    productividad = Number(((1 - tiemposImproductivos / totalTiempos) * 100).toFixed(2));
    if (totalTiempos === 0) {
        productividad = 0;
    }
    console.log(productividad);
    return productividad;
}
