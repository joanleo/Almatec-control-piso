

  google.charts.load('current', {'packages':['gauge']});
  google.charts.setOnLoadCallback(drawIndi);

  function drawIndi() {

    let data_1  = new google.visualization.DataTable();
	  data_1.addColumn('number', '');
	  data_1.addRows(1);
	  data_1.setCell(0, 0, 80);

    let options = {
      width: 600, height: 200,
      redFrom: 0, redTo: 50,
      yellowFrom:50, yellowTo: 75,
      greenFrom:75, greenTo:100,
      minorTicks: 5
    };

    let chart_ind = new google.visualization.Gauge(document.getElementById('indicador_div'));

    chart_ind.draw(data_1, options);

    setInterval(function() {
      data_1.setValue(0, 0, 40 + Math.round(60 * Math.random()));
      chart_ind.draw(data_1, options);
    }, 13000);

  }
