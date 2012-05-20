/**
 * 
 */
package textscrapper;

/**
 * @author rahul
 *
 */
public class NexTagMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		NexTagTextScrapperInterface scrapper = new NexTagTextScrapper();
		//Integer n = scrapper.NumResults("jeans");
		//System.out.println("number of results = " + n);
		scrapper.GetResultInfo("jeans", 1);
	}

}
