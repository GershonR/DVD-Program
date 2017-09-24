
/**
 * @author Gershon Reydman
 * Basic Linked List class
 *
 */
abstract class LinkedList {
	Node top;
	
	public abstract int size();
	public abstract boolean contains(String name);
	public abstract void add(Node name);
	public abstract void remove(Node name);
	public abstract Node getNode(String name);
	
	
	public Node getTop() {
		return top;
	}
	
	
	public void setTop(Node top) {
		this.top = top;
	}
}
