package eAuctionSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
* Auction Class (implements Blockable interface)
* @author 	Enes Demirag & Damon Gilbert
* @version	1.0
* @see		Blockable
*/
public final class Auction implements Blockable {

	private Seller seller;
	private Buyer winner;
	private Item item;
	private double startPrice;
	private double reservePrice;
	private LocalDateTime closeDate;
	private Status status;
	private List<Bid> bidList = new ArrayList<Bid>();
	
	/**
	* Auction Constructor
	* @param	seller			owner of the auction 
	* @param	item			item to be sold
	* @param	startPrice		starting price
	* @param	reservePrice	minimum price for valid sale
	* @param	closeDate		closing date of the auction
	* @throws 					if close date is not valid
	* @see		Seller
	* @see		Item
	*/
	public Auction(Seller seller, Item item,  double startPrice, double reservePrice, LocalDateTime closeDate) throws Exception {
		this.seller = seller;
		this.item = item;
		this.startPrice = startPrice;
		this.reservePrice = reservePrice;
		if (this.isCloseDateValid(closeDate)) {
			this.closeDate = closeDate; // Check closing date is valid		
		}
		else {
			throw new Exception("Close Date should between now and 7 days after !");
		}
		this.status = Status.PENDING;
	}

	/**
	* Returns Auction object's attributes
	*/
	@Override
	public String toString() {
		return "Auction: " + this.item.getName() + ": " + this.item.getDescription() + "\nLocation: " + this.item.getLocation() + "\nCondition: " + this.item.getStatus()
				+ "\nStart Price: " + this.startPrice + "\nReserve Price: " + this.reservePrice + "\nClosing Date: " + this.closeDate.getHour() + ":" + this.closeDate.getMinute();
	}
	
	/**
	* @return	owner of the auction
	* @see		Seller
	*/
	public Seller getSeller() {
		return this.seller;
	}
	
	/**
	* @return	winner of the Auction if its closed, otherwise returns null
	* @see		Buyer
	*/
	public Buyer getWinner() {
		return this.winner;
	}
	
	/**
	* @return	item of the auction
	* @see		Item
	*/
	public Item getItem() {
		return this.item;
	}
	
	/**
	* @return	specified start price of the auction
	*/
	public double getStartPrice() {
		return this.startPrice;
	}

	/**
	* @return	reserved price of the auction
	*/
	public double getReservePrice() {
		return this.reservePrice;
	}

	/**
	* @return	closing date of the auction
	* @see		LocalDateTime
	*/
	public LocalDateTime getCloseDate() {
		return this.closeDate;
	}
	
	/**
	* @return	true if closing date is between now and 7days after, false otherwise
	* @see		LocalDateTime
	*/
	public boolean isCloseDateValid(LocalDateTime inputDate) {
		if (inputDate.isAfter(LocalDateTime.now()) && inputDate.isBefore(LocalDateTime.now().plusDays(7))) {
			return 	true;	
		}
		else {
			return false;
		}
	}

	/**
	* Bidding on to Auction
	* @param		amount	bid value
	* @param		buyer	bidder
	* @exception	if Auction is not ACTIVE (pending, blocked or closed)
	* @see			Buyer
	*/
	public void placeBid(double amount, Buyer buyer) throws Exception {
		if (getStatus().equals(Status.ACTIVE)) {
			/*
			if (amount <= getBestBid().getAmount() * 1.2 && amount >= getBestBid().getAmount() * 1.1) {
				bidList.add(new Bid(amount, buyer, LocalDateTime.now()));							
			}
			else {
				throw new Exception("Amount should between %10 - %20 bigger than last bid !");				
			}*/
			bidList.add(new Bid(amount, buyer, LocalDateTime.now()));
		}
		else {
			throw new Exception("This Auction is not ACTIVE!");
		}
	}
	
	/**
	* Returns all bids of Auction 
	* @return	list of Bid objects
	* @see		Bid
	*/
	public List<Bid> getBids() {
		return this.bidList;
	}
	
	/**
	* Verify the Auction, status of the Auction becomes ACTIVE
	* @see		Status
	*/
	public void verify() {
		this.status = Status.ACTIVE;
	}
	
	/**
	* Get the Best Bid
	* @return 	the bid with biggest value if reserved price met, otherwise returns null
	* @see		Bid
	*/
	public Bid getBestBid() {
		Bid winnerBid = Collections.max(getBids(), Comparator.comparing(o -> o.getAmount()));
		if (winnerBid.getAmount() >= getReservePrice()) {
			return winnerBid;
		}
		return null;
	}

	/**
	* Close the Auction
	* Status of the Auction becomes CLOSED
	* Winner will be updated if the is a valid bid
	* @see		Status
	* @see		Buyer
	*/
	public void close() {
		// get highest bidder
		this.status = Status.CLOSED;
		Bid winnerBid = getBestBid();
		if (winnerBid != null) {
			this.winner = winnerBid.getWho();
		}
	}
	
	/**
	* Returns the status of the Auction
	* @return	status of the auction
	* @see		Status
	*/
	public Status getStatus() {
		return this.status;
	}
	
	/**
	* Returns true if the Auction is blocked, otherwise returns false
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
	* Block the Auction
	*/
	@Override
	public void setBlocked() {
		this.status = Status.BLOCKED;
	}

	/**
	* Unlock the Auction
	*/
	@Override
	public void setUnBlocked() {
		this.status = Status.ACTIVE;
	}
	
	// TODO
	// changeReservePrize()
	// changeCloseDate()
}
