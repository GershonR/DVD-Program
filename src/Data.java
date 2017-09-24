
public abstract class Data {
	public static boolean DEBUG_MODE = true; // True to display all errors,warning,successes - False to disable 
	
	public abstract String toString();
	public abstract String getName();
	public abstract void setName(String name);
	public abstract int getLoans();
}
