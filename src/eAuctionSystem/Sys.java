package eAuctionSystem;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
* Sys Class: the eAuctionSystem Class
* @author 	Enes Demirag & Damon Gilbert
* @version	1.0
* @see 		Auction
* @see 		User
*/
public final class Sys {
	
	private List<Auction> auctions = Collections.synchronizedList(new LinkedList<Auction>());
	private List<User> users = new LinkedList<User>();
	
	/**
	* Create an Account: add new Seller or Buyer object to Users list
	* @see	User#User(String, String)
	*/
	public void setupAccount(String username, String password, String type) throws Exception {
		if (type.equals("Seller")) {
			users.add(new Seller(username, password));
		}
		else if (type.equals("Buyer")) {
			users.add(new Buyer(username, password));
		}
		else if (type.equals("Admin")) {
			users.add(new Admin(username, password));
		}
	}
	
	/**
	* Start an Auction: add new Auction object to Auctions list
	* @see	Auction#Auction(Seller, Item, double, double, LocalDateTime)
	* @throws	if close date is not valid 
	*/
	public void placeAuction(Seller seller, Item item, double startPrice, double reservePrice, LocalDateTime closeDate) throws Exception {
		auctions.add(new Auction(seller, item, startPrice, reservePrice, closeDate));
	}
	
	/**
	* @return	a list of ACTIVE auctions
	*/
	public List<Auction> browseAuctions() {
		return this.auctions.stream().filter(c -> c.getStatus().equals(Status.ACTIVE)).collect(Collectors.toList());
	}
	
	/**
	* @return	a list of all auctions
	*/
	public List<Auction> getAllAuctions() {
		return this.auctions;
	}
	
	/**
	* @return an auction with specified item name if there is one, otherwise returns null
	*/
	public Auction getAuctionByItemName(String itemName) {
		for (Auction auction : this.auctions) {
			if (auction.getItem().getName().equals(itemName)) return auction;
		}
		return null;
	}
	
	/**
	* @return  all users in the system
	*/
	public List<User> getAllUsers() {
		return this.users;
	}
	
	/**
	* @return 	all users in the system
	* @throws	Casting Exception
	* @see		#getSellerByName(String)
	*/
	public List<Seller> getAllSellers() throws Exception {
		List<Seller> sellers = null;
		for (User u : this.users) {
			if (getSellerByName(u.getUsername()) != null) {
				sellers.add(getSellerByName(u.getUsername()));
			}
		}
		return sellers;
	}
	
	/**
	* @return  all users in the system
	*/
	public void deleteUser(String username) {
		this.users.stream().filter(o -> o.getUsername().equals(username)).forEach(o -> {
			o = null;
		});

	}
	
	/**
	* @return a User with specified username if there is one, otherwise returns null
	*/
	public User getUserByName(String username) {
		for(User user : users) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}
	/**
	* @return a Seller with specified username if there is one, otherwise returns null
	* @throws	Casting Exception
	*/
	public Seller getSellerByName(String username) throws Exception {
		for(User user : users) {
			if(user.getUsername().equals(username)) {
				try {
					return (Seller) user;					
				}
				catch (Exception e) {
					throw new Exception("Casing Error !");
				}
			}
		}
		return null;
	}
	
	/**
	* @return a Buyer with specified username if there is one, otherwise returns null
	 * @throws Exception 
	*/
	public Buyer getBuyerByName(String username) throws Exception {
		for(User user : users) {
			if(user.getUsername().equals(username)) {
				try {
					return (Buyer) user;					
				}
				catch (Exception e) {
					throw new Exception("Casing Error !");
				}
			}
		}
		return null;
		
		// return (Buyer) users.stream().filter(o -> o.getUsername().equals(username)); // Check this later
	}
}
