package noughtsAndCrosses;

public class State {
	private Tile[][] state;
	public State()
	{
		state = new Tile[3][3];
		for(int i = 0; i< 9; i++) {
			state[i/3][i%3].setPiece(Piece.EMPTY);
		}
	}	
	public void setState(String s)
	{
		assert (s.length() == 9);
		for(int i = 0; i< 9; i++) {
			switch(s.charAt(i)) {
				case 'o': this.state[i/3][i%3].setPiece(Piece.NAUGHT);
				break;
				case 'x': this.state[i/3][i%3].setPiece(Piece.CROSS);
				break;
				case ' ': this.state[i/3][i%3].setPiece(Piece.EMPTY);
				break;
				default: System.out.println("Invalid state");
				break;
			}
			 
		}
	}
	public Tile[][] getState()
	{
		return state;
	}
	
	public boolean isWinningState()
	{
		for(int i = 0; i < 3; i++) {
			if(state[i][0].equals(state[i][1]) && state[i][1].equals(state[i][2]))
				return true;
			if(state[0][i].equals(state[1][i]) && state[1][i].equals(state[2][i]))
				return true;
		}
		if(state[0][0].equals(state[1][1]) && state[1][1].equals(state[2][2]))
			return true;
		if(state[0][2].equals(state[1][1]) && state[1][1].equals(state[2][0]))
			return true;
		
		return false;
	}
	
	
}
