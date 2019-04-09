package eAuctionSystem;

/**
* Item Class
* @author 	Enes Demirag & Damon Gilbert
* @version	1.0
*/
public class Item {
	
	private String name;
	private String description;
	private String location;
	private Status status;

	/**
	* Item Constructor
	* @param	name			name of the item
	* @param	description		detailed description about item
	* @param	location		location of the item
	* @param	status			status of the item (NEW, REFURBISHED, USED)
	* @see		Status
	*/
	public Item(String name, String description, String location, Status status) {
		this.setName(name);
		this.setDescription(description);
		this.setLocation(location);
		this.setStatus(status);
	}

	/**
	* @return the name of the Item
	*/
	public String getName() {
		return this.name;
	}

	/**
	* Sets the name of the Item
	*/
	public void setName(String name) {
		this.name = name;
	}

	/**
	* @return the description of the Item
	*/
	public String getDescription() {
		return this.description;
	}
	
	/**
	* Sets the description of the Item
	*/
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	* @return the location of the Item
	*/
	public String getLocation() {
		return this.location;
	}

	/**
	* Sets the location of the Item
	*/
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	* @return the status of the Item
	* @see	Status
	*/
	public Status getStatus() {
		return this.status;
	}
	/**
	* Sets the status of the Item
	* @see	Status
	*/
	public void setStatus(Status status) {
		this.status = status;
	}
}
