<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- Declaration to use JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Reação Popular</title>
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
<body>

	<div class="metade">
		<div class="card">
			<div class="emoction">
				<img src="${seriesSentiment.pic}" width="460px" height="300px" />
			</div>
			<div class="nome-emoction">
				<b> Principal sentimento</b>: ${seriesSentiment.mainSentiment}
			</div>
			<div class="hashtag">
				<b> Hashtags: </b> ${seriesSentiment.hashtag}
			</div>
		</div>
	</div>

	<div class="metade">
		<div class="card">
			<div class="table">
				<div class="row">
					<div class="icone-row" id="emo-5"></div>
					<div class="valor-row">${seriesSentiment.sentiment4}</div>
					<div class="porcent-row">${seriesSentiment.sentiment4_perc}%</div>
				</div>
				<div class="row">
					<div class="icone-row" id="emo-4"></div>
					<div class="valor-row">${seriesSentiment.sentiment3}</div>
					<div class="porcent-row">${seriesSentiment.sentiment3_perc}%</div>
				</div>
				<div class="row">
					<div class="icone-row" id="emo-3"></div>
					<div class="valor-row">${seriesSentiment.sentiment2}</div>
					<div class="porcent-row">${seriesSentiment.sentiment2_perc}%</div>
				</div>
				<div class="row">
					<div class="icone-row" id="emo-2"></div>
					<div class="valor-row">${seriesSentiment.sentiment1}</div>
					<div class="porcent-row">${seriesSentiment.sentiment1_perc}%</div>
				</div>
				<div class="row">
					<div class="icone-row" id="emo-1"></div>
					<div class="valor-row">${seriesSentiment.sentiment0}</div>
					<div class="porcent-row">${seriesSentiment.sentiment0_perc}%</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>