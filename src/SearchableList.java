class SearchableList extends LinkedList {
	
	private int size = 0;
	
	
	public int size() {
		return this.size;
	}
	
	/**
	 * Insert a node to the end of the list
	 * 
	 * @param toInsert the node to insert
	 */
	public void add(Node toInsert) {
		if(toInsert == null) {
			if(Data.DEBUG_MODE)
				System.out.println("Warning: Tried to insert a NULL node.");
			return;
		} else {
			if(top == null) {
					top = toInsert;
					size = 1;
			} else {
				Node curr = top;
				while(curr.getNext() != null) {
					curr = curr.getNext();
				}
				curr.setNext(toInsert);
				size++;
			}
		}
	}
	/**
	 * Remove a specific node from the list
	 * 
	 * @param toRemove the node to remove
	 */
	public void remove(Node toRemove) {
		Node curr = top;
		Node prev = top;
		if(toRemove == null || curr == null) {
			if(Data.DEBUG_MODE)
				System.out.println("ERROR: Tried to remove a NULL node.");
			return;
		}
		if(curr.getData().getName().equals(toRemove.getData().getName())) {
			top = top.getNext(); // Remove the top node
		} else {
			curr = curr.getNext();
			while(curr != null) {
			
				if(curr.getData().getName().equals(toRemove.getData().getName())) {
					prev.setNext(curr.getNext());
				}
				prev = prev.getNext();
				curr = curr.getNext();
			}
		}
		size--;
		if(size < 0)
			size = 0;
	}
	
	/**
	 * Checks to see if a node exists in the list
	 * 
	 * @param toFind the data to find
	 * @return true if that data exists, false otherwise
	 */
	public boolean contains(String toFind) {
		boolean inList = false;
		Node curr = top;
		while(curr != null) {
			if(curr.getData().getName().equals(toFind)) {
				return true;
			}
			curr = curr.getNext();
		}
		return inList;
	}
	

	
	/**
	 * Gets a node by its index
	 * 
	 * @param index the index of the node to get
	 * @return the node at spot index
	 */
	public Node getNode(int index) {
		Node curr = top;
		int spot = 0;
		while(curr != null && index >= 0) {
			if(spot == index)
				return curr;
			spot++;
			curr = curr.getNext();
		}
		return null;
	}
	
	/**
	 * Gets a node defined by its name
	 * 
	 * @param name the name of the data in the node
	 * @return the node associated by its name
	 */
	public Node getNode(String name) {
		Node curr = top;
		while(curr != null) {
			if(curr.getData().getName().equals(name))
				return curr;
			curr = curr.getNext();
		}
		return null;
	}

}
