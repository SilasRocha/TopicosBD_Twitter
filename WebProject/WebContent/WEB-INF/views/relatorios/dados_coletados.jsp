<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- Declaration to use JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dados Coletados</title>
<meta charset="utf-8">

<!--link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"/-->
<!--script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script-->

<script src="assets/js/script.js"></script>
<link href="assets/css/dados_coletados.css" rel="stylesheet" />
</head>
<body>
<head>
<body>
	<div class="box-tabela">
		<table class="tabela">
			<tr class="tr-header">
				<th width="100px">Data</th>
				<th width="350px">Tweet Puro</th>
				<th width="340px">Tweet Pós-Processamento</th>
				<th width="105px">Sentimento</th>
			</tr>

			<c:forEach items="${tweetsDataset}" var="tweet">
				<tr class="tr-line">
					<td width="200px"><c:out value="${tweet.date}" /></td>
					<td width="200px"><c:out value="${tweet.text}" /></td>
					<td width="200px"><c:out value="${tweet.processedText}" /></td>
					<td width="100px"><c:out value="${tweet.sentiment}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>