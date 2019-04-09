package Console;

import java.util.Scanner;

public class Menu {

	Scanner input = new Scanner(System.in);
	String choice;
	
	public byte defaultMenu() {
		
        System.out.println("***** Auction System Default Menu *****");
        System.out.println("1. [S]ign Up");
        System.out.println("2. [L]og In");
        System.out.println("3. [B]rowse Auctions");
        System.out.println("4. [Q]uit");
        System.out.printf("--> ");
        
        choice = input.nextLine();
        
        switch (choice.toUpperCase().charAt(0)) {
	        case '1':
	        case 'S': {
	            return 1;
	        }
	        case '2':
	        case 'L': {
	            return 2;
	        }
	        case '3':
	        case 'B': {
	            return 3;
	        }
	        case '4':
	        case 'Q': {
	            System.exit(0);
	        }
	        default: {
	            System.out.printf("No valid response. Try Again.");
	            return defaultMenu();
	        }
        }
	}
	
	public byte sellerMenu() {
		
        System.out.println("***** Auction System Seller Menu *****");
        System.out.println("1. [B]rowse my Auctions");
        System.out.println("2. [S]earch Item");
        System.out.println("3. [C]reate an Auction");
        System.out.println("4. [V]erify an Auction");
        System.out.println("5. [L]og Out");
        System.out.println("6. [Q]uit");
        System.out.printf("--> ");
        
        choice = input.nextLine();
        
        switch (choice.toUpperCase().charAt(0)) {
	        case '1':
	        case 'B': {
	            return 1;
	        }
	        case '2':
	        case 'S': {
	            return 2;
	        }
	        case '3':
	        case 'C': {
	            return 3;
	        }
	        case '4':
	        case 'V': {
	            return 4;
	        }
	        case '5':
	        case 'L': {
	            return 5;
	        }
	        case '6':
	        case 'Q': {
	            System.exit(0);
	        }
	        default: {
	            System.out.printf("No valid response. Try Again.");
	            return sellerMenu();
	        }
        }
	}
	
	public byte buyerMenu() {
	
		System.out.println("***** Auction System Buyer Menu *****");
        System.out.println("1. [S]earch Item");
        System.out.println("2. [P]lace Bid");
        System.out.println("3. Bid History");
        System.out.println("4. Browse Auctions");
        System.out.println("5. [L]og Out");
        System.out.println("6. [Q]uit");
        System.out.printf("--> ");

        choice = input.nextLine();
        
        switch (choice.toUpperCase().charAt(0)) {
	        case '1':
	        case 'S': {
	            return 1;
	        }
	        case '2':
	        case 'P': {
	            return 2;
	        }
	        case '3': {
	            return 3;
	        }
	        case '4': {
	            return 4;
	        }
	        case '5':
	        case 'L': {
	        	return 5;
	        }
	        case '6':
	        case 'Q': {
	        	System.exit(0);
	        }
	        default: {
	            System.out.printf("No valid response. Try Again.");
	            return buyerMenu();
	        }
        }
	}
	
	public byte adminMenu() {
		
		System.out.println("***** Auction System Admin Menu *****");
        System.out.println("1. [G]et All Auctions");
        System.out.println("2. [D]elete a User");
        System.out.println("3. Block or Unlock a Seller");
        System.out.println("4. Block or Unlock an Auction");
        System.out.println("5. Change thread interval");
        System.out.println("6. Close an Auction");
        System.out.println("7. [L]og Out");
        System.out.println("8. [Q]uit");
        System.out.printf("--> ");

        choice = input.nextLine();
        
        switch (choice.toUpperCase().charAt(0)) {
	        case '1':
	        case 'G': {
	            return 1;
	        }
	        case '2':
	        case 'D': {
	            return 2;
	        }
	        case '3': {
	            return 3;
	        }
	        case '4': {
	            return 4;
	        }
	        case '5': {
	        	return 5;
	        }
	        case '6': {
	            return 6;
	        }
	        case '7': 
	        case 'L': {
	            return 7;
	        }
	        case '8':
	        case 'Q': {
	        	System.exit(0);
	        }
	        default: {
	            System.out.printf("No valid response. Try Again.");
	            return adminMenu();
	        }
        }
	}
}
