
public class Customer extends Data {
	SearchableList WishList = new SearchableList();
	OrderedList LoanedList = new OrderedList();
	
	String name;
	int maxDVDs;

	public String toString() {
		return "Customer: " + this.name + " max dvds: " + maxDVDs + " on loan: " + this.getLoans();
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getMax() {
		return maxDVDs;
	}
	
	public void setMax(int max) {
		this.maxDVDs = max;
	}
	
	/**
	 * Add a dvd to a customers wishlist
	 * 
	 * @param dvd the dvd to add
	 */
	public void addToWish(Node dvd) {
		if(dvd != null) {
			if(WishList.contains(dvd.getData().getName())) {
				if(Data.DEBUG_MODE)
					System.out.println("ERROR: " + dvd.getData().getName() + " is already on " + this.getName() + "'s wishlist!");
			} else {
				Node theDVD = new Node();
				theDVD.setData(dvd.getData());
				WishList.add(theDVD);
				if(Data.DEBUG_MODE)
					System.out.println("WARNING: " + dvd.getData().getName() + " unavailable! Moving to " + this.getName() + "'s wishlist.");
			}
		} else {
			System.out.println("ERROR: dvd is NULL");
		}
	}
	
	/**
	 * Add a dvd to a customers loan list
	 * 
	 * @param dvd the dvd to add
	 */
	public void addToList(Node dvd) {
		if(dvd != null) {
			if(LoanedList.contains(dvd.getData().getName())) {
				if(Data.DEBUG_MODE)
					System.out.println("ERROR: This DVD is already on the customers loanlist!");
			} else {
				Node theDVD = new Node();
				theDVD.setData(dvd.getData());
				LoanedList.add(theDVD);
			}
		} else {
			System.out.println("ERROR: dvd is NULL");
		}
	}
	
	/**
	 * Print the customers loanlist and wishlist
	 * The loanlist is sorted alphabetically
	 * The wishlist is sorted in order of requested, oldest to recent
	 * 
	 */
	public void print() {
		System.out.println(this.getName());
		System.out.println("---");
		System.out.println("DVDS ON LOAN");
		Node curr = LoanedList.getTop();
		while(curr != null) {
			System.out.println(" " + curr.getData().getName());
			curr = curr.getNext();
		}
		System.out.println("QUEUE");
		curr = WishList.getTop();
		while(curr != null) {
			System.out.println(" " + curr.getData().getName());
			curr = curr.getNext();
		}
		System.out.println("");
	}
	
	public int getLoans() {
		return this.getLoanList().size();
	}
	
	public OrderedList getLoanList() {
		return this.LoanedList;
	}
	
	public SearchableList getWishList() {
		return this.WishList;
	}
}
