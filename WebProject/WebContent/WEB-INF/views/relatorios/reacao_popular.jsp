<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Rea��o Popular</title>
<meta charset="utf-8">

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous" />
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qv'yjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<!--link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"/-->
<!--script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script-->

<link href="assets/css/reacao_popular.css" rel="stylesheet" />
<script src="assets/js/script.js"></script>

</head>
<body onload="trocaimagem();">

	<div class="metade">
		<div class="card">
			<div class="emoction"></div>
			<div class="nome-emoction" id="nomeserie">Sentimento: Negativo
			</div>
		</div>
	</div>

	<div class="metade">
		<div class="card">
			<div class="table">
				<div class="row">
					<div class="icone-row" id="emo-5"></div>
					<div class="valor-row">
						<p id="number5"></p>

					</div>
					<div class="porcent-row">0%</div>
				</div>
				<div class="row">
					<div class="icone-row" id="emo-4"></div>
					<div class="valor-row">8</div>
					<div class="porcent-row">16%</div>
				</div>
				<div class="row">
					<div class="icone-row" id="emo-3"></div>
					<div class="valor-row">23</div>
					<div class="porcent-row">46%</div>
				</div>
				<div class="row">
					<div class="icone-row" id="emo-2"></div>
					<div class="valor-row">18</div>
					<div class="porcent-row">36%</div>
				</div>
				<div class="row">
					<div class="icone-row" id="emo-1"></div>
					<div class="valor-row">1</div>
					<div class="porcent-row">2%</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>