package setUp;

import java.util.HashMap;

public class AccountManager {

	public HashMap<String, user.User> accountMap;

	  
	/*
	 * Creates new hashmap to be used to store accounts to
	 * passwords
	 */
	public AccountManager(){
		accountMap = new HashMap<String,user.User>();  
	}
	/*
	 * Does an account exist already(username)
	 */
	public boolean accountExists(String username){
		return accountMap.containsKey(username);  
	}
	/*
	 * Checks if the password is correct
	 */

	/*
	 * Creates a new account by adding it to the map
	 */
	public void createAccount(String newUsername,user.User userObj){
		accountMap.put(newUsername, userObj);
	}
	
	public user.User getAccount(String username){
		if(accountExists(username)){
			return accountMap.get(username);
		}
		return null;
	}


}
