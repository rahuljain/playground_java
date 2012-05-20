/**
 * 
 */
package subsetSum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author Rahul Jain
 * email: jain10 AT illinois DOT edu
 *
 */
public class SubsetSumProblem {
	private HashMap<ArrayList<Integer>, Integer> PossibleSumsMap;
	private ArrayList<ArrayList<Integer>> solution;
	private Integer numSolutions;
	private ArrayList<Integer> NumList;
	private Integer sum;
	
	/**
	 * Nested Solution class defined because we need to return two values: 
	 * the solution combinations and 
	 * the number of solutions found.
	 * 
	 * @author Rahul
	 *
	 */
	public class Solution {
		private ArrayList<ArrayList<Integer>> combinations;
		private Integer numSol;	
		public Solution(ArrayList<ArrayList<Integer>> sol, Integer numSol)
		{
			this.numSol = numSol;
			this.combinations = sol;
		}
		public ArrayList<ArrayList<Integer>> getCombinations() {
			return this.combinations;
		}
		public Integer getNumSol() {
			return this.numSol;
		}	
	}

	/*
	 * Constructor
	 */
	public SubsetSumProblem()
	{
		this.PossibleSumsMap = new HashMap<ArrayList<Integer>, Integer>();
		this.solution = new ArrayList<ArrayList<Integer>>();
		this.NumList = new ArrayList<Integer>();
		this.sum = 0;
		this.numSolutions = 0;
	}
	
	/*
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void findSolutions(Integer num)
	{
		//Get all the keys in the map.
		ArrayList<Integer>[] NumKeySet = (ArrayList<Integer>[])PossibleSumsMap.keySet().toArray(new ArrayList[PossibleSumsMap.size()]);
		int numElements = PossibleSumsMap.size();
		for(int i = 0; i < numElements; i++)
		{
			ArrayList<Integer> key = new ArrayList<Integer>(NumKeySet[i]);
			Integer value = PossibleSumsMap.get(key)+num;	//value for the new key = value of old key + num
			key.add(num);	//new key = append "num" to key.
			PossibleSumsMap.put(key, value);
			if(value.equals(sum))
			{
				solution.add(key);
				numSolutions++;
			}
		}
		//Add "num" itself to the map. Map it to its own value.
		ArrayList<Integer> NumKey = new ArrayList<Integer>();
		NumKey.add(num);
		PossibleSumsMap.put(NumKey, num);
		if(num.equals(sum))
		{
			solution.add(NumKey);
			numSolutions++;
		}
	}
	
	private void resetValues()
	{
		this.NumList.clear();
		this.PossibleSumsMap.clear();
		this.solution.clear();
		this.numSolutions = 0;
	}
	
	/*
	 * Subset Sum Solver
	 * Takes 4 values and a Sum as input.
	 * Finds if any combination of the values equals the Sum.
	 */
	public SubsetSumProblem.Solution SubsetSumSolver (Integer a, Integer b, Integer c, Integer d, Integer sum)
	{
		resetValues();
		this.sum = sum;
		this.NumList.add(a);
		this.NumList.add(b);
		this.NumList.add(c);
		this.NumList.add(d);
		Collections.sort(NumList);
		for(int i = 0; i < NumList.size(); i++)
		{
			findSolutions(NumList.get(i));			
		}
		SubsetSumProblem.Solution s = this.new Solution(solution, numSolutions);
		return s;
	}
}
