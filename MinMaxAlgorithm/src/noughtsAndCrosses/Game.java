package noughtsAndCrosses;

public class Game {
	private Player currentPlayer;
	private State currentState;
	private Board board;
	
	public Game() {
		currentPlayer = new Player();
		currentPlayer.setPlayer("max");	//max = cross, min = naught
		currentState = new State();
		board = new Board(currentState);
	}
	
	public void changePlayer() {
		if(currentPlayer.getPlayer().equals("max")) {
			currentPlayer.setPlayer("min");
		} else currentPlayer.setPlayer("max");
	}
	public void makeMove(String move)
	{
		currentState.setState(move);
		board.setBoard(currentState);
		board.displayBoard();                                                                                                                                                                                                                                                           
		changePlayer();
	}
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(String player) {
		this.currentPlayer.setPlayer(player);
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
}
