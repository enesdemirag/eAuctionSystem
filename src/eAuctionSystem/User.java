package eAuctionSystem;

/**
* User Class
* @author 	Enes Demirag & Damon Gilbert
* @version	1.0
*/
public abstract class User {

	protected String username;
	protected String password;
	
	/**
	* User Constructor
	* @param		username
	* @param		password
	* @param		type		Seller or Buyer
	* @exception	if password is not valid
	*/
	protected User(String username, String password) throws Exception {
		this.username = username;
		if (isValid(password)) {
			this.password = password; // Set the password			
		}
		else {
			throw new Exception("Invalid password");  // Throw exception
		}
	}

	/**
	* @return username
	*/
	public String getUsername() {
		return this.username;
	}

	/**
	* @return * characters with length of the password 
	*/
	public String getPassword() {
		String hidden = "";
		for (int i = 0; i < this.password.length(); i++) {
			hidden += "*";
		}
		return hidden;
	}
	
	/**
	* @return true if entered password is correct
	*/
	public boolean checkPassword(String inputPassword) {
		return this.password.equals(inputPassword);
	}
	
	/**
	* @return if password is valid returns true, otherwise false 
	*/
	protected boolean isValid(String password) {
		// if password is valid, return true
		if (password.length() >= 4) return true; // Must be longer than 4 or equal to
		else return false;
	}
	
	// TODO
	// changePassword()
}
