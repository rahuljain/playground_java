package noughtsAndCrosses;

public enum Piece {
	CROSS("X"), NAUGHT("O"), EMPTY(" ");
	
	private String piece;
	Piece (String s) {
		this.piece = s;
	}
	public String piece() {
		return piece;
	}
}
