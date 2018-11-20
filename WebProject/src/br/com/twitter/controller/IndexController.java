package br.com.twitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import br.com.twitter.jdbc.model.*;
import br.com.twitter.jdbc.dao.*;

@Controller
public class IndexController extends HttpServlet {

    @RequestMapping("/")
    public String execute() {
        System.out.println("Executando a lógica com Spring MVC");
        return "home";
    }
    
    @RequestMapping("/reacaopopular")
    public String analiseSentimento(HttpServletRequest request, String serie, HttpSession session) {
        TweetDAO tweetDao = new TweetDAO();
    	SeriesSentiment sentiment = new SeriesSentiment();
        
    	switch(serie) {
    	case "1":  
    		tweetDao.getSeriesSentiment("FLASH");
    		sentiment.setSerie("Flash");
    		sentiment.setPic("flash.jpg");
    		sentiment.setHashtag("[#flashseries] [#flash] [#theflash] [#flasharrowverse]");
    		
    	}
    	
    	System.out.println(serie);
        return "relatorios/reacao_popular";
    }
    
    @RequestMapping("/graficos")
    public String analiseSentimentoGraficos(HttpServletRequest request) {
        System.out.println("Executando a lógica com Spring MVC");
        return "relatorios/graficos";
    }
    
    @RequestMapping("/dados")
    public String analiseSentimentoDados(HttpServletRequest request) {
        System.out.println("Executando a lógica com Spring MVC");
        return "relatorios/dados_coletados";
    }
    
    @RequestMapping("/escolhaserie")
    public String escolhaSerie(HttpServletRequest request) {
        System.out.println("Executando a lógica com Spring MVC");
        return "relatorios/escolha_serie";
    }
    
    @RequestMapping("/escolhaacao")
    public String escolhaAcao(HttpServletRequest request) {
        System.out.println("Executando a lógica com Spring MVC");
        return "relatorios/escolha_acao";
    }
    
    @RequestMapping("/getSentiment")
    public String recuperaSentimento(HttpServletRequest request) {
        System.out.println("Executando a lógica com Spring MVC");
        return "home";
    }
}