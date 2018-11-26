var programa;
var teste;
function go(loc) {
	window.programa = document.getElementById("lista-serie").value;
	var opcao = document.getElementById('telao').src.indexOf(loc);
	
	if (programa != "Selecione..." && opcao == -1) {
		document.getElementById('telao').src = loc;
	}
}

function proximaEtapa() {
	serie = document.getElementById("lista-serie").value;
	if(serie != "Selecione...") {
		go("escolhaacao");
	}
	else {
		go("escolhaserie");
	}
}

function analiseSentimento() {
	serie = document.getElementById("lista-serie").value;
	var url = 'reacaopopular?serie=' + serie;
	go(url);
}

function exibeDados() {
	serie = document.getElementById("lista-serie").value;
	var url = 'dados?serie=' + serie;
	go(url);
}

function exibeGraficos() {
	serie = document.getElementById("lista-serie").value;
	var url = 'graficoJS?serie=' + serie;
	go(url);
}