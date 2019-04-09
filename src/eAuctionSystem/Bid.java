package eAuctionSystem;

import java.time.LocalDateTime;

/**
* Bid Class
* @author 	Enes Demirag & Damon Gilbert
* @version	1.0
*/
public final class Bid {
	
	private double amount;
	private Buyer who;
	private LocalDateTime when;
	
	/**
	* Bid Constructor
	* @param	amount	value of the bid
	* @param	who		owner of the bid (bidder)
	* @param	when	bidding date
	*/
	public Bid(double amount, Buyer who, LocalDateTime when) {
		this.amount = amount;
		this.who = who;
		this.when = when;
	}
	
	/**
	* Returns the Bid information
	*/
	@Override
	public String toString() {
		return "Bid: Buyer: " + this.who.getUsername() + ", Amount: " + this.amount + ", Time: " +  this.when.getHour() + ":" + this.when.getMinute();
	}

	/**
	* Returns the amount of the Bid
	* @return	amount of the Bid
	*/
	public double getAmount() {
		return amount;
	}

	/**
	* Returns the Bidder
	* @return	who		the Buyer object (who bid on)
	*/
	public Buyer getWho() {
		return who;
	}
	
	/**
	* Returns the time when the Bid created
	* @return	the time when the Bid created
	* @see LocalDateTime
	*/
	public LocalDateTime getWhen() {
		return when;
	}
	
}
