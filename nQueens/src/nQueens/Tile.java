package nQueens;

final class Tile {
	private boolean isQueen;
	
	public Tile() {
		isQueen = false;
	}
	
	protected boolean hasQueen() {
		return isQueen;
	}
	
	protected void setQueen(boolean set) {
		this.isQueen = set;
	}
}
