var programa;
var teste;
function go(loc) {
	window.programa = document.getElementById("lista-programa").value;
	var opcao = document.getElementById('telao').src.indexOf(loc);
	
	if (programa != "Selecione..." && opcao == -1) {
		document.getElementById('telao').src = loc;
	}
}

function trocaimagem(){
	var x = document.getElementById("mySelect").options.namedItem("Flash").text;
	document.getElementById("number5").innerHTML = x;	
	}
function proximaEtapa() {
	teste = document.getElementById("lista-programa").value;
	if(teste != "Selecione...") {
		go("relatorios/escolha_acao.html");
	}
	else {
		go("relatorios/escolha_programa.html");
	}
}

function recebePrograma() {
	document.getElementById('graf').style.backgroundImage = "url('../imagens/graf-The Voice Brasil.png')";
}