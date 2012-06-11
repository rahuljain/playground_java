package interviewQuestions;

import java.util.ArrayList;
/**
 * Given an array having positive integers, find a continuous subarray which adds to a given number.
 * @author Rahul
 *
 */
public class SubArrayAddsToNumber {
	
	public ArrayList<Integer> find_sub_range(int A[], int len, int sum)
	{
		ArrayList<Integer> indexPairs = new ArrayList<Integer>();
		int startIndex = 0;
		int currSum = 0;
		
		while (startIndex < len && A[startIndex] < sum)
			startIndex++;
		
		for(int endIndex=startIndex; endIndex < len; endIndex++)
		{
			if(currSum == sum)
			{
				indexPairs.add(startIndex+1);
				indexPairs.add(endIndex);
				return indexPairs;
			}
			
			if(currSum + A[endIndex] > sum)
			{
				currSum -= A[startIndex];
				startIndex++;
			}
			currSum += A[endIndex];
		}
		return null;
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int A[] = {30, 2, 3, 1, 5, 4};
		
		SubArrayAddsToNumber obj = new SubArrayAddsToNumber();
		 
		  ArrayList<Integer> indexPairs = obj.find_sub_range(A, 6, 1);
		 
		  if (indexPairs!=null){
		    System.out.println("Found!");
		    System.out.println(indexPairs.get(0));
		    System.out.println(indexPairs.get(1));
		  }
		  else 
			  System.out.println("Not Found");

	}

}
