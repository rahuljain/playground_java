package nQueens;

import java.util.ArrayList;
import java.util.List;

public class nQueens implements nQueensInterface {

	private int n;
	//private Tile[][] Board;
	private List<Tile[][]> solutions;
	
	
	public nQueens() {
		this.solutions = new ArrayList<Tile[][]>();
	}
	
	@Override
	public List<Tile[][]> getSolution(int n) {
		this.n = n;
		//Board = new Tile[n][n];
		
		return solutions;
	}
	
	private List<Tile[][]> generateChildren(Tile[][] board, int level) {
		for(int i = 0; i < level; i++) {
			for(int j = 0; j < n; j++) {
				if(board[i][j].hasQueen()) {
					board = setNoQueens(board, i, j);
				}
			}
		}
		return null;
	}
	
	private Tile[][] setNoQueens(Tile[][] board, int i, int j) {
		for(int p=0; p < n; p++) {
			if(p != j) {
				board[i][p].setQueen(false);
			}
			if(p != i) {
				board[p][j].setQueen(false);
			}
			//do diagonals
		}
		return board;
	}
}
