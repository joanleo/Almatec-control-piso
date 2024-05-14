  google.charts.load('current', {'packages':['corechart']});
  google.charts.setOnLoadCallback(drawChart);

  async function drawChart() {
	let infoParadas = await obtenerInfoParadas()
	//console.log(infoParadas)
	
	let paradas = [['Parada','Tiempo']]
	if(infoParadas){
		for(const registro of infoParadas){
			paradas.push([registro.nombre, registro.tiempo])
		}		
	}

	let data = google.visualization.arrayToDataTable(paradas)
    let chart = new google.visualization.PieChart(document.getElementById('piechart'));

    chart.draw(data)
    
    setInterval(async function() {		
		infoParadas = await obtenerInfoParadas()
		
		let paradas = [['Parada','Tiempo']]
		if(infoParadas){
			for(const registro of infoParadas){
				paradas.push([registro.nombre, registro.tiempo])
			}		
		}
		
		data = google.visualization.arrayToDataTable(paradas)
	  	//console.log("Graficando paradas")
	  	chart.draw(data)
    }, 13000)
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
