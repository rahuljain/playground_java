/**
 * 
 */
package problem3;

import java.util.LinkedHashSet;

/**
 * @author Rahul
 *
 * Assume you want to store a bunch of usernames in a data structure.
* Assume you want to add many names incrementally to the data structure, and
* that you want the order of insertion to be preserved.  Please write proper
* Java code to allocate an appropriate data structure to meet this need.
* Assume you are using JDK 1.5+
 */
public class UsernameClass {
	
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
