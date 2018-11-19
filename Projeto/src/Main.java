import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import analyzer.Tweet;
import analyzer.TweetDAO;
import analyzer.PreProcessing;
import analyzer.Translation;
import analyzer.SentimentAnalyzer;

public class Main {
	
	static String[] series = {"FLASH", "GREY", "HOUSE", "SPN", "TWD"};

	public static void main(String[] args) throws IOException {
		
		/* Código para processar tweets*/
		/*ArrayList<Tweet> tweets = readArchive();
		tweets = processing(tweets);
		updateDatabase(tweets);*/
		
		printSeriesSentiment(getSeriesSentiment());
		
		
	}

	// leitura do arquivo csv
	public static ArrayList<Tweet> readArchive() throws IOException {		
		String arquivoCSV = "exceptions.csv";
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ";";
		ArrayList<Tweet> tweetDataset = new ArrayList<>();
		try {			
			br = new BufferedReader(new FileReader(arquivoCSV));
			while ((linha = br.readLine()) != null) {
				Tweet tweet = new Tweet();
				String[] twt = linha.split(csvDivisor);
				tweet.setId(Long.parseLong(twt[0]));
				tweet.setDate(twt[1]);
				tweet.setTime(twt[2]);
				tweet.setText(twt[3]);
				tweet.setHashtag(twt[4]);
				tweet.setSerie(twt[5]);
				tweet.setLanguage(twt[6]);
				tweetDataset.add(tweet);
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return tweetDataset;
	}	

	// faz um pré-processamento nos tweets, traduz para a língua inglesa e calcula o
	// sentimento
	public static ArrayList<Tweet> processing(ArrayList<Tweet> tweets) {

		SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
		sentimentAnalyzer.initialize();

		for (Tweet tweet : tweets) {
			String text = Translation.translate(PreProcessing.preprocessar(tweet.getText()), tweet.getLanguage());
			System.out.println(text);
			tweet.setProcessedText(text);
			tweet.setSentiment(sentimentAnalyzer.getSentimentResult(text));
		}

		return tweets;
	}

	// insere dataset de tweets no banco de dados
	public static void insertDatabase(ArrayList<Tweet> tweets) {
		TweetDAO tweetDao = new TweetDAO();
		tweetDao.insert(tweets);
		System.out.println("Tweets adicionados no banco");
	}
	
	// atualiza dataset de tweets no banco de dados
	public static void updateDatabase(ArrayList<Tweet> tweets) {
			TweetDAO tweetDao = new TweetDAO();
			tweetDao.update(tweets);
			System.out.println("Tweets adicionados no banco");
	}
	
	// recupera um vetor com o número de tweets por sentimento de cada série
	public static int[][] getSeriesSentiment () {		
		TweetDAO tweetDao = new TweetDAO();
		int[][] sentiment = new int[5][5];
		int index = 0;		
		
		while (index < series.length) {			
			sentiment[index] = tweetDao.getSeriesSentiment(series[index]);			
			index++;
		}
		
		return sentiment;
	}
	
	// para fins de teste, imprime uma tabela com o número de tweets por sentimento de cada série
	public static void printSeriesSentiment (int[][] sentiment) {		
		for (int i = 0; i < sentiment.length; i++) {			
			System.out.print(series[i] + " | ");
			for (int j = 0; j < sentiment.length; j++) {
				System.out.print(sentiment[i][j]);
				System.out.print(" | ");
			}
			System.out.println("\n");
		}		
	}
}
