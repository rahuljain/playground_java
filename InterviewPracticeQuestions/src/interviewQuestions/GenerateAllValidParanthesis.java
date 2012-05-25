/**
 * 
 */
package interviewQuestions;

import java.util.HashSet;

/**
 * @author Rahul
 *
 */
public class GenerateAllValidParanthesis {

	public HashSet<String> getAllCombinations(String string)
	{
		HashSet<String> combinations = new HashSet<String>();
		getAllCombinations(string, "", combinations);
		return combinations;
	}
	
	private void getAllCombinations(String permuteString, String baseString, HashSet<String> combinations)
	{
		if(permuteString.length()==1 && isValidCombination(baseString+permuteString)){
			combinations.add(baseString+permuteString);
		}
		else {
			for(int i=0; i<permuteString.length(); i++) {
				String newPermuteString = permuteString.substring(0, i) + permuteString.substring(i+1, permuteString.length());
				String newBaseString = baseString + permuteString.charAt(i);
				getAllCombinations(newPermuteString, newBaseString, combinations);
			}
		}
	}
	
	private boolean isValidCombination(String s)
	{
		if(!s.startsWith("("))
			return false;
		
		int count=0;
		for(int i = 0; i < s.length(); i++)
		{
			if(count<0) {
				return false;
			}
			if(s.charAt(i)=='(') {
				count++;
			}
			else if(s.charAt(i) == ')') {
				count--;
			}
		}
		if(count==0)
			return true;
		
		return false;
	}
	
	public static void main(String[] args) {
		GenerateAllValidParanthesis foo = new GenerateAllValidParanthesis();
		HashSet<String> combinations = foo.getAllCombinations(")))(((");
		for(String s : combinations) {
			System.out.println(s);
		}

	}

}
