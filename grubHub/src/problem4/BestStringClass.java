package problem4;

public class BestStringClass {
	/*4.  Please complete the code below.  The output of the method should be the
	String value out of the array passed in that contains the least number of
	numeric characters.  If two Strings have the same number of numeric
	characters, return the longest String.  If two Strings have the same number
	of numeric characters and are the same length, return the String that
	appears first in the input array.   If the array is empty, return null;
	 */
	public String getBestString(String[] values) {
		String best = null;
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BestStringClass bsc = new BestStringClass();
		String[] values = null;
		//String[] values = {null};
		//String[] values = {"Rahul1234", "Rahul1234Jai98n0", "Rahul1234Jain", "Jurrasic0Park1"};
		String best = bsc.getBestString(values);
		System.out.println(best);
	}
}
