package Console;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner; 

import eAuctionSystem.*; // Import all classes

public class Console {

	// Definitions
	Scanner input = new Scanner(System.in); // Scanner object for getting input from user
	Sys sys = new Sys(); // eAuctionSystem
	Menu menu = new Menu(); // Menus
	byte choice; // Menu choices
	boolean initial = true; // For creating sample account, auctions ect.
	String lastMenu = "default"; // For keep tracking the menus
	User currentUser = null; // For sign-in
	
	Thread t = new Thread(new StatusCheck(sys, 10)); // backgroud status checker
    
	public void start() throws Exception {
		
		if (initial) { // For the fist time
			
			t.start(); // Start status checker thread
			
			// Create Sample Users
			sys.setupAccount("enes", "123456", "Admin");
			sys.setupAccount("damon", "123456", "Admin");
			
			sys.setupAccount("jessica", "123456", "Seller");
			sys.setupAccount("rex", "123456", "Seller");
			
			sys.setupAccount("joseph", "123456", "Buyer");
			sys.setupAccount("emily", "123456", "Buyer");
			
			// Create Sample Auctions
			sys.placeAuction(sys.getSellerByName("jessica"),
							new Item("Lord of the Rings", "Fiction Book", "Liverpool", Status.USED),
							10.0,
							25.0,
							LocalDateTime.now().plusMinutes(1));
			sys.getAllAuctions().get(0).verify(); // Verify

			sys.placeAuction(sys.getSellerByName("rex"),
					new Item("Macbook Pro", "Macbook Pro 13 inch", "London", Status.NEW),
					1000.0,
					1525.0,
					LocalDateTime.now().plusMinutes(1));
			sys.getAllAuctions().get(1).verify(); // Verify

			// Create Sample Bids
			sys.getAuctionByItemName("Lord of the Rings").placeBid(20.0, sys.getBuyerByName("joseph"));
			sys.getAuctionByItemName("Lord of the Rings").placeBid(22.0, sys.getBuyerByName("emily"));
			sys.getAuctionByItemName("Lord of the Rings").placeBid(26.0, sys.getBuyerByName("joseph"));
			
			sys.getAuctionByItemName("Macbook Pro").placeBid(1600.0, sys.getBuyerByName("emily"));
			
			initial = false;
		}

		// Starting Menu Loop
		if (lastMenu == "default") { // DEFAULT MENU
			System.out.println();
			choice = menu.defaultMenu(); // Get a choice from user
			switch (choice) {
				case 1: { // Create Account
					
					// Get username
					System.out.printf("Enter username: ");
					String username = input.nextLine();
					
					// Get password
					System.out.printf("Enter password: ");
					String password = input.nextLine();
					
					// Get type
					System.out.printf("[S]eller or [B]uyer: ");
					String type = input.nextLine();
					
					if (type.toUpperCase().charAt(0) == 'S') {
						type = "Seller";
					}
					else if (type.toUpperCase().charAt(0) == 'B') {
						type = "Buyer";
					}
					else {
						System.out.println("\nInvalid type !\n");
						break;
					}
					
					sys.setupAccount(username, password, type); // If everything is ok, create account
					System.out.println("\nAccount created successfully\n"); // Inform
					break;
				}
				case 2: { // Log on
					
					// Get username
					System.out.printf("Enter username: ");
					String username = input.nextLine();
					
					 if (sys.getUserByName(username) == null) { // Check if user exists
						 System.out.println("\nUser cannot found !\n");
						 break;
					 }
					 
					// Get password
					System.out.printf("Enter password: ");
					String password = input.nextLine();
					
					if (sys.getUserByName(username).checkPassword(password)) { // Check if password is correct
						currentUser = sys.getUserByName(username);
						if (currentUser instanceof Seller) { // Is it Seller ?
							lastMenu = "seller"; // Change Menu 
							break;
						}
						else if (currentUser instanceof Buyer) { // Is it Buyer ?
							lastMenu = "buyer"; // Change Menu 
							break;
						}
						else { // Is it Admin ?
							lastMenu = "admin"; // Change Menu
							break;
						}
					}
					else {
						System.out.println("\nPassword is incorrect !\n"); // Password is wrong
						break;
					}
				}
				case 3: { // Browse Auctions
					
					System.out.println("--- ACTIVE AUCTIONS ---");
					
					for (byte i = 0; i < sys.browseAuctions().size(); i++) {
						System.out.printf("%d: ", i + 1);
						System.out.println(sys.browseAuctions().get(i).toString());
						System.out.println();
					}
				}
				break;
			}
		}
		
		else if (lastMenu == "seller") { // SELLER MENU
			System.out.println();
			choice = menu.sellerMenu(); // Get a choice from user
			switch (choice) {
				case 1: { // Print auctions of this seller
					List<Auction> allAuctions = sys.getAllAuctions();
					for (Auction auc : allAuctions) {
						if (auc.getSeller().getUsername().equals(currentUser.getUsername())) {
							System.out.println(auc.toString());
						}
					}
					break;
				}
				case 2: {
					System.out.printf("Enter item name: ");
					String itemName = input.nextLine();
					if (sys.getAuctionByItemName(itemName) != null) {
						// Print the auction
						System.out.println(sys.getAuctionByItemName(itemName));						
					}
					else {
						System.out.println("Auction cannot found !\n");
					}
					break;
				}
				case 3: {
					// Get all necessary information one by one (check every one of them and throw exception if there is an error on the way)
					System.out.printf("Enter item name: ");
					String itemName = input.nextLine();
					
					System.out.printf("Enter item description: ");
					String itemDescription = input.nextLine();
					
					System.out.printf("Enter item location (city): ");
					String itemLocation = input.nextLine();
					
					System.out.printf("Enter item status: ([N]EW, [R]EFURBISHED, [U]SED)");
					String itemStatusString = input.nextLine();
					Status itemStatus;
					// Convert from string to Status
					if (itemStatusString.toUpperCase().charAt(0) == 'N') {
						itemStatus = Status.NEW;
					}
					else if (itemStatusString.toUpperCase().charAt(0) == 'R') {
						itemStatus = Status.REFURBISHED;
					}
					else if (itemStatusString.toUpperCase().charAt(0) == 'U') {
						itemStatus = Status.USED;
					}
					else {
						System.out.println("Invalid Item Status !");
						break;
					}

					System.out.printf("Enter starting price: ");
					String startPriceString = input.nextLine();
					double startPrice;
					// Convert from string to double
					try {
						startPrice = Double.parseDouble(startPriceString);
					}
					catch(Exception e) {
						System.out.println("Error while converting string to double");
						break;
					}
					
					System.out.printf("Enter reserve price: ");
					String reservePriceString = input.nextLine();
					double reservePrice;
					// Convert from string to double
					try {
						reservePrice = Double.parseDouble(reservePriceString);
					}
					catch(Exception e) {
						System.out.println("Error while converting string to double");
						break;
					}
					
					System.out.printf("Enter closing date (format should be like this -> dd-MM-yyyy HH:mm -> example 28-05-2019 17:30): ");
					String closeDateString = input.nextLine();
					LocalDateTime closeDate;
					// Convert from string to LocalDateTime
					try {
						DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
						closeDate = LocalDateTime.parse(closeDateString, format);
					}
					catch(Exception e) {
						System.out.println("Error in date format");
						break;
					}
					
					// If everything is correct, create the auction
					try {
						sys.placeAuction((Seller) currentUser,
										new Item(itemName, itemDescription, itemLocation, itemStatus),
										startPrice,
										reservePrice,
										closeDate);
					}
					catch (Exception e) {
						System.out.println(e.toString()); // If closing date is not between now and 7days after
					}
										
					// Print auction created, to verify press 0, you can verify later if you want
					System.out.printf("Auction Created ! -> Press 0 to verify or press another key to go back, (you can verify later if you want): ");
					String isVerify = input.nextLine();
					
					if (isVerify.toUpperCase().charAt(0) == '0') {
						sys.getAllAuctions().get(sys.getAllAuctions().size() - 1).verify();
						break;
					}
					else {
						break;
					}
				}
				case 4: { // Verify
					
					// Chose one of the pending auctions to verify
					// Get index
					System.out.println("--- PENDING AUCTIONS ---");
					
					// Java 8 version (FIX)
					// List<Auction> pendingAuctions = sys.getAllAuctions().stream().filter(c -> c.getSeller().getUsername().equals(currentUser.getUsername())).collect(Collectors.toList());
					
					// Java 7 Version
					for (Auction auc : sys.getAllAuctions()) {
						if (auc.getSeller().getUsername().equals(currentUser.getUsername())) {
							if (auc.getStatus().equals(Status.PENDING)) {
								System.out.println("INDEX: (" + sys.getAllAuctions().indexOf(auc) + "): " + auc.toString());
							}
						}
					}
					System.out.printf("\nEnter the index of the auction you want to verify: ");
					String indexString = input.nextLine();
					byte index;
					// Convert from string to byte
					try {
						index = Byte.parseByte(indexString);
					}
					catch(Exception e) {
						System.out.println("\nError: you should enter a valid index !");
						break;
					}
					
					sys.getAllAuctions().get(index).verify(); // Verify specified auction
					System.out.println("Auction \"" + sys.getAllAuctions().get(index).getItem().getName() + "\" verified successfully.");
					break;
				}
				case 5: { // Log Out
					lastMenu = "default"; // Goto default menu
					break;
				}
			}
		}
		else if (lastMenu == "buyer") { // BUYER MENU
			System.out.println();
			choice = menu.buyerMenu(); // Get a choice from user
			
			switch (choice) {
				case 1: { // Search
					
					System.out.printf("Enter item name: ");
					String itemName = input.nextLine();
					
					if (sys.getAuctionByItemName(itemName) != null) {
						// Print the auction
						System.out.println(sys.getAuctionByItemName(itemName));						
					}
					else {
						System.out.println("Auction cannot found !\n");
					}
					break;
				}
				case 2: { // Bidding
					
					System.out.println("--- ACTIVE AUCTIONS ---");
					
					for (byte i = 0; i < sys.browseAuctions().size(); i++) {
						System.out.println("INDEX: (" + i + "): " + sys.browseAuctions().get(i).toString());
					}
					
					System.out.printf("\nEnter the index of the auction you want to bid on: ");
					String indexString = input.nextLine();
					byte index;
					// Convert from string to byte
					try {
						index = Byte.parseByte(indexString);
					}
					catch(Exception e) {
						System.out.println("Error: you should enter a valid index !");
						break;
					}
					
					System.out.printf("Enter the amount");
					String amountString = input.nextLine();
					double amount;
					// Convert from string to double
					try {
						amount = Double.parseDouble(amountString);
					}
					catch(Exception e) {
						System.out.println("Error while converting string to double");
						break;
					}
					
					// Place the Bid
					try {
						sys.browseAuctions().get(index).placeBid(amount, (Buyer) currentUser);						
					}
					catch (Exception e) {
						System.out.println(e.toString()); // Amount should between %10 - %20 bigger than last bid !
					}
					break;
				}
				case 3: { // Bid History
					
					System.out.println("--- BID HISTORY ---");
					
					for (byte i = 0; i < sys.getAllAuctions().size(); i++) {
						if (!sys.getAllAuctions().get(i).getBids().isEmpty()) {
							for (byte j = 0; j < sys.getAllAuctions().get(i).getBids().size(); j++) {
								if (sys.getAllAuctions().get(i).getBids().get(j).getWho().equals((Buyer) currentUser)) {
									System.out.println(sys.getAllAuctions().get(i).getBids().get(j).toString());
								}
							}
						}
					}
					break;
				}
				case 4: { // Browse Auctions
					
					System.out.println("--- ACTIVE AUCTIONS ---");
					
					for (byte i = 0; i < sys.browseAuctions().size(); i++) {
						System.out.printf("%d: ", i + 1);
						System.out.println(sys.browseAuctions().get(i).toString());
						System.out.println();
					}
					break;
				}
				case 5: { // Log Out
					lastMenu = "default";  // Goto default menu
					break;
				}
			}
		}
		else if (lastMenu == "admin") { // ADMIN  MENU
			
			System.out.println();
			choice = menu.adminMenu(); // Get a choice from user
			
			switch (choice) {
				case 1: { // Get all auctions in the system
					System.out.println("All Auctions: ");
					for (Auction auc : sys.getAllAuctions()) {
						System.out.println(auc.toString());						
					}
					break;
				}
				case 2: { // Delete user
					for (User u : sys.getAllUsers()) {
						System.out.println(u.getUsername());
					}
					System.out.printf("\nEnter the name of the user you want to delete: ");
					String name = input.nextLine();
					try {
						sys.deleteUser(name);
					}
					catch (Exception e) {
						System.out.println("Invalid username !\n");
					}
					break;
				}
				case 3: { // Block seller
					System.out.printf("\n[B]lock or [U]nlock: ");
					String selection = input.nextLine();
					System.out.println();
					if (selection.toLowerCase().charAt(0) == 'b') {
						/*for (Seller s : sys.getAllSellers()) {
							if (!s.isBlocked()) {
								System.out.println(s.getUsername());															
							}
						}*/
						System.out.printf("\nEnter the name of the seller you want to block: ");
						String name = input.nextLine();
						try {
							sys.getSellerByName(name).setBlocked();
							System.out.println(name + " blocked succesfully !");
						}
						catch (Exception e) {
							System.out.println("Invalid username !\n");
						}
					}
					else {
						/*for (Seller s : sys.getAllSellers()) {
							if (s.isBlocked()) {
								System.out.println(s.getUsername());															
							}
						}*/
						System.out.printf("\nEnter the name of the seller you want to unlock: ");
						String name = input.nextLine();
						try {
							sys.getSellerByName(name).setUnBlocked();
							System.out.println(name + " unlocked succesfully !");
						}
						catch (Exception e) {
							System.out.println("Invalid username !\n");
						}
					}
					break;
				}
				case 4: {
					System.out.printf("\n[B]lock or [U]nlock: ");
					String selection = input.nextLine();
					if (selection.toLowerCase().charAt(0) == 'b') {
						for (Auction auc : sys.browseAuctions()) {
							if (!auc.isBlocked()) {
								System.out.println(auc.getItem().getName());															
							}
						}
						System.out.printf("\nEnter the item name of the auction you want to block: ");
						String name = input.nextLine();
						try {
							sys.getAuctionByItemName(name).setBlocked();
							System.out.println(name + " blocked succesfully !");
						}
						catch (Exception e) {
							System.out.println("Invalid username !\n");
						}
					}
					else {
						for (Auction auc : sys.browseAuctions()) {
							if (auc.isBlocked()) {
								System.out.println(auc.getItem().getName());															
							}
						}
						System.out.printf("\nEnter the item name of the auction you want to unlock: ");
						String name = input.nextLine();
						try {
							sys.getAuctionByItemName(name).setUnBlocked();
							System.out.println(name + " unlocked succesfully !");
						}
						catch (Exception e) {
							System.out.println("Invalid username !\n");
						}
					}
					break;
				}
				case 5: {
					System.out.printf("\nDefault interval is 10 seconds, enter the new interval in seconds:  ");
					String intervalString = input.nextLine();
					int interval = 10;
					
					try {
						interval = Integer.parseInt(intervalString);
					}
					catch (Exception e) {
						System.out.println("Error while converting string to int");
						break;
					}
					
					// Kill old thread
					t.stop();
					t.destroy();
					
					// Create new one
					Thread t = new Thread(new StatusCheck(sys, interval)); // Update delay time
					// timer.setDelay(interval);
					break;
				}
				case 6: { // Closing an Auction manually
					System.out.println("--- ACTIVE AUCTIONS ---");
					for (byte i = 0; i < sys.browseAuctions().size(); i++) {
						System.out.println("INDEX: (" + i + "): " + sys.browseAuctions().get(i).toString());
						}
					System.out.printf("\nEnter the index of the auction you want to close: ");
					String indexString = input.nextLine();
					byte index;
					
					try {
						index = Byte.parseByte(indexString);
					}
					catch(Exception e) {
						System.out.println("\nError: you should enter a valid index !");
						break;
					}
					sys.browseAuctions().get(index).close();
					sys.browseAuctions().get(index).getWinner().victory(sys.browseAuctions().get(index), sys.browseAuctions().get(index).getBestBid());
					break;
				}
				case 7: { // Log Out
					lastMenu = "default"; // Goto default menu
					break;
				}
			}
		}
	}
}
