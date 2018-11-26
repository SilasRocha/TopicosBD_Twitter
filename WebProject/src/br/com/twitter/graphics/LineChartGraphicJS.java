package br.com.twitter.graphics;

import br.com.twitter.jdbc.dao.TweetDAO;

public class LineChartGraphicJS {
	
	public String generateGraphic (String serie) {
		TweetDAO tweetDao = new TweetDAO();
		
		String result = "";
		String[] sentiment = {"Super negativo", "Negativo", "Neutro", "Positivo", "Super positivo"};
		
		for (int i = 0; i < 5; i++) {
			if (i == 0)
				result = "\n[";
			
			/*result = result + 
					"\n {\n type: &quot;line&quot;,\n" +
					" axisYType: &quot;secondary&quot;,\n" +
					" name: &quot;" + sentiment[i] + "&quot;,\n" +
					" showInLegend: true,\n" +
					" markerSize: 0,\n" +
					" dataPoints: [ \n" + tweetDao.getDataSerie(serie, i) + "]";*/
			
			result = result + 
			"\n {\n type: \"line\",\n" +
			" axisYType: \"secondary\",\n" +
			" name: \"" + sentiment[i] + "\",\n" +
			" showInLegend: true,\n" +
			" markerSize: 0,\n" +
			" dataPoints: [ \n" + tweetDao.getDataSerie(serie, i) + "]";
			
			if (i == 4) {
				result = result + "\n}";
				result = result + "\n]";
			}else {
				result = result + "\n},";
			}
		}	
	
		System.out.println("Dataset" + result);
	return result;
	}	
	
}
