/**
 * 
 */
package lcs;

import java.util.ArrayList;
import java.util.Arrays;

import lcs.LCS.Direction;

/**
 * @author Rahul
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Character> X = new ArrayList<Character>(Arrays.asList('A', 'B', 'C','B', 'D', 'A', 'B'));
		ArrayList<Character> Y = new ArrayList<Character>(Arrays.asList('B', 'D', 'C', 'A', 'B', 'A'));
		LCS lcs = new LCS(X, Y);
		lcs.GenerateLCSTable();
		Direction[][] tableB = lcs.getB();
		for(int i = 0; i <= X.size(); i++) {
			for(int j = 0; j <= Y.size(); j++) {
				try {
					if(tableB[i][j].equals(Direction.West)) {
						System.out.print(" - ");
					}
					else if(tableB[i][j].equals(Direction.NorthWest)) {
						System.out.print(" \\ ");
					}
					else if(tableB[i][j].equals(Direction.North)) {
						System.out.print(" | ");
					}
				} catch (NullPointerException e) {
					System.out.print(" 0 ");
				}				
			}
			System.out.println();
		}
		String LCS = lcs.PrintLCS(X.size(), Y.size());
		System.out.println(LCS);
	}
}
