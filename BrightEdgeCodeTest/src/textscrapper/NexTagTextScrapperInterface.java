/**
 * 
 */
package textscrapper;

/**
 * @author rahul
 *
 */
public interface NexTagTextScrapperInterface {
	Integer NumResults(String keyword) throws Exception;
	Result GetResultInfo(String keyword, Integer pageNumber) throws Exception;
}
