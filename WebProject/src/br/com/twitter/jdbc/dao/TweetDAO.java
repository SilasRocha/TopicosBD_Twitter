package br.com.twitter.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.twitter.jdbc.connection.*;
import br.com.twitter.jdbc.model.*;

public class TweetDAO {

	// Conex�o com o banco de dados
	private Connection connection;

	public TweetDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void insert(ArrayList<Tweet> tweets) {
		String sql = "INSERT INTO TWEETS " + "(id,date,time,text,processed_text,hashtag,sentiment,serie,language)"
				+ " VALUES (?,?,?,?,?,?,?,?,?)";

		try {
			// prepared statement para inser��o
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			for (Tweet tweet : tweets) {
				stmt.setLong(1, tweet.getId());
				stmt.setString(2, tweet.getDate());
				stmt.setString(3, tweet.getTime());
				stmt.setString(4, tweet.getText());
				stmt.setString(5, tweet.getProcessedText());
				stmt.setString(6, tweet.getHashtag());
				stmt.setInt(7, tweet.getSentiment());
				stmt.setString(8, tweet.getSerie());
				stmt.setString(9, tweet.getLanguage());

				// executa
				stmt.execute();
			}

			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void update(ArrayList<Tweet> tweets) {
		String sql = "UPDATE TWEETS SET text=?, processed_text=?, hashtag=?, sentiment=?, serie=?, language=? WHERE id=?";

		try {
			// prepared statement para inser��o
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			for (Tweet tweet : tweets) {
				stmt.setString(1, tweet.getText());
				stmt.setString(2, tweet.getProcessedText());
				stmt.setString(3, tweet.getHashtag());
				stmt.setInt(4, tweet.getSentiment());
				stmt.setString(5, tweet.getSerie());
				stmt.setString(6, tweet.getLanguage());
				stmt.setLong(7, tweet.getId());

				// executa
				stmt.execute();
			}

			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

public SeriesSentiment getSeriesSentiment(String serie) {
		
		SeriesSentiment sentiment = new SeriesSentiment();
		
		int maxSent = 0;
		int countSent = 0;
		
		String sql = "SELECT QR1.SERIE, QR1.SENTIMENT, QR1.CONT_SENT, ROUND((CONT_SENT/CONT_TOTAL)*100,0) AS PORCENT " + 
				"FROM " + 
				"(SELECT SERIE, SENTIMENT, Count(SENTIMENT) AS CONT_SENT " + 
				"FROM tweets " + 
				"GROUP BY SERIE, SENTIMENT) AS QR1 " + 
				"INNER JOIN " + 
				"(SELECT SERIE, Count(ID) AS CONT_TOTAL " + 
				"FROM tweets " + 
				"GROUP BY SERIE) AS QR2 ON QR1.SERIE = QR2.SERIE " + 
				"WHERE QR1.SERIE =? "+
				"ORDER BY QR1.SENTIMENT;";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, serie);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				if(rs.getInt("CONT_SENT")>countSent) {
					countSent = rs.getInt("CONT_SENT");
					maxSent = rs.getInt("sentiment");
				}
				
				if(rs.getInt("sentiment")==0) {
					sentiment.setSentiment0(rs.getInt("CONT_SENT"));
					sentiment.setSentiment0_perc(rs.getInt("PORCENT"));
				}
				if(rs.getInt("sentiment")==1) {
					sentiment.setSentiment1(rs.getInt("CONT_SENT"));
					sentiment.setSentiment1_perc(rs.getInt("PORCENT"));
				}
				if(rs.getInt("sentiment")==2) {
					sentiment.setSentiment2(rs.getInt("CONT_SENT"));
					sentiment.setSentiment2_perc(rs.getInt("PORCENT"));
				}
				if(rs.getInt("sentiment")==3) {
					sentiment.setSentiment3(rs.getInt("CONT_SENT"));
					sentiment.setSentiment3_perc(rs.getInt("PORCENT"));
				}
				if(rs.getInt("sentiment")==4) {
					sentiment.setSentiment4(rs.getInt("CONT_SENT"));
					sentiment.setSentiment4_perc(rs.getInt("PORCENT"));
				}
			}
				
			if(maxSent == 0)
				sentiment.setMainSentiment("Muito negativo");
			if(maxSent == 1)
				sentiment.setMainSentiment("Negativo");
			if(maxSent == 2)
				sentiment.setMainSentiment("Neutro");
			if(maxSent == 3)
				sentiment.setMainSentiment("Positivo");
			if(maxSent == 4)
				sentiment.setMainSentiment("Muito positivo");		
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return sentiment;
	}
	
	public List<Tweet> getTweetsSerie(String serie) {

		String sql = "SELECT date, text, processed_text, sentiment FROM tweets WHERE serie=? order by date desc";
		List<Tweet> tweets = new ArrayList<Tweet>();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, serie);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Tweet tweet = new Tweet();
				tweet.setDate(rs.getString("date"));
				tweet.setText(rs.getString("text"));
				tweet.setProcessedText(rs.getString("processed_text"));
				tweet.setSentiment(rs.getInt("sentiment"));
				
				tweets.add(tweet);
			}
			stmt.close();
			return tweets;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
