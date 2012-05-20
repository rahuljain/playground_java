/**
 * 
 */
package subsetSum;

import java.util.ArrayList;

/**
 * @author Rahul Jain
 * email: jain10 AT illinois DOT edu
 *
 */
public class Main {	
	
	/*
	 * Prints the solution
	 */
	public static void printSolution(ArrayList<ArrayList<Integer>> combinations, Integer numSolutions)
	{
		System.out.println("The number of combinations that sum up to the required sum are " + numSolutions);
		System.out.println("The combinations are:");
		for(ArrayList<Integer> s : combinations)
		{
			System.out.println(s.toString());
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> combinations;
		Integer numSolutions;
		SubsetSumProblem obj = new SubsetSumProblem();
		SubsetSumProblem.Solution s1 = obj.SubsetSumSolver(4, 5, 6, 10, 15);
		numSolutions = s1.getNumSol();
		combinations = s1.getCombinations();
		printSolution(combinations, numSolutions);
		
		SubsetSumProblem.Solution s2 = obj.SubsetSumSolver(4, 3, -7, -3, 0);
		numSolutions = s2.getNumSol();
		combinations = s2.getCombinations();
		printSolution(combinations, numSolutions);
	}
}
