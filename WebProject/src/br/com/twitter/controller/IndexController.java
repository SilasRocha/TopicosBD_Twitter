package br.com.twitter.controller;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.twitter.jdbc.model.*;
import br.com.twitter.graphics.LineChartGraphic;
import br.com.twitter.graphics.LineChartGraphicJS;
import br.com.twitter.jdbc.dao.*;

@Controller
public class IndexController extends HttpServlet {

	@RequestMapping("/")
	public String execute() {
		System.out.println("Executando a l�gica com Spring MVC");
		return "home";
	}

	@RequestMapping("/reacaopopular")
	public String analiseSentimento(HttpServletRequest request, String serie, HttpSession session) {
		TweetDAO tweetDao = new TweetDAO();
		SeriesSentiment sentiment = new SeriesSentiment();
		System.out.println(serie);

		switch (serie) {
		case "1":
			sentiment = tweetDao.getSeriesSentiment("FLASH");
			sentiment.setSerie("Flash");
			sentiment.setPic("assets/imagens/flash.jpg");
			sentiment.setHashtag("#flashseries #flash #theflash #flasharrowverse");
			session.setAttribute("seriesSentiment", sentiment);
			break;
		case "2":
			sentiment = tweetDao.getSeriesSentiment("GREY");
			sentiment.setSerie("Grey's Anatomy");
			sentiment.setPic("assets/imagens/greys.jpg");
			sentiment.setHashtag("#greysanatomy #meredithgrey #greys #greysabc");
			session.setAttribute("seriesSentiment", sentiment);
			break;
		case "3":
			sentiment = tweetDao.getSeriesSentiment("HOUSE");
			sentiment.setSerie("House of Cards");
			sentiment.setPic("assets/imagens/house.jpg");
			sentiment.setHashtag("#houseofcards #houseofcardsseries #kleinehumboldtgalerie #brittalumer");
			session.setAttribute("seriesSentiment", sentiment);
			break;
		case "4":
			sentiment = tweetDao.getSeriesSentiment("SPN");
			sentiment.setSerie("Supernatural");
			sentiment.setPic("assets/imagens/super.jpg");
			sentiment.setHashtag("#supernatural #spn #deanwinchester #samwinchester");
			session.setAttribute("seriesSentiment", sentiment);
			break;
		case "5":
			sentiment = tweetDao.getSeriesSentiment("TWD");
			sentiment.setSerie("The Walking Dead");
			sentiment.setPic("assets/imagens/twd.jpg");
			sentiment.setHashtag("#walkingdead #twd #thewalkingdead #rickgrimes");
			session.setAttribute("seriesSentiment", sentiment);
			break;
		default:
			System.out.println("S�rie n�o mapeada");
			break;
		}

		return "relatorios/reacao_popular";
	}

	@RequestMapping("/dados")
	public String analiseSentimentoDados(HttpServletRequest request, String serie, HttpSession session) {
		TweetDAO tweetDao = new TweetDAO();
		List<Tweet> tweets = new ArrayList<Tweet>();
		System.out.println(serie);

		switch (serie) {
		case "1":
			tweets = tweetDao.getTweetsSerie("FLASH");
			System.out.println(tweets.get(0).getProcessedText());
			session.setAttribute("tweetsDataset", tweets);
			break;
		case "2":
			tweets = tweetDao.getTweetsSerie("GREY");
			session.setAttribute("tweetsDataset", tweets);
			break;
		case "3":
			tweets = tweetDao.getTweetsSerie("HOUSE");
			session.setAttribute("tweetsDataset", tweets);
			break;
		case "4":
			tweets = tweetDao.getTweetsSerie("SPN");
			session.setAttribute("tweetsDataset", tweets);
			break;
		case "5":
			tweets = tweetDao.getTweetsSerie("TWD");
			session.setAttribute("tweetsDataset", tweets);
			break;
		default:
			System.out.println("S�rie n�o mapeada");
			break;
		}

		return "relatorios/dados_coletados";
	}

	@RequestMapping("/graficos")
	public String analiseSentimentoGraficos(HttpServletRequest request, String serie, HttpSession session) {
		LineChartGraphic graphic = new LineChartGraphic();
		String url;

		switch (serie) {
		case "1":
			url = graphic.generateGraphic("FLASH");
			session.setAttribute("graphicPic", url);	
			break;
		case "2":
			url = graphic.generateGraphic("GREY");
			session.setAttribute("graphicPic", url);		
			break;			
		case "3":
			url = graphic.generateGraphic("HOUSE");
			session.setAttribute("graphicPic", url);		
			break;			
		case "4":			
			url = graphic.generateGraphic("SPN");
			session.setAttribute("graphicPic", url);		
			break;			
		case "5":
			url = graphic.generateGraphic("TWD");
			session.setAttribute("graphicPic", url);		
			break;			
		default:
			System.out.println("S�rie n�o mapeada");
			break;
		}

		return "relatorios/graficos";
	}
	
	@RequestMapping("/graficoJS")
	public String analiseGraficoJS(HttpServletRequest request, String serie, HttpSession session) {
		LineChartGraphicJS graphic = new LineChartGraphicJS();
		String result;

		switch (serie) {
		case "1":
			result = graphic.generateGraphic("FLASH");
			session.setAttribute("dataset", result);
			break;
		case "2":
			result = graphic.generateGraphic("GREY");
			session.setAttribute("dataset", result);			
			break;			
		case "3":
			result = graphic.generateGraphic("HOUSE");
			session.setAttribute("dataset", result);
			break;			
		case "4":			
			result = graphic.generateGraphic("SPN");
			session.setAttribute("dataset", result);		
			break;			
		case "5":
			result = graphic.generateGraphic("TWD");
			session.setAttribute("dataset", result);
			break;			
		default:
			System.out.println("S�rie n�o mapeada");
			break;
		}

		return "relatorios/graficos";
	}

	@RequestMapping("/escolhaserie")
	public String escolhaSerie(HttpServletRequest request) {
		System.out.println("Executando a l�gica com Spring MVC");
		return "relatorios/escolha_serie";
	}

	@RequestMapping("/escolhaacao")
	public String escolhaAcao(HttpServletRequest request) {
		System.out.println("Executando a l�gica com Spring MVC");
		return "relatorios/escolha_acao";
	}

	@RequestMapping("/getSentiment")
	public String recuperaSentimento(HttpServletRequest request) {
		System.out.println("Executando a l�gica com Spring MVC");
		return "home";
	}
}