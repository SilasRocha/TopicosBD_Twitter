<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- Declaration to use JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Gráficos</title>
<meta charset="utf-8">
<!--link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"/-->
<!--script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script-->

<script>
	window.onload = function() {
		var chart = new CanvasJS.Chart("chartContainer", {
			title : {
				text : "Análise de sentimento por data"
			},
			axisX: {
				valueFormatString: "DD MMM"
			},
			axisY2 : {
				title : "Quantidade",
			},
			toolTip : {
				shared : true
			},
			legend : {
				cursor : "pointer",
				verticalAlign : "top",
				horizontalAlign : "center",
				dockInsidePlotArea : true,
				itemclick : toogleDataSeries
			},
			data : ${dataset}
		});

		chart.render();

		function toogleDataSeries(e) {
			if (typeof (e.dataSeries.visible) === "undefined"
					|| e.dataSeries.visible) {
				e.dataSeries.visible = false;
			} else {
				e.dataSeries.visible = true;
			}
			chart.render();
		}

}
</script>

<link href="assets/css/graficos.css" rel="stylesheet" />
<link rel="shortcut icon" href="assets/ico/favicon.png">

</head>
<body>

	<div id="chartContainer" style="height: 100%; width: 100%" ></div>	
	<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

</body>
</html>