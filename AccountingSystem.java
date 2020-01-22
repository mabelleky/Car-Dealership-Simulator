
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * This class organizes Transaction objects.
 * @author Mabel
 *
 */
public class AccountingSystem {
	
	
	/**
	 * listOfTransaction: Holds the list of transactions created
	 */
	private HashMap<Integer, Transaction> listOfTransactions = new HashMap<Integer, Transaction>();
	
	/**
	 * @param date Randomly generated date
	 * @param car Car object
	 * @param salesPerson Sales person name
	 * @param type is the type of transaction
	 * @param salePrice is the sale price the car was sold at
	 * @return the added transaction into the list of transactions and displays the list of transactions
	 */
	public String add(GregorianCalendar date, Car car, String salesPerson, String type, double salePrice)
	{
		Transaction newTransaction = new Transaction(date, type, salesPerson, car, salePrice);
		
		listOfTransactions.put(newTransaction.getID(), newTransaction);
		
		return newTransaction.display();
	}
	
	/**
	 * gets the list of transactions
	 * @return list of transactions
	 */
	public HashMap<Integer, Transaction> getListOfTransactions()
	{
		return listOfTransactions;
	}
		
	/**
	 * @param id takes in the transaction id
	 * @return the transaction with the matching transaction id in the list of transactions
	 */
	public Transaction getTransaction(int id)
	{
		return listOfTransactions.get(id);
	}
	
	
}
