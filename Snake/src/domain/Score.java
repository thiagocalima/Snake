package domain;

public class Score {
	private int score;
	private String player;

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}	
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}	
	
	public void incrementScore(int i) {
		this.score += i;
	}
}
