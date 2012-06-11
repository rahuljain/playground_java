package interviewQuestions;

import java.util.*;

public class HashTableAnagrams {
	
	public HashSet<String> findAllAnagrams(HashSet<String> dictionary)
	{
		HashSet<String> anagrams = new HashSet<String>();
		Hashtable<String, List<String>> anagramtable; 
		String sortedWord;
		for(String word : dictionary)
		{
			//sort the letters of the word
			sortedWord = sortWord(word);
			
		}
		return anagrams;
	}
	
	private String sortWord(String word)
	{
		String sortedWord = new String(word);
		Arrays.sort(sortedWord.toCharArray());
		return sortedWord;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
