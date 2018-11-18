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
		String sql = "INSERT INTO TWEETS " + "(id,date,time,text,processed_text,hashtag,sentiment,serie,language)" + " VALUES (?,?,?,?,?,?,?,?,?)";

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
	
	public void insert(Tweet tweet) {
		String sql = "INSERT INTO TWEETS " + "(id,date,time,text,hashtag,serie,language)" + " VALUES (?,?,?,?,?,?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setLong(1, tweet.getId());
			stmt.setString(2, tweet.getDate());
			stmt.setString(3, tweet.getTime());
			stmt.setString(4, tweet.getText());
			stmt.setString(5, tweet.getHashtag());
			stmt.setString(6, tweet.getSerie());
			stmt.setString(7, tweet.getLanguage());

			// executa
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	// completar
	public ArrayList<Tweet> getDataset() {
		String sql = "SELECT * FROM TWEETS";
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Tweet tweet = new Tweet();
				tweet.setId(rs.getLong("id"));			

				tweets.add(tweet);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return tweets;
	}

	public void updateText(long id, String text) {
		String sql = "UPDATE TWEETS SET processed_text=? WHERE id=?";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setLong(1, id);
			stmt.setString(2, text);

			// executa
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void updateSentiment(long id, int sentiment) {
		String sql = "UPDATE TWEETS SET sentiment=? WHERE id=?";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setLong(1, id);
			stmt.setInt(2, sentiment);

			// executa
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
