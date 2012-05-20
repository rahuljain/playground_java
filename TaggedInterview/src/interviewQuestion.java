import java.util.Collections;

/**
 * 
 */

/**
 * @author Rahul Jain
 * Computer Engineering, UIUC.
 * Graduating: May 2010
 * jain10 AT illinois.edu
 *
 */
public class interviewQuestion {
	
	public boolean containsSum(List<int> numbers, int sum)
	{
		for(int a : numbers) {
			int conjugate = sum - a;
			if(numbers.contains(conjugate)) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
