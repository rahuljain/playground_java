/**
 * 
 */
package lcs;

import java.util.ArrayList;

/**
 * @author Rahul Jain
 * jain10 AT illinois DOT edu
 * 
 * 
The longest-common-subsequence problem: 
A subsequence of a given sequence is just the given sequence with zero or
more elements left out. Formally, given a sequence X = <x1, x2, . . . , xm>, another
sequence Z = <z1, z2, . . . , zk> is a subsequence of X if there exists a strictly
increasing sequence <i1, i2, . . . ,ik> (but not necessarily consecutive) of indices of X 
such that for all j = 1, 2, . . . , k, we have x_ij = z_j . 
For example, Z = <B, C, D, B> is a subsequence of X = <A, B,C, B, D, A, B> 
with corresponding index sequence <2, 3, 5, 7>.
 */
public class LCS {
	private ArrayList<Character> X;
	private ArrayList<Character> Y;	
	private Integer[][] c;
	private Direction[][] b;
	
	public enum Direction { North, West, NorthWest }
	
	public LCS(ArrayList<Character> X, ArrayList<Character> Y)
	{
		this.X = X;
		this.Y = Y;
		Integer lengthX = X.size();
		Integer lengthY = Y.size();
		this.c = new Integer[lengthX+1][lengthY+1];
		this.b = new Direction[lengthX+1][lengthY+1];
	}
	
	public void GenerateLCSTable()
	{
		Integer lengthX = this.X.size();
		Integer lengthY = this.Y.size();		
		for(int i = 1; i <= lengthX; i++) {
			c[i][0] = 0;
		}
		for(int j = 0; j <= lengthY; j++) {
			c[0][j] = 0;
		}
		for(int i = 1; i <= lengthX; i++) {
			for(int j = 1; j <= lengthY; j++) {
				if(X.get(i-1).equals(Y.get(j-1))) {
					c[i][j] = c[i-1][j-1] + 1;
					b[i][j] = Direction.NorthWest;
				} 
				else if (c[i-1][j] >= c[i][j-1]) {
					c[i][j] = c[i-1][j];
					b[i][j] = Direction.North;
				}
				else {
					c[i][j] = c[i][j-1];
					b[i][j] = Direction.West;
				}
			}
		}
	}
	
	public String PrintLCS(int i, int j)
	{
		String LCS = new String();
		if(i==0 || j==0) {
			return LCS;
		}
		if(this.b[i][j].equals(Direction.NorthWest)) {
			PrintLCS(i-1, j-1);
			LCS.concat(X.get(i-1).toString());
//			System.out.print(this.X.get(i-1));
		}
		else if(this.b[i][j].equals(Direction.North)) {
			PrintLCS(i-1, j);
		}
		else if(this.b[i][j].equals(Direction.West)) {
			PrintLCS(i, j-1);
		}
		return LCS;
	}

	public ArrayList<Character> getX() {
		return this.X;
	}

	public void setX(ArrayList<Character> x) {
		this.X = x;
	}

	public ArrayList<Character> getY() {
		return this.Y;
	}

	public void setY(ArrayList<Character> y) {
		this.Y = y;
	}

	public Integer[][] getC() {
		return this.c;
	}

	public Direction[][] getB() {
		return this.b;
	}
}
