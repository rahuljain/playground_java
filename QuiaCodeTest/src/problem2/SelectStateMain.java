package problem2;

public class SelectStateMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(SelectState.displayStateFullName("aZ"));
		System.out.println(SelectState.parseSelectedState("California"));
		System.out.println(SelectState.createStateSelectList("California"));
	}

}
