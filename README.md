# DVD-Program
A program to simulate DVD rentals.

Debug:
- In Data.java, DEBUG_MODE can be set to false to prevent all the ERROR, WARNING and SUCCESS messages

Lists:
- LinkedList.java (Abstract and requires implementation of basic methods such as remove,add,contains)
		>- SearchableList.java, adds nodes to the end of the list
		>- OrderedList.java, stores nodes in alphabetical order from A -> Z
		
Data:
	- Stored in each Node
		>- Customer, contains a customers wishlist, loanlist, the maximum of dvds that are allowed on loan, and the current number on loan
		>- DVD, contains the current quantity and the number that are currently on loan


Events:
	- ACN: Events.java addCustomer(String name, int maxDVDs, Database database)
		- Checks to see if that customer already exists in the database, if its does, it produces a print message using Util.dataExists(name, database, false)
		- Otherwise it creates the node and stores it in the Database class in sorted order
	- ACQ: Events.java addDVD(String name, int quantity, Database database)
		- Checks to see if that DVD already exists in the Netflix database, if it does, it increments it by "quantity" amount
		- Otherwise it creates a new node in the Database list of DVD's with "quantity" amount available
		- It also tries to assign the newely added DVDs to people who might want it in their wishlist
	- ADD: Events.java addDVDToCustomer(String dvd, String customer, Database database)
		- Checks to see if the DVD and customer exist, if they don't, produce output stating so
		- If a user has already taken out more loans than they are allowed it will produce a message and then add the DVD to their wishlist
		- If a user already has that DVD it will print a message stating so
		- If the DVD already has too many copies loaned out, it will add it to the users wishlist
		- Otherwise it gets added to the users loan list
	- REM: Events.java removeFromList(String dvd, String customer, Database database)
		- Checks to see if the DVD and customer exist, if they don't, produce output stating so
		- If a user does not have that DVD in their wishlist it will produce output stating so
		- Otherwise remove it from the users wishlist
	- RET: Events.java returnDVD(name, secondName, database)
		- Checks to see if the DVD and customer exist, if they don't, produce output stating so
		- If a user does not have that DVD in their loanlist it will produce output stating so
		- Otherwise remove it from their loanlist
	- LOS: Events.java loseDVD(String dvd, String customer, Database database)
		- Checks to see if the DVD and customer exist, if they don't, produce output stating so
		- If a user did not have that DVD on loan it will produce output stating so
		- Otherwise it will remove 1 copy from the Netflix database
			- If their are less than 0, changes to 0 but keeps it in the database in-case more are added in the future
	- RCN: Events.java removeCustomer(String name, Database database)
		- Checks to see if that customer exists, if not, produces output stating so
		- Checks to see if that customer still has DVDs on loan, if they do, produce output stating so
		- Otherwise remove that customer from the list
