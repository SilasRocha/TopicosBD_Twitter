package br.com.twitter.jdbc.model;

public class SeriesSentiment {
	
	private String serie;
	private String pic;
	private String hashtag;
	private int sentiment0;
	private int sentiment1;
	private int sentiment2;
	private int sentiment3;
	private int sentiment4;
	private int sentiment0_perc;
	private int sentiment1_perc;
	private int sentiment2_perc;
	private int sentiment3_perc;
	private int sentiment4_perc;
	private String mainSentiment;
	
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public int getSentiment0() {
		return sentiment0;
	}
	public void setSentiment0(int sentiment0) {
		this.sentiment0 = sentiment0;
	}
	public int getSentiment1() {
		return sentiment1;
	}
	public void setSentiment1(int sentiment1) {
		this.sentiment1 = sentiment1;
	}
	public int getSentiment2() {
		return sentiment2;
	}
	public void setSentiment2(int sentiment2) {
		this.sentiment2 = sentiment2;
	}
	public int getSentiment3() {
		return sentiment3;
	}
	public void setSentiment3(int sentiment3) {
		this.sentiment3 = sentiment3;
	}
	public int getSentiment4() {
		return sentiment4;
	}
	public void setSentiment4(int sentiment4) {
		this.sentiment4 = sentiment4;
	}
	public int getSentiment0_perc() {
		return sentiment0_perc;
	}
	public void setSentiment0_perc(int sentiment0_perc) {
		this.sentiment0_perc = sentiment0_perc;
	}
	public int getSentiment1_perc() {
		return sentiment1_perc;
	}
	public void setSentiment1_perc(int sentiment1_perc) {
		this.sentiment1_perc = sentiment1_perc;
	}
	public int getSentiment2_perc() {
		return sentiment2_perc;
	}
	public void setSentiment2_perc(int sentiment2_perc) {
		this.sentiment2_perc = sentiment2_perc;
	}
	public int getSentiment3_perc() {
		return sentiment3_perc;
	}
	public void setSentiment3_perc(int sentiment3_perc) {
		this.sentiment3_perc = sentiment3_perc;
	}
	public int getSentiment4_perc() {
		return sentiment4_perc;
	}
	public void setSentiment4_perc(int sentiment4_perc) {
		this.sentiment4_perc = sentiment4_perc;
	}
	public String getMainSentiment() {
		return mainSentiment;
	}
	public void setMainSentiment(String mainSentiment) {
		this.mainSentiment = mainSentiment;
	}	
}