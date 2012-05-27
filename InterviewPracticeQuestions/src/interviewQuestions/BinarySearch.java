/**
 * 
 */
package interviewQuestions;

/**
 * @author Rahul
 *
 */
public class BinarySearch {

	public Integer recursiveBinarySearch(Integer[] A, Integer searchValue)
	{
		return recursiveBinarySearch(A, 0, A.length-1, searchValue);
	}
	
	private Integer recursiveBinarySearch(Integer[] A, Integer startIndex, Integer endIndex, Integer searchValue)
	{
		if(startIndex>=endIndex && A[startIndex]!=searchValue)
		{
			return -1;
		}
		
		Integer midIndex = startIndex + (endIndex-startIndex)/2;
		if(A[midIndex] == searchValue)
		{
			return midIndex;
		}
		else 
		{
			if(A[midIndex] < searchValue)
				return recursiveBinarySearch(A, midIndex+1, endIndex, searchValue);
			else
				return recursiveBinarySearch(A, startIndex, midIndex, searchValue);
		}
	}
	
	public Integer iterativeBinarySearch(Integer[] A, Integer searchValue)
	{
		Integer startIndex = 0;
		Integer endIndex = A.length-1;
		Integer midIndex;
		while(startIndex <= endIndex)
		{
			if(startIndex == endIndex && A[startIndex]!=searchValue)
			{
				return -1;
			}
			midIndex = startIndex + (endIndex - startIndex)/2;
			if(A[midIndex]==searchValue) 
			{
				return midIndex;
			}
			else if(searchValue > A[midIndex])
			{
				startIndex = midIndex+1;
			}
			else
			{
				endIndex = midIndex;
			}
		}
		return -1;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] A = {1, 3, 5, 7, 9, 13, 15};
		BinarySearch bs = new BinarySearch();
		System.out.println(bs.iterativeBinarySearch(A, 3));
		System.out.println(bs.recursiveBinarySearch(A, 13));
	}

}
