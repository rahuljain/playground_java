package problem1;

import java.util.HashSet;

/*
 * My solution makes use of HashSet in Java.
 * HashSet (more generally a Set) in Java is a collection that can not contain any duplicate elements.
 * Thus any time the size of the HashSet grows > 1, the function should return false.
 * 
 */
public class StringSet {	
	/*
	 * This method takes an array of sets of String objects, 
	 * and returns true if all sets in the array are equivalent 
	 * else returns false.
	 */
	public static boolean allStringSetsIdentical(String[][] sets) {
		HashSet<HashSet<String>> HSets = new HashSet<HashSet<String>>();
		try{
			//Create a hash set for each set and then add it to HSets.
			for(String[] set : sets) {
				HashSet<String> hSet = new HashSet<String>();
				for(String s : set) {
					hSet.add(s);
				}
				HSets.add(hSet);
				if(HSets.size() > 1) {	//more than one kind of set is found. Return false. No need to parse the remaining sets (if any).
					return false;
				}
			}
		}
		catch(NullPointerException e) {
			System.err.println("Can not pass null as an argument to allStringSetsIdentical method. NullPointerException. Program terminated.");
			System.exit(-1);
			
			/* For example this would be an exception.
			 * StringSet.allStringSetsIdentical(new String[][]{
				{"a","b"},
				{"b","b","a"},
				null,
				{"a", "b", "a", "a", "a", "a", "b"} }))
			 *
			 * But this won't (since here it's a null set):
			 * StringSet.allStringSetsIdentical(new String[][]{
				{"a","b"},
				{"b","b","a"},
				{null},
				{"a", "b", "a", "a", "a", "a", "b"}}))
			 */
		}
		return true; 
	}
}
