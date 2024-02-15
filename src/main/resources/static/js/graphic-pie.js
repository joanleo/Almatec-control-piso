  
  
  google.charts.load('current', {'packages':['corechart']});
  google.charts.setOnLoadCallback(drawChart);

  function drawChart() {

    let data = google.visualization.arrayToDataTable([
      ['Task', 'Hours per Day'],
      ['Cambio de color', 11],
      ['Cambio de rollo', 2],
      ['Falta de operarios', 2],
      ['Cambio de fabricacion de producto', 2],
      ['Problemas de calidad', 2]
    ]);

    let chart = new google.visualization.PieChart(document.getElementById('piechart'));

    chart.draw(data);
  }
