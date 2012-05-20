package noughtsAndCrosses;

public class Player {
	private String player;
	
	public Player() {
		player = null;
	}
	
	public void setPlayer(String plyr) {
		if(!plyr.equals("min") || !plyr.equals("max")) {
			System.err.println("Invalid player");
		}
		else {
			player = plyr;
		}
	}
	
	public String getPlayer() {
		return player;
	}
}
