/**
 * 
 */
package interviewQuestions;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Rahul
 * 
 * Extract all combinations of a target substring that appear in a source string.
 *
 */
public class FindAllSubstrings {

	/**
	 * @param args
	 */
	
	public ArrayList<String> getAllSubstringCombinations(String s, String substring)	{
		ArrayList<String> occuringCombinations = new ArrayList<String>();
		HashSet<String> combinations = getAllCombinations(substring);
		for(String combination : combinations) {
			if(s.contains(combination)) {
				occuringCombinations.add(combination);
			}
		}
		return occuringCombinations;
	}
	
	private HashSet<String> getAllCombinations(String string)
	{
		HashSet<String> combinations = new HashSet<String>();
		getAllCombinations(string, "", combinations);
		return combinations;
	}
	
	private void getAllCombinations(String permuteString, String baseString, HashSet<String> combinations)
	{
		if(permuteString.length()==1){
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
	
	public static void main(String[] args) {
		FindAllSubstrings foo = new FindAllSubstrings();
		//HashSet<String> combinations = foo.getAllCombinations("abc");
		ArrayList<String> occuringCombinations = foo.getAllSubstringCombinations("abcaefgbcahijkacb12df", "abc");
		for(String s : occuringCombinations) {
			System.out.println(s);
		}

	}

}
