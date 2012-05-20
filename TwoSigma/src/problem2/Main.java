package problem2;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Animation obj = new Animation();
		/*String[] moves = obj.animate(2, "..R....");
		for(int i = 0; i < moves.length; i++) {
			System.out.println(moves[i]);
		}*/
		String[] moves2 = obj.animate( 1,  "LRRL.LR.LRR.R.LRRL.");
		for(int i = 0; i < moves2.length; i++) {
			System.out.println(moves2[i]);
		}
	}
}
