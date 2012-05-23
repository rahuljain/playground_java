/**
 * 
 */
package interviewQuestions;

import java.util.HashSet;

/**
 * @author Rahul
 *
 */
public class GenerateAllStringCombinations {

	public HashSet<String> getAllCombinations(String string)
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
		GenerateAllStringCombinations foo = new GenerateAllStringCombinations();
		HashSet<String> combinations = foo.getAllCombinations("abc");
		for(String s : combinations) {
			System.out.println(s);
		}

	}

}
