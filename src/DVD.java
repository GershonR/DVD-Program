class DVD extends Data {
	String name;
	int quantity;
	int loans;
	
	public String toString() {
		return "DVD: " + this.name + " current amount:" + quantity;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void addQuantity (int toAdd) {
		this.quantity += toAdd;
	}
	
	public void decreaseQuantity(int toRemove) {
		this.quantity -= toRemove;
	}
	
	public int getLoans() {
		return loans;
	}
	
	public void increaseLoans() {
		this.loans++;
	}
	
	public void decreaseLoans() {
		this.loans--;
	}
	
}