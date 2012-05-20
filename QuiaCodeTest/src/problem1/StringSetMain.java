package problem1;

public class StringSetMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(StringSet.allStringSetsIdentical(new String[][]{
				{null},
				{null},
		})) {
			System.out.println("true");
		}
		else {
			System.out.println("false");
		}
	}

}
