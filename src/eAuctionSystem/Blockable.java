package eAuctionSystem;

/**
* Blockable Interface
* Used for controlling the Status of Auction and Seller objects
* @author 	Enes Demirag & Damon Gilbert
* @version	1.0
* @see Status
*/
public interface Blockable {
	boolean isBlocked();
	void setBlocked();
	void setUnBlocked();
}
