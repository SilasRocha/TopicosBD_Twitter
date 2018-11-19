<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>Twitter Analyzer</title>
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

<link href="assets/css/style.css" rel="stylesheet" />
<script src="assets/js/script.js"></script>
</head>

<body>
	<div class="total">
		<div class="conteudo">
			<div class="lista">
				<p class="lbl-serie">Escolha uma s�rie:</p>

				<select class="cmb-serie" id="lista-programa"
					onchange="proximaEtapa();">
					<option>Selecione...</option>
					<optionvalue"1">Flash</option>
					<optionvalue"2">Greys Anatomy</option> 
					<option value"3">House</option>
					<option value"4">Supernatural</option> 
					<option value"5">The Walking Dead</option> 					
				</select>
			</div>

			<div class="box">
				<iframe src="relatorios/escolha_programa.html" id="telao"></iframe>
			</div>
		</div>
		<div class="rodape">
			<div class="opcao">
				<input type="button" onclick="go('relatorios/reacao_popular.html');"
					class="btn-opcao" id="reacao" value="Rea��o Popular"></input>
			</div>
			<div class="opcao">
				<input type="button" onclick="go('relatorios/dados_coletados.html')"
					class="btn-opcao" id="dados" value="Dados Coletados"></input>
			</div>
			<div class="opcao">
				<input type="button" onclick="go('relatorios/graficos.html')"
					class="btn-opcao" id="graficos" value="Gr�ficos"></input>
			</div>
			<div class="opcao">
				<input type="button" onclick="go('relatorios/comparativo.html')"
					class="btn-opcao" id="comparativo" value="Comparativo"></input>
			</div>
		</div>
	</div>
</body>
</html>