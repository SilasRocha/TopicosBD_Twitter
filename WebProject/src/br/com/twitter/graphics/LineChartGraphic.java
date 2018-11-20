package br.com.twitter.graphics;

import br.com.twitter.jdbc.dao.TweetDAO;

import java.awt.Color;
import java.awt.Image;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChartGraphic {
	
	public String generateGraphic (String serie) {
		TweetDAO tweetDao = new TweetDAO();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String title = "";
		String url = "";
		String url_jsp = "";
		
		dataset = tweetDao.getDataSerie(serie);
		
		switch (serie) {
			case "FLASH": 
				title = "Sentimento x data - Flash";
				url = System.getProperty("catalina.base") + "\\wtpwebapps\\TwitterAnalyzer\\assets\\imagens\\Flash_grafico.png";
				url_jsp = "assets/imagens/Flash_grafico.png";
				break;
			case "GREY":
				title = "Sentimento x data - Grey's Anatomy";
				url = System.getProperty("catalina.base") + "\\wtpwebapps\\TwitterAnalyzer\\assets\\imagens\\Grey_grafico.png";
				url_jsp = "assets/imagens/Grey_grafico.png";
				break;
			case "HOUSE":
				title = "Sentimento x data - House of Cards";
				url = System.getProperty("catalina.base") + "\\wtpwebapps\\TwitterAnalyzer\\assets\\imagens\\House_grafico.png";
				url_jsp = "assets/imagens/House_grafico.png";
				break;
			case "SPN":
				title = "Sentimento x data - Supernatural";	
				url = System.getProperty("catalina.base") + "\\wtpwebapps\\TwitterAnalyzer\\assets\\imagens\\SPN_grafico.png";
				url_jsp = "assets/imagens/SPN_grafico.png";
				break;
			case "TWD":
				title = "Sentimento x data - The Walking Dead";
				url = System.getProperty("catalina.base") + "\\wtpwebapps\\TwitterAnalyzer\\assets\\imagens\\TWD_grafico.png";
				url_jsp = "assets/imagens/TWD_grafico.png";
				break;
			default:
				title = "";				
				break;
		}
		
		JFreeChart grafico = ChartFactory.createLineChart(title, "Dia", "# Tweets", dataset, PlotOrientation.VERTICAL, true, true, false);
				
		try{
			System.out.println("Salvando a imagem no caminho: " + url);
			OutputStream arquivo = new FileOutputStream(url);
			ChartUtilities.writeChartAsPNG(arquivo, grafico, 700, 400);
			
			arquivo.close();
		}catch (Exception e) {
			System.out.println("Exception: " + e);
		}				
		
		return url_jsp;		
	}

}
