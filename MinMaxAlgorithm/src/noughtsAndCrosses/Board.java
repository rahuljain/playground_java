package noughtsAndCrosses;

public class Board {
	private Tile board[][];
	
	public Board(State state) {
		this.setBoard(state);
	}
	
	public void setBoard(State state) {
		this.board = state.getState();
	}
	
	public Tile[][] getBoard() {
		return this.board;
	}
	
	public void displayBoard() {
		for(int i=0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(this.board[i][j].getPiece().piece()+" ");
			}
			System.out.println();
		}
	}
}
