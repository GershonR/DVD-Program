
/**
 * @author Gershon Reydman
 * 
 * All the cases for each event
 *
 */
public class Events {
	
	/**
	 * Add a DVD to the Netflix database
	 * 
	 * @param name name of the dvd
	 * @param quantity amount of the dvd available
	 * @param database the database to add the dvd to
	 */
	public static void addDVD(String name, int quantity, Database database) {
			if(database.DVDExists(name)) {
  				database.incrementDVD(name, quantity);
  			} else {
  				Node node = Util.createNode(name, quantity, "DVD");
  				database.addData(node);
      			if(Data.DEBUG_MODE)
      				System.out.println("SUCCESS: Added " + quantity + " copies of " + name);
      			for(int x = 0; x < quantity; x++) // Try and assign the new dvd's to everyone
      				addRandomDVD(name, database);
  			}
	}
	
	/**
	 * Add a customer to the Netflix database
	 * 
	 * @param name the name of the customer
	 * @param maxDVDs the max amount of dvds they can have
	 * @param database the name of the Netflix database
	 */
	public static void addCustomer(String name, int maxDVDs, Database database) {
			if(Util.dataExists(name, database, false))
  				System.out.println("ERROR: Tried to create an already existing customer!");
  			else {
      			Node node = Util.createNode(name, maxDVDs, "Customer");
      			database.addData(node);
      			if(Data.DEBUG_MODE)
      				System.out.println("SUCCESS: Created " + name + " with " + maxDVDs + " max dvds.");
  			}
	}
	
	/**
	 * Remove a customer from the Netflix database
	 * 
	 * @param name the name of the customer to remove
	 * @param database the name of the database
	 */
	public static void removeCustomer(String name, Database database) {
		if(Util.dataExists(name, database, true)) {
  			Node node = database.getCustomerList().getNode(name);
  			if(node != null) {
  				Customer c = (Customer) node.getData();
  				if(c.getLoans() > 0) { // They still have DVD's loaned out
					if(Data.DEBUG_MODE)
						System.out.println("ERROR: Could not remove customer " + c.getName() + ", this customer has DVDs on loan.");
  				} else {
  					database.getCustomerList().remove(node);
					if(Data.DEBUG_MODE) {
						System.out.println("SUCCESS: Customer " + name + " has been removed!");
					}
						
  				}
  			} else if(Data.DEBUG_MODE) {
  				System.out.println("ERROR: Tried to remove a NULL customer!");
  			}
		}
	}
	
	/**
	 * Add a DVD to a customer
	 * 
	 * @param dvd the name of the dvd
	 * @param customer the name of the customer
	 * @param database the name of the database
	 */
	public static void addDVDToCustomer(String dvd, String customer, Database database) {
		if(Util.dataExists(dvd, customer, database)) { // See if the customer and dvd exist
			Node dvdNode = database.getDVDList().getNode(dvd);
			Node customerNode = database.getCustomerList().getNode(customer);
			if(dvdNode != null && customerNode != null) {
				Customer c = (Customer) customerNode.getData();
				DVD d = (DVD) dvdNode.getData();
				if(c.getLoans() >= c.getMax()) { // They have loaned out to many
					System.out.println("WARNING: Exceeds maximum loans, adding to wishlist..");
					c.addToWish(dvdNode); // If a user loaned out the maximum number of dvd's add to their wish list
				} else if (c.getLoanList().contains(dvd)) {
					if(Data.DEBUG_MODE)
						System.out.println("ERROR: Did not add " + dvd + " to " + customer + " this customer already has this DVD!");
				} else {
					if(d.getQuantity() > d.getLoans()) {
						if(c.getWishList().contains(dvd))
							c.getWishList().remove(dvdNode); // If a user has this dvd in their wish list, remove it
						c.addToList(dvdNode); // Add the dvd to their loan list
						d.increaseLoans();
						if(Data.DEBUG_MODE)
							System.out.println("SUCCESS: Added " + dvd + " to " + customer);
					} else {
						c.addToWish(dvdNode); // Add to wish list if their are no available copies of the dvd
					}
				}

				
			} else if (Data.DEBUG_MODE) {
				System.out.println("ERROR: The DVD Node or Customer Node is NULL!");
			}
		}
	}
	
	/**
	 * Create a temporary list of customers who want a certain dvd
	 * and assign it to a random person in that list
	 * 
	 * @param dvd the dvd to check
	 * @param database the name of the database
	 */
	public static void addRandomDVD(String dvd, Database database) {
		SearchableList tempList = new SearchableList();
		
		if(database.DVDExists(dvd)) {
			for(int x = 0; x < database.getCustomerList().size(); x++) {
				Node customerNode = database.getCustomerList().getNode(x); // Grab each customer
				if(customerNode != null) {
					Customer customerData = (Customer) customerNode.getData();
					if(customerData.getWishList().contains(dvd) && customerData.getLoans() < customerData.getMax()) { // See if they want it and are able to take it as a loan
						Node toAdd = new Node();
						toAdd.setData(customerData);
						tempList.add(toAdd);
					}
				}
				
			}
		}
		
		if(tempList.size() > 0) { // Atleast 1 person wants that dvd
			int randomCustomer = (int)(Math.random() * tempList.size()); // Pick a random customer
			Node customerNode = tempList.getNode(randomCustomer);
			Customer customerData = (Customer) customerNode.getData();
			addDVDToCustomer(dvd, customerData.getName(), database);
		}
	}
	
	/**
	 * A customer loses a dvd remove a copy from
	 * all available dvds
	 * 
	 * @param dvd the name of the dvd lost
	 * @param customer the name of the customer who lost the dvd
	 * @param database the name of the database
	 */
	public static void loseDVD(String dvd, String customer, Database database) {
		if(Util.dataExists(dvd, customer, database)) { // That dvd and customer should exist
			Customer customerData = database.getCustomer(customer);
			Node DVD = database.getDVDNode(dvd);
			if(DVD != null) {
				if(!customerData.getLoanList().contains(dvd)) { // The customer does not have that dvd on loan
					if(Data.DEBUG_MODE)
						System.out.println("ERROR: " + customer + " does not have " + dvd + " in their loanlist!");
				} else {
					DVD d = (DVD) DVD.getData();
					customerData.getLoanList().remove(DVD);
					d.decreaseQuantity(1);
					if(d.getQuantity() <= 0)
						d.setQuantity(0);
					if(Data.DEBUG_MODE)
						System.out.println("SUCCESS: Removed 1 copy of " + dvd + " that " + customer + " lost.");
				}
			}

		}
}
	
	/**
	 * Remove a dvd from a customers wishlist
	 * 
	 * @param dvd the name of the dvd to remove
	 * @param customer the name of the customer who wants it removed
	 * @param database the name of the database
	 */
	public static void removeFromList(String dvd, String customer, Database database) {
		if(Util.dataExists(dvd, customer, database)) { // That dvd and customer should exist
			Customer customerData = database.getCustomer(customer);
			Node DVD = database.getDVDNode(dvd);
			if(DVD != null) {
				if(!customerData.getWishList().contains(dvd)) { // The customer does not have that dvd in their wishlist
					if(Data.DEBUG_MODE)
						System.out.println("ERROR: " + customer + " does not have " + dvd + " in their wishlist!");
				} else {
					customerData.getWishList().remove(DVD);
					if(Data.DEBUG_MODE)
						System.out.println("SUCCESS: Removed " + dvd + " from " + customer + "'s wishlist!");

				}
			}
		}
	}
	
	/**
	 * Remove a dvd from a customers loan list
	 * 
	 * @param dvd the name of the dvd to remove
	 * @param customer the name of the customer who wants it removed
	 * @param database the name of the database
	 */
	public static void removeDVDFromCustomer(String dvd, String customer, Database database) {
		if(Util.dataExists(dvd, customer, database)) { // That dvd and customer should exist
			Customer customerData = database.getCustomer(customer);
			Node dvdNode = database.getDVDNode(dvd);
			if(dvdNode != null) {
				DVD d = (DVD) dvdNode.getData();
				if(!customerData.getLoanList().contains(dvd)) { // The customer does not have that dvd on loan
					if(Data.DEBUG_MODE)
						System.out.println("ERROR: " + customer + " does not have " + dvd + " in their loanlist!");
				} else {
					customerData.getLoanList().remove(dvdNode);
					d.decreaseLoans();
					if(Data.DEBUG_MODE)
						System.out.println("SUCCESS: Removed " + dvd + " from " + customer + "'s loanlist!");
					addRandomDVD(dvd, database); // Try and assign that DVD to someone else
				}
			} else if(Data.DEBUG_MODE) {
				System.out.println("ERROR: Cannot remove " + dvd + " from " + customer + " that DVD does not exist!");
			}
		}
	}

}
