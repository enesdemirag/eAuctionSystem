package eAuctionSystem;

/**
* Buyer Class (extends User class)
* @author 	Enes Demirag & Damon Gilbert
* @version	1.0
* @see User
*/
public class Buyer extends User {
	
	/**
	* Buyer Constructor
	* @see	User
	*/
	public Buyer(String username, String password) throws Exception {
		super(username, password);
		
	}

	/**
	* Prints the detailed victory message
	* @param	auction		related Auction
	* @param	bid			best Bid
	*/
	public void victory(Auction auction, Bid bid) {
		System.out.println("\nThe winner of the Auction: \"" + auction.getItem().getName() + "\" is \"" + this.getUsername() + "\" with Bid: " + bid.getAmount() + " GBP");
	}
}
