package eAuctionSystem;

/**
* Seller Class (extends User class) (implements Blockable interface)
* @author 	Enes Demirag & Damon Gilbert
* @version	1.0
* @see 		User
* @see 		Blockable
*/
public class Seller extends User implements Blockable {
	
	private Status status;
	
	/**
	* Seller Constructor
	* @see	User
	*/
	public Seller(String username, String password) throws Exception {
		super(username, password);
		this.status = Status.ACTIVE;
	}

	/**
	* Returns true if the Seller is blocked, otherwise returns false
	* @see		Status
	*/
	@Override
	public boolean isBlocked() {
		if (this.status.equals(Status.BLOCKED)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	* Block the Seller
	*/
	@Override
	public void setBlocked() {
		this.status = Status.BLOCKED;
	}

	/**
	* Unlock the Seller
	*/
	@Override
	public void setUnBlocked() {
		this.status = Status.ACTIVE;
	}
}
