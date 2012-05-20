package noughtsAndCrosses;

public class Tile {
	private Piece piece;
	
	public Tile() {
		this.piece = Piece.EMPTY;
	}
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public Piece getPiece() {
		return piece;
	}
	
}
