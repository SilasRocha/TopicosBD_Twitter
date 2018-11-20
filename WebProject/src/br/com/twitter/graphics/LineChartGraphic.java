package br.com.twitter.graphics;

import br.com.twitter.jdbc.dao.TweetDAO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChartGraphic {
	
	public String generateGraphic (String serie) {
		TweetDAO tweetDao = new TweetDAO();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String title = "";
		String url = "";
		
		dataset = tweetDao.getDataSerie(serie);
		
		switch (serie) {
			case "1": 
				title = "Sentimento x data - Flash";				
				break;
			case "2":
				title = "Sentimento x data - Grey's Anatomy";				
				break;
			case "3":
				title = "Sentimento x data - House of Cards";				
				break;
			case "4":
				title = "Sentimento x data - Supernatural";				
				break;
			case "5":
				title = "Sentimento x data - The Walking Dead";				
				break;
			default:
				title = "";
				url = "";
				break;
		}
		
		JFreeChart grafico = ChartFactory.createLineChart(title, "Dia", "# Tweets", dataset, PlotOrientation.VERTICAL, true, true, false);                          
		
		ChartFrame frame1=new ChartFrame(serie,grafico);
		frame1.setVisible(true);
		frame1.setSize(300,300);
		
		return url;		
		
	}

}
