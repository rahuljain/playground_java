/**
 * 3.  Assume you want to store a bunch of usernames in a data structure.
 * Assume you want to add many names incrementally to the data structure, and 
 * that you want the order of insertion to be preserved.  Please write proper 
 * Java code to allocate an appropriate data structure to meet this need.
 * Assume you are using JDK 1.5+
 */
public class UsernameClass {
	/*
	 *LinkedHashSet provides constant-time performance for the basic operations (add, contains and remove).
	 *LinkedHashSet maintains a doubly-linked list and preserves the order of insertion.
	 */
	private LinkedHashSet<String> usernames;
	
	public UsernameClass()
	{
		usernames = new LinkedHashSet<String>();
	}
	
	public void AddUserNames(String username)
	{
		usernames.add(username);
	}
}


/*4.  Please complete the code below.  The output of the method should be the
  String value out of the array passed in that contains the least number of
  numeric characters.  If two Strings have the same number of numeric
  characters, return the longest String.  If two Strings have the same number
  of numeric characters and are the same length, return the String that
  appears first in the input array.   If the array is empty, return null;
  */
public String getBestString(String[] values) {
	String best = null;
	//... insert your code here ...
	int min = 0;
	try {
		for(String s : values) {
			int numInts = countNumericCharacters(s);
			if(best == null) {
				best = s;
			}
			if( (numInts == min && s.length()>best.length()) || (numInts < min) ) {
				min = numInts;
				best = s;
			}
		}
	}
	catch(NullPointerException e) {
		System.err.println(e.toString());
	}
	return best;
}

private int countNumericCharacters(String s) {
	int num = 0;
	try {
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)>='0' && s.charAt(i)<='9') {
				num++;
			}
		}
	}
	catch(NullPointerException e) {
		System.err.println(e.toString());
	}
	return num;
}

