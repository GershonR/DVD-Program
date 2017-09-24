
/**
 * @author Gershon Reydman
 * String utilities class
 */
class Util {
	
	
	/**
	 * Return a string starting at a certain point
	 * in the split array until it hits a specific string
	 * 
	 * @param line the split array
	 * @param until point to stop
	 * @param start point to start in the array
	 * @return the string between start and until without the space at the end
	 */
	public static String createName(String line[], String until, int start) {
		String name = "";
		for(int x = start; x < line.length; x++)
			name += line[x] + " "; // Create the string begining at start
		if(line != null && until != null) {
			if(until.equals(""))
				name = name.substring(0, name.length()-1); // Go to the end of the string, and trim the space
			else
				name = name.substring(0, name.indexOf(until)-1); // Go until it reached the required string, and trim the space
		}
		return name;
	}
	
	/**
	 * Return a string beggining at a specific string
	 * in the line to the very end
	 * 
	 * @param line the line to work on
	 * @param start the string start at
	 * @return the string with everything after start
	 */
	public static String createName(String line, String start) {
		String name = "";
		if(line != null && start != null) {
			int offSet = start.length() + 1; // Step over start string and skip the space
			name = line.substring(line.indexOf(start) + offSet);
		}
		return name;
	}
	
	/**
	 * Create either a Customer or DVD node
	 * 
	 * @param name the name of the data
	 * @param max the amount of dvds or the max the customer can have
	 * @param type either Customer or DVD
	 * @return the created node
	 */
	public static Node createNode(String name, int max, String type) {
			Node node = new Node();
			if(type.equals("Customer")) {
				Customer c = new Customer();
				c.setName(name);
				c.setMax(max);
				node.setData(c);
			} else if (type.equals("DVD")) {
				DVD d = new DVD();
				d.setName(name);
				d.setQuantity(max);
				node.setData(d);
			}
			return node;
	}
	
	/**
	 * Checks to see if a DVD or Customer exists in the database
	 * 
	 * @param dvd the dvd to check
	 * @param customer the customer to check
	 * @param database the database to check on
	 * @return true if both exist, false if either does not exist
	 */
	public static boolean dataExists(String dvd, String customer, Database database) {
		boolean exists = true;
		if(!database.customerExists(customer)) {
			if(Data.DEBUG_MODE)
				System.out.println("ERROR: Customer " + customer + " does not exist in the database!");
			exists = false;
		}
		
		if(!database.DVDExists(dvd)) {
			if(Data.DEBUG_MODE)
				System.out.println("ERROR: DVD " + dvd + " does not exist in the database!");
			exists = false;
		}
		
		return exists;
		
	}
	
	/**
	 * Checks to see if a customer exists
	 * 
	 * @param customer the customer to check
	 * @param database the customer to check on
	 * @param print true to execute the print out, false otherwise
	 * @return true if the customer exists, false otherwise
	 */
	public static boolean dataExists(String customer, Database database, boolean print) {
		boolean exists = true;
		if(!database.customerExists(customer)) {
			if(Data.DEBUG_MODE && print)
				System.out.println("ERROR: That customer does not exist!");
			exists = false;
		}
		
		return exists;
		
	}


		
}