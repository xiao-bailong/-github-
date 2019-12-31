package entity;

public class Song {
	private String songname;
	private int positive_num;
	private int negative_num;
	public String getSongName() {
		return songname;
	}
	public void setSongName(String songName) {
		songname = songName;
	}
	public int getPositive_num() {
		return positive_num;
	}
	public void setPositive_num(int positive_num) {
		this.positive_num = positive_num;
	}
	public int getNegative_num() {
		return negative_num;
	}
	public void setNegative_num(int negative_num) {
		this.negative_num = negative_num;
	}
}
