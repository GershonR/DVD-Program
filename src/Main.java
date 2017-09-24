/*
 * Main class
 *
 */
import java.io.*;
 
/**
 * @author Gershon Reydman 7763541
 *
 */
public class Main {

  public static void main(String [] args){
	Database database = new Database();
	Node node;
	String name;
	String secondName;
    // check that a command-line argument was provided
    if(args.length == 0){
      System.out.println("Usage: java Main <input file>");
      System.exit(0);
    }
    BufferedReader inFile = File.openInputFile(args[0]);
    
    // Event processing loop
    String line = File.getLine(inFile);
    if(line != null)
        System.out.println(line);
    while(line != null){
      line = File.getLine(inFile);
      String[] theLine;
      if(line != null) {
    	  System.out.println(line);
    	  theLine = line.split(" ");
    	  switch(theLine[0]) {
      	
      		case "ACN":
      			name = Util.createName(theLine, "", 2); // Customer name
      			Events.addCustomer(name, Integer.parseInt(theLine[1]), database);
      		break;
      		
      		case "ACQ":
      			name = Util.createName(theLine, "", 2);// Customer name
      			Events.addDVD(name, Integer.parseInt(theLine[1]), database);
      		break;
      		
      		case "ADD":
      			name = Util.createName(theLine, "FOR", 1);
      			secondName = Util.createName(line, "FOR");
      			Events.addDVDToCustomer(name, secondName, database);
      		break;
      		
      		case "REM":
      			name = Util.createName(theLine, "FOR", 1);
      			secondName = Util.createName(line, "FOR");
      			Events.removeFromList(name, secondName, database);
      		break;
      		
      		case "RET":
      			name = Util.createName(theLine, "FROM", 1);
      			secondName = Util.createName(line, "FROM");
      			Events.removeDVDFromCustomer(name, secondName, database);
      		break;
      		
      		case "LOS":
      			name = Util.createName(theLine, "FROM", 1);
      			secondName = Util.createName(line, "FROM");
      			Events.loseDVD(name, secondName, database);
      		break;
      		
      		case "RCN":
      			name = Util.createName(theLine, "", 1);
      			Events.removeCustomer(name, database);
      		break;
    	  }
      }
    }// while
    
    File.closeFile(inFile);
    
    database.printCustomerList();
    System.out.println("\n\nEnd of processing.");
    System.exit(0);
  }// main
  
}// class A1

