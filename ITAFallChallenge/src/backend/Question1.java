/**
 * 
 */
package backend;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Rahul
 *
 */
public class Question1 {
	public String getBestMatch(String searchTerm, String[] values) {
		Set<Character> hset = new HashSet<Character>();
		Integer maxCount = 0;
		String bestMatch = null;
		int searchTermLen = searchTerm.length();
		for(int i=0; i<searchTermLen; i++) {
			hset.add(searchTerm.charAt(i));
		}
		for(String s : values) {
			int localCount = 0;
			int len = s.length();
			for(int i=0; i<len; i++) {
				if(hset.contains(s.charAt(i))) {
					localCount++;
				}
			}
			if(localCount > maxCount) {
				maxCount = localCount;
				bestMatch = s;
			}
		}
		return bestMatch;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
