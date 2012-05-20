package problem1;

public class missingMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MissingLetters obj = new MissingLetters();
		/*String[] moves = obj.animate(2, "..R....");
		for(int i = 0; i < moves.length; i++) {
			System.out.println(moves[i]);
		}*/
		String missing = obj.getMissingLetters("");
		System.out.println(missing);
	}

}
