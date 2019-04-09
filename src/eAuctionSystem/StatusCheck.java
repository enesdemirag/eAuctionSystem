package eAuctionSystem;

import java.time.LocalDateTime;
import java.util.List;

/**
* StatusCheck Class
* @author 	Enes Demirag & Damon Gilbert
* @version	1.0
* @see Status
*/
public class StatusCheck implements java.lang.Runnable {
	private Sys system;
	private int delay;
	private List<Auction> auctions;
	
	/**
	* StatusCheck Constructor
	* @param	system		the eAuctionSystem (Sys) where all auctions in
	* @param	seconds		interval of the timer
	* @see		Sys
	*/
	public StatusCheck(Sys system, int seconds) {
		setDelay(seconds);
		setSystem(system);
	}
	
	/**
	* Checks all the auctions in the system periodically and closes them if the closing date come.
	* Calls the victory method of the winner
	* @see	Auction#close()
	* @see	Auction#getWinner()
	* @see	Auction#getBestBid()
	* @see	Buyer#victory(Auction, Bid)
	*/
	public void run() {
		while(true) {
			// Refresh and Close outdated auctions
			this.auctions = system.getAllAuctions();
			// If an auction is not blocked and if that auction's closing date is past, and if that auction is ACTIVE; close that auction and inform the winner.
			auctions.stream().filter(o -> !o.isBlocked()).filter(o -> o.getCloseDate().isBefore(LocalDateTime.now())).filter(o -> o.getStatus().equals(Status.ACTIVE)).forEach(o -> {
				o.close();
				o.getWinner().victory(o, o.getBestBid());
			});
			try {
				Thread.sleep(this.delay);
			}
			catch (InterruptedException e) {
				System.out.println("Exception in thread: " + e.getMessage());
			}
		}
	}
	
	/**
	* Sets the delay of the thread in seconds
	*/
	public void setDelay(int seconds) {
		this.delay = seconds * 1000;
	}
	
	/**
	* Returns the delay of the thread in seconds
	*/
	public int getDelay() {
		return this.delay / 1000;
	}
	
	/**
	* Sets the eAuctionSystem (Sys) where all auctions in
	* @see Sys
	*/
	public void setSystem(Sys system) {
		this.system = system;
	}
}
