import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import analyzer.Tweet;
import analyzer.TweetDAO;
import analyzer.PreProcessing;
import analyzer.Translation;
import analyzer.SentimentAnalyzer;

public class Main {

	public static void main(String[] args) throws IOException {
		ArrayList<Tweet> tweets = readArchive();
		tweets = processing(tweets);
		insertDatabase(tweets);
	}

	// lê arquivo CSV e cria dataset de tweets
	public static ArrayList<Tweet> readArchive() throws IOException {
		Scanner scanner = new Scanner(new File("datasetTweet.csv"), "UTF-8");
		Scanner dataScanner = null;
		int index = 0;
		ArrayList<Tweet> tweetDataset = new ArrayList<>();

		while (scanner.hasNextLine()) {
			dataScanner = new Scanner(scanner.nextLine());
			dataScanner.useDelimiter(";");
			Tweet tweet = new Tweet();

			while (dataScanner.hasNext()) {
				String data = dataScanner.next();
				if (index == 0)
					tweet.setId(Long.parseLong(data));
				else if (index == 1)
					tweet.setDate(data);
				else if (index == 2)
					tweet.setTime(data);
				else if (index == 3)
					tweet.setText(data);
				else if (index == 4)
					tweet.setHashtag(data);
				else if (index == 5)
					tweet.setSerie(data);
				else if (index == 6)
					tweet.setLanguage(data);
				else
					System.out.println("invalid data::" + data);

				index++;
			}

			index = 0;
			tweetDataset.add(tweet);
		}

		scanner.close();
		return tweetDataset;
	}

	// faz um pré-processamento nos tweets, traduz para a língua inglesa e calcula o sentimento
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

}
