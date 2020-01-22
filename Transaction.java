
import java.util.Random;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

/**
 * This class instantiates Transaction objects.
 * @author Mabel
 */
public class Transaction {

	/**
	 * id: Holds the transaction id 
	 * dateOfTransaction: Holds the transaction date when the car is bought
	 * typeOfTransaction: Holds the type of transaction taking place, i.e. BUY or RET
	 * salesPersonName: Holds the sales person name associated with the transaction id generated
	 * specificCar: Holds information of the Car that was bought
	 * salePrice: Holds information of the price the Car was bought at
	 */
	private int id;
	private GregorianCalendar dateOfTransaction;
	private String typeOfTransaction;
	private String salesPersonName;
	private Car specificCar;
	private double salePrice;

	/**
	 * Transaction constructor
	 * Transaction id is randomly generated for vehicle bought
	 * @param dateOfTransaction Date the car is bought
	 * @param typeOfTransaction Two types of transaction: BUY or RET
	 * @param salesPersonName Sales person name associated with the bought car
	 * @param specificCar Car that is bought associated with the transaction id generated
	 * @param price the car was bought at
	 */
	Transaction(GregorianCalendar dateOfTransaction, String typeOfTransaction, String salesPersonName, Car specificCar, double salePrice)
	{
		Random randomTransactionID = new Random();
		this.id = randomTransactionID.nextInt(99) + 1;
		
		this.dateOfTransaction = dateOfTransaction;
		this.typeOfTransaction = typeOfTransaction;
		this.salesPersonName = salesPersonName;
		this.specificCar = specificCar;
		this.salePrice = salePrice;
	}
	
	/**
	 * gets the transaction ID
	 * @return transaction ID
	 */
	public int getID()
	{
		return id;
	}
	
	/**
	 * gets the date of transaction
	 * @return date of the transaction
	 */
	public GregorianCalendar getDateOfTransaction()
	{
		return dateOfTransaction;
	}
	
	/**
	 * gets the type of transaction, e.g. BUY or RET
	 * @return type of transaction
	 */
	public String getTypeOfTransaction()
	{
		return typeOfTransaction;
	}
	
	/**
	 * gets the sales person name who is associated with the transaction
	 * @return the sales person name who 
	 */
	public String getSalesPersonName()
	{
		return salesPersonName;
	}
	
	/**
	 * gets the price the car was sold at
	 * @return price of car was sold at
	 */
	public double getSalePrice()
	{
		return salePrice;
	}
	
	/**
	 * set the price the car was sold at
	 * @param salePrice takes in the price the car was sold at
	 */
	public void setSalePrice(double salePrice)
	{
		this.salePrice = salePrice;
	}
	
	/**
	 * get the information of the car object
	 * @return car object
	 */
	public Car getSpecificCar()
	{
		return specificCar;
	}
	
	/**
	 * @return string listing details of the transaction
	 */
	public String display()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, yyyy");
		
		return "ID: " + id + " " + sdf.format(dateOfTransaction.getTime()) + " " + typeOfTransaction + " " + "SalesPerson: " + salesPersonName + " " + "Car: " + specificCar.display(); 
	}
	
}
