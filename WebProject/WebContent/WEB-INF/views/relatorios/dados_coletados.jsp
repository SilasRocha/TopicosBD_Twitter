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
<link rel="shortcut icon" href="assets/ico/favicon.png">
</head>
<body>
<head>
<body>
	<div class="box-tabela">
		<table class="tabela">
			<tr class="tr-header">
				<th width="100px">Data</th>
				<th width="300px">Tweet Puro</th>
				<th width="300px">Tweet Pós-Processamento</th>
				<th width="105px">Sentimento</th>
			</tr>

			<c:forEach items="${tweetsDataset}" var="tweet">
				<tr class="tr-line">
					<td width="10%"><c:out value="${tweet.date}" /></td>
					<td width="40%"><c:out value="${tweet.text}" /></td>
					<td width="40%"><c:out value="${tweet.processedText}" /></td>					
					
					<c:if test="${tweet.sentiment eq '0'}">					
						<td width="10%"><c:out value="Super negativo" /></td>										
					</c:if>
					
					<c:if test="${tweet.sentiment eq '1'}">					
						<td width="10%"><c:out value="Negativo" /></td>										
					</c:if>
					
					<c:if test="${tweet.sentiment eq '2'}">					
						<td width="10%"><c:out value="Neutro" /></td>										
					</c:if>
					
					<c:if test="${tweet.sentiment eq '3'}">					
						<td width="10%"><c:out value="Positivo" /></td>										
					</c:if>
					
					<c:if test="${tweet.sentiment eq '4'}">					
						<td width="10%"><c:out value="Super positivo" /></td>										
					</c:if>
					
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>