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

function trocaimagem(){
	var x = document.getElementById("mySelect").options.namedItem("Flash").text;
	document.getElementById("number5").innerHTML = x;	
}

function recebePrograma() {
	document.getElementById('graf').style.backgroundImage = "url('../imagens/graf-The Voice Brasil.png')";
}