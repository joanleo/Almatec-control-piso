  
  
  google.charts.load('current', {'packages':['corechart']});
  google.charts.setOnLoadCallback(drawChart);

  async function drawChart() {
	let infoParadas = await obtenerInfoParadas()
	console.log(infoParadas)
	
	let paradas = [['Parada','Tiempo']]
	for(const registro of infoParadas){
		paradas.push([registro.nombre, registro.tiempo])
	}

    /*let data = google.visualization.arrayToDataTable([
      ['Task', 'Hours per Day'],
      ['Cambio de color', 11],
      ['Cambio de rollo', 2],
      ['Falta de operarios', 2],
      ['Cambio de fabricacion de producto', 2],
      ['Problemas de calidad', 2]
    ]);*/
	let data = google.visualization.arrayToDataTable(paradas)
    let chart = new google.visualization.PieChart(document.getElementById('piechart'));

    chart.draw(data);
  }
  
  async function obtenerInfoParadas(){
	  if(configProceso != null){
		try{
			const response = await fetch(`/centros-trabajo/${centroTSelected.id}/proceso/${configProceso.id}/paradas`)
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
