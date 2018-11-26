package br.com.twitter.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.twitter.jdbc.connection.*;
import br.com.twitter.jdbc.model.*;
import org.jfree.data.category.DefaultCategoryDataset;

public class TweetDAO {

	// Conexão com o banco de dados
	private Connection connection;

	public TweetDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void insert(ArrayList<Tweet> tweets) {
		String sql = "INSERT INTO TWEETS " + "(id,date,time,text,processed_text,hashtag,sentiment,serie,language)"
				+ " VALUES (?,?,?,?,?,?,?,?,?)";

		try {
			// prepared statement para inserção
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
			// prepared statement para inserção
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

		String sql = "SELECT QR1.SERIE, QR1.SENTIMENT, QR1.CONT_SENT, ROUND((CONT_SENT/CONT_TOTAL)*100,0) AS PORCENT "
				+ "FROM " + "(SELECT SERIE, SENTIMENT, Count(SENTIMENT) AS CONT_SENT " + "FROM tweets "
				+ "GROUP BY SERIE, SENTIMENT) AS QR1 " + "INNER JOIN " + "(SELECT SERIE, Count(ID) AS CONT_TOTAL "
				+ "FROM tweets " + "GROUP BY SERIE) AS QR2 ON QR1.SERIE = QR2.SERIE " + "WHERE QR1.SERIE =? "
				+ "ORDER BY QR1.SENTIMENT;";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, serie);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				if (rs.getInt("CONT_SENT") > countSent) {
					countSent = rs.getInt("CONT_SENT");
					maxSent = rs.getInt("sentiment");
				}

				if (rs.getInt("sentiment") == 0) {
					sentiment.setSentiment0(rs.getInt("CONT_SENT"));
					sentiment.setSentiment0_perc(rs.getInt("PORCENT"));
				}
				if (rs.getInt("sentiment") == 1) {
					sentiment.setSentiment1(rs.getInt("CONT_SENT"));
					sentiment.setSentiment1_perc(rs.getInt("PORCENT"));
				}
				if (rs.getInt("sentiment") == 2) {
					sentiment.setSentiment2(rs.getInt("CONT_SENT"));
					sentiment.setSentiment2_perc(rs.getInt("PORCENT"));
				}
				if (rs.getInt("sentiment") == 3) {
					sentiment.setSentiment3(rs.getInt("CONT_SENT"));
					sentiment.setSentiment3_perc(rs.getInt("PORCENT"));
				}
				if (rs.getInt("sentiment") == 4) {
					sentiment.setSentiment4(rs.getInt("CONT_SENT"));
					sentiment.setSentiment4_perc(rs.getInt("PORCENT"));
				}
			}

			if (maxSent == 0)
				sentiment.setMainSentiment("Muito negativo");
			if (maxSent == 1)
				sentiment.setMainSentiment("Negativo");
			if (maxSent == 2)
				sentiment.setMainSentiment("Neutro");
			if (maxSent == 3)
				sentiment.setMainSentiment("Positivo");
			if (maxSent == 4)
				sentiment.setMainSentiment("Muito positivo");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return sentiment;
	}

	public List<Tweet> getTweetsSerie(String serie) {

		String sql = "Select T3.date, T3.text, T3.processed_text, T3.sentiment FROM ("
				+ "Select * from (SELECT date, text, processed_text, sentiment,serie FROM tweets WHERE DATE LIKE '%out%' order by date asc) as T1 "
				+ "union "
				+ "select * From (SELECT date, text, processed_text, sentiment,serie FROM tweets WHERE DATE LIKE '%nov%' order by date asc) as T2 "
				+ ") as T3 WHERE T3.serie=?;";
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

	public DefaultCategoryDataset getDataSerie (String serie) {
		String sql = "SELECT count(sentiment) as quantidade, sentiment, date FROM tweets WHERE serie=? group by date, sentiment order by date asc";
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, serie);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int quantidade = rs.getInt("quantidade");
				int sentiment = rs.getInt("sentiment");
				String date = rs.getString("date");
				
				dataset.addValue(quantidade, Integer.toString(sentiment), date);				
			}
			stmt.close();
			return dataset;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	public String getDataSerie(String serie, int sentiment) {
		String sql = "SELECT count(sentiment) as quantidade, sentiment, date FROM tweets WHERE serie=? and sentiment=? group by date, sentiment order by date asc";
		String result = "";		
		String dateFormatedOut = "";
		String dateFormatedNov = "";		

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, serie);
			stmt.setInt(2, sentiment);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String date = rs.getString("date");
				
				switch(date.split("-")[1]) {
					case "out": 
						dateFormatedOut = dateFormatedOut + "\n{ x: " + 
								"new Date(2018," + "09," + date.split("-")[0] + ")" + 
								", y: " + 
								rs.getInt("quantidade") +
								"},";
						break;
					case "nov": 
						dateFormatedNov = dateFormatedNov + "\n{ x :" + 
								"new Date(2018," + "10," + date.split("-")[0] + ")" + 
								", y: " + 
								rs.getInt("quantidade") +
								"},";
						break;
					default:
						break;
				}			
				
			}
			
			if (dateFormatedOut.length() != 0 && dateFormatedNov.length() == 0 )
				dateFormatedOut = dateFormatedOut.substring(0,dateFormatedOut.length()-1);			
			
			if (dateFormatedNov.length() != 0)
				dateFormatedNov = dateFormatedNov.substring(0,dateFormatedNov.length()-1);
			
			result = result + dateFormatedOut + dateFormatedNov;

			stmt.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}
}
