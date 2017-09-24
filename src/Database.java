class Database {
	private SearchableList DVDList = new SearchableList();
	private OrderedList CustomerList = new OrderedList();
	
	/**
	 * Add data to a list
	 * 
	 * @param name the name of the node
	 */
	public void addData(Node name) {
		if(name != null && name.getData() instanceof Customer) { // Add a Customer
			CustomerList.add(name);
		} else if(name != null && name.getData() instanceof DVD) { // Add a DVD
			DVDList.add(name);
		} else if(name != null && Data.DEBUG_MODE) {
			System.out.println("Tried to add an object of unknown type");
		} else if (Data.DEBUG_MODE) {
			System.out.println("Tried to add a NULL object");
		}
	}
	
	/**
	 * Delete data from a list
	 * 
	 * @param name the name of the node
	 */
	public void deleteData(Node name) {
		if(name != null && name.getData() instanceof Customer) { // Remove a Customer
			CustomerList.remove(name);
		} else if(name != null && name.getData() instanceof DVD) { // Remove a DVD
			DVDList.remove(name);
		} else if(name != null && Data.DEBUG_MODE) {
			System.out.println("Tried to remove an object of unknown type");
		} else if (Data.DEBUG_MODE) {
			System.out.println("Tried to remove a NULL object");
		}
	}

	/**
	 * See if a DVD exists in the database
	 * 
	 * @param name the name of the DVD to check
	 * @return true if the DVD exists, false otherwise
	 */
	public boolean DVDExists(String name) {
		boolean toReturn = false;
		if(DVDList.contains(name))
			toReturn = true;
		return toReturn;
	}
	
	/**
	 * See if a customer exists in the database
	 * 
	 * @param name the name of the customer to check
	 * @return true if the customers exists, false otherwise
	 */
	public boolean customerExists(String name) {
		boolean toReturn = false;
		if(CustomerList.contains(name))
			toReturn = true;
		return toReturn;
	}
	
	/**
	 * Print the customer list by calling each customers
	 * print() function
	 */
	public void printCustomerList() {
		Node curr = CustomerList.getTop();
		while(curr != null) {
			Customer c = (Customer) curr.getData();
			c.print();
			curr = curr.getNext();
		}
			
	}
	
	/**
	 * Increment a certain DVD by an amount
	 * 
	 * @param name the name of the DVD to increment
	 * @param quantity the amount of increment by
	 */
	public void incrementDVD(String name, int quantity) {
		if(name != null) {
			Node node = DVDList.getNode(name);
			if(node != null) {
				DVD dvd = (DVD) node.getData();
				dvd.addQuantity(quantity);
			}
		}
	}
	
	/**
	 * Get a DVD data type by its name
	 * 
	 * @param name the name of the DVD
	 * @return the data type of that DVD
	 */
	public DVD getDVD(String name) {
		DVD dvd = null;
		if(name != null) {
			Node node = DVDList.getNode(name);
				if(node != null)
					dvd = (DVD) node.getData();
		}
		
		return dvd;
	}
	
	/**
	 * Get a DVD node defined by its name
	 * 
	 * @param name the name of the DVD
	 * @return the Node type of that DVD
	 */
	public Node getDVDNode(String name) {
		Node dvd = null;
		if(name != null) {
			dvd = DVDList.getNode(name);
		}
		return dvd;
	}
	
	/**
	 * Get a Customer data type defined by its name
	 * 
	 * @param name the name of the customer
	 * @return the data type of that customer
	 */
	public Customer getCustomer(String name) {
		Customer customer = null;
		if(name != null) {
			Node node = CustomerList.getNode(name);
				if(node != null)
					customer = (Customer) node.getData();
		}
		return customer;
	}
	
	/**
	 * Get a Customer Node type defined by its name
	 * 
	 * @param name the name of the customer
	 * @return the Node type of that customer
	 */
	public Node getCustomerNode(String name) {
		Node customer = null;
		if(name != null) {
			customer = CustomerList.getNode(name);
		}
		return customer;
	}
	
	/**
	 * Get the list of all DVD's in the database
	 * 
	 * @return the list of DVD's
	 */
	public SearchableList getDVDList() {
		return DVDList;
	}
	
	/**
	 * Get the list of all customers in the database
	 * in sorted order
	 * 
	 * @return the list of customers in sorted order
	 */
	public OrderedList getCustomerList() {
		return CustomerList;
	}
	
}
