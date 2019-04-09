package eAuctionSystem;

/**
* Status Enum
* @author 	Enes Demirag & Damon Gilbert
* @version	1.0
* @see User
*/
public enum Status {
	
	ACTIVE, BLOCKED,		// for Auction and Seller
	PENDING, CLOSED,		// auction only
	NEW, REFURBISHED, USED;	// item only
}
