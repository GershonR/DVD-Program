class Node {
	
	private Data data;
	
	Node next;
	
	public Node getNext() {
		return next;
	}
	
	public void setNext(Node nextNode) {
		this.next = nextNode;
	}
	
	public void setData(Data data) {
		this.data = data;
	}
	
	public Data getData() {
		return data;
	}
}