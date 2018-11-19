package analyzer;

import java.util.Properties;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

/*

 * "Very negative" = 0
 * "Negative" = 1
 * "Neutral" = 2
 * "Positive" = 3
 * "Very positive" = 4

 */

public class SentimentAnalyzer {

	StanfordCoreNLP pipeline;

	public void initialize() {
		Properties properties = new Properties();
		properties.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
		pipeline = new StanfordCoreNLP(properties);
	}

	public int getSentimentResult(String text) {		
		int mainSentiment = 0;
		if (text != null && text.length() > 0) {
			Annotation annotation = pipeline.process(text);
			int longest = 0;
			
			for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {				
				Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);				
				int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
				String partText = sentence.toString();
                if (partText.length() > longest) {
                    mainSentiment = sentiment;
                    longest = partText.length();                    
                }
			}
		}
		System.out.println("Principal sentimento: " + mainSentiment);
		return mainSentiment;
	}
}