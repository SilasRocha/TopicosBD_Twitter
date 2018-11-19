package analyzer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	public int[] getSeriesSentiment(String serie) {
		String sql = "SELECT sentiment, COUNT(sentiment) AS quantity FROM TWEETS where serie=? group by sentiment";

		int[] sentiment = new int[5];

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, serie);
			ResultSet rs = stmt.executeQuery();
			int index = 0;

			while (rs.next() & index < sentiment.length) {				
				sentiment[rs.getInt("sentiment")] = rs.getInt("quantity");
				index++;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return sentiment;
	}

}
