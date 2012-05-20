/*
 * Rahul Jain. Quia Code Test. Problem 2.
 */

package problem2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * Additional Improvements:
 * (1) Fixed the problem of dual maintenance at a few places:
 * 		(a) Earlier, two maps were to be maintained manually - one mapping the state name to its abbreviation
 * 			and another mapping the abbreviation to its respective state name.
 * 			Now, only one map is to be create manually, the other map uses the values and keys of this first map
 * 			to create the reverse mapping. This way we need to maintain only one map and the other will be updated 
 * 			on its own.
 * 		(b) Earlier, the two methods parseSelectedState and displayStateFullName did the same checking in each of
 * 			their methods. Now I have refactored to do the checking in a helper method.
 * 
 * (2) Since the createStateSelectList method will be called many times, I have created two static strings of states.
 * 	   If no state was previously selected StateSelectList is returned. Running time: O(1).
 * 	   If a state was previously selected then StateSelectedList is returned. StateSelectedList is only updated
 * 	   when there's a change in the selected state.
 * 	   Running time: when there's no update: O(1).
 * 					 when the selected state changes: O(n) where n is the number of states in the hash map.
 *		I had to create two State Lists because if createStateSelectList() and createStateSelectList(String s) methods
 *		are called a number of times alternately then it would take O(n) each time to update the list.
 *		Whereas by having two lists it will always take O(1) time for the first case and O(1) on average in the second case if
 *		the state is not updated too often.
 *		It is also a lot easier to maintain the code if the two strings are maintained separately.      
 *  
 * (3) As mentioned in the feedback e-mail, the createStateSelectList method may need to take an argument indicating which state 
 * 	   is currently selected (if any).
 * 	   This functionality can be achieved using function overloading - one method with no arguments and another with a string argument.
 */

/*
 * This code is better than the provided sample code because:
 * (1) In my solution use a hash map to map the names of state to their respective abbreviation/code.
 * This helped me to eliminate the need of duplicating code in each of the methods.
 * Duplication of code leads to higher maintenance and makes the code harder to read.
 * 
 *  (2) Also, by using a hash map the run time of my program significantly reduces.
 *  The average time to look up a value for a specified key in a hash map is O(1) ie, constant time.
 *  Whereas before (in the provided sample code) it was parsing through all the values using if-else statements 
 *  which would have a linear run-time O(n) where n is the number of states. 
 *  
 *  (3) My solution also makes use of encapsulation. The person using my API can't play with the state and state-code mappings.
 *  The state and state-code hash maps are declared as private static final thereby preventing any accidental/intentional 
 *  change to the maps by the user using the API.
 *  
 *  (4) My solution is also better because I handle exceptions using try-catch statements and gracefully quit the program - 
 *  after providing useful information to the user as to what happened and why the program didn't function as expected.
 *  
 *  Assumptions: We have enough space to store two hash maps with 50 values each (ie, total of 100 values).
 *  The higher priority is the speed. With hash map we can reduce the look up time to constant time - which is as good as it gets. 
 *  I think that's a reasonable assumption as modern day devices (in banks etc - where this program might be used) 
 *  have enough memory and the higher priority is given to the speed of the transaction as the banks don't 
 *  want their customers waiting for too long.
 *  I could have got away with one map but then for one of the methods (parseSelectedState or displayStateFullName) 
 *  the run-time would have become linear O(n) as I would have to parse the values of the map.
 *  
 */

public class SelectState {
		
	private static final HashMap<String, String> StateMap = new HashMap<String, String>();
	private static final HashMap<String, String> AbbrMap = new HashMap<String, String>();	
	private static String StateSelectList = new String("<select name=\"state\">\n");
	private static String StateSelectedList = new String("<select name=\"state\">\n");
	private static String[] states;
	private static String SelectedState = null;
	private static enum SelectOption { GET_ABBR, GET_STATE }
	
	static
	{
		AbbrMap.put("al", "Alabama");
		AbbrMap.put("ak", "Alaska");
		AbbrMap.put("az", "Arizona");
		AbbrMap.put("ar", "Arkansas");
		AbbrMap.put("ca", "California");
		//add more codes.
		
		for(Map.Entry<String, String> e : AbbrMap.entrySet()) {
			StateMap.put(e.getValue().toLowerCase(), e.getKey().toUpperCase());
		}
		
		states = AbbrMap.values().toArray(new String[AbbrMap.size()]);
		Arrays.sort(states);
		for(String state : states) {
			StateSelectList = StateSelectList.concat("<option value=\""+state+"\">"+state+"</option>\n");
		}
		StateSelectList = StateSelectList.concat("</select>\n");
	}
	
	/*
	 Generates an HTML select list that can be used to select a specific
	 U.S. state.
	*/
	public static String createStateSelectList()
	{
		return StateSelectList;
	}
	
	/*
	 * Overloads createStateSelectList method.
	 * 
	 * This method is called when a state has already been selected by the user.
	 * This method allows the state list to be generated with an already-selected state. 
	 * The selected state can either be left selected or the user may use the list to make a new selection.
	 * 
	 * The selected state list is updated only when the state which is passed in to this method changes from 
	 * what was previously selected. 
	 */
	public static String createStateSelectList(String state) {
		if(state == null || !StateMap.containsKey(state.toLowerCase())) {
			return "Invalid state selected.";
		}
		if(SelectedState == null || !SelectedState.equals(state.toLowerCase())) {
			SelectedState = state.toLowerCase();
			for(String s : states) {
				if(SelectedState.equals(s.toLowerCase())) {
					StateSelectedList = StateSelectedList.concat("<option value=\""+s+"\" selected>"+s+"</option>\n");
				}
				else {
					StateSelectedList = StateSelectedList.concat("<option value=\""+s+"\">"+s+"</option>\n");
				}
			}
			StateSelectedList = StateSelectedList.concat("</select>\n");
		}
		return StateSelectedList;
	}
	
	private static String helper(String s, SelectOption parseSelectState)
	{
		String output = null;
		try {
			s = s.toLowerCase();
			if(parseSelectState == SelectOption.GET_ABBR) {
				output = StateMap.get(s);
			}
			else if(parseSelectState == SelectOption.GET_STATE) {
				output = AbbrMap.get(s);
			}
		}
		catch (NullPointerException e) {
			System.err.println("Can not pass null as an argument. NullPointerException");
		}
		if(output == null) {
			return "Invalid state";
		}
		return output;
	}

	/*
	 Parses the state from an HTML form submission, converting it to
	 the two-letter abbreviation. We need to store the two-letter abbreviation
	 in our database.
	*/
	public static String parseSelectedState(String state)
	{
		return helper(state, SelectOption.GET_ABBR);
	}

	/*
	 Displays the full name of the state specified by the two-letter code.
	*/
	public static String displayStateFullName(String abbr)
	{
		return helper(abbr, SelectOption.GET_STATE);
	}
}
