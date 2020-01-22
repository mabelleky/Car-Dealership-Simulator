import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Iterator;

/**
 * class CarDealership organizes car objects
 * @author Mabel
 *
 */
public class CarDealership 
{
	/**
	 * Instance variables used as flags to stack filter methods
	 * AWD:  Used as a flag to filter whether the cars are all-wheel drive or not
	 * electric:  Used as a flag to filter whether the car is electric or not
	 * price: Used as a flag to filter the price of a car
	 */
	private boolean AWD = false;
	private boolean electric = false;
	private boolean price = false;
	
	/**
	 * two array lists used for stacking filter options for the car list
	 */
	private ArrayList<Car> listOfCars;
	private ArrayList<Car> filteredListOfCars;
	
	
	/**
	 * accountingSystem: Accounting system object
	 * salesTeam: Sales team object
	 */
	private AccountingSystem accountingSystem = new AccountingSystem();
	private SalesTeam salesTeam = new SalesTeam();
	
	
	/**
	 * gets the accounting system
	 * @return accounting system
	 */
	public AccountingSystem getAccountingSystem()
	{
		return accountingSystem;
	}
	
	/**
	 * gets the sales team list
	 * @return sales team list
	 */
	public SalesTeam getSalesTeam()
	{
		return salesTeam;
	}
	
	//CarDealership Constructor
	CarDealership()
	{
		listOfCars = new ArrayList<Car>();
		filteredListOfCars = new ArrayList<Car>();
	}
	
	/**
	 * If any filter flags are set to true return filteredListOfCars array, if no filter flags are triggered than return original listOfCars array
	 * @return filteredListOfCars array if electric, AWD or price are set true, otherwise return listOfCars array
	 */
	public ArrayList<Car> getListOfCars()
	{
		if (electric == true || AWD == true || price == true)
		{
			return filteredListOfCars;
		}
		else
		{
		return listOfCars;
		}
	}
	
	/**
	 * Gets the boolean value to determine if listOfCars array is empty or not.  If listOfCars is empty return true, otherwise return false. 
	 * @return boolean value
	 */
	public boolean isListEmpty() 
	{
		if (listOfCars.isEmpty())
		{
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Adds ArrayList of car objects to CarDealership's listOfCars ArrayList
	 * @param newCars Arraylist of car objects
	 */
	public void addCars(ArrayList<Car> newCars)
	{
		listOfCars.addAll(newCars);
	}
	
	/**
	 * Iterates through the list of car objects and checks if the VIN entered is in the list of cars.  
	 * If there's a match, store car object in purchasedCar and remove the same car object from the list
	 * @param VIN car identification number
	 * @return car object that was bought and removes it from the list of cars
	 */
	public String buyCar(int VIN)
	{
		Iterator<Car> carObjectIterator = listOfCars.iterator();
		Car purchasedCar = null;
		
		while (carObjectIterator.hasNext())
		{
			Car eachCar = carObjectIterator.next();
			if (eachCar.getVIN() == VIN)
			{
				purchasedCar = eachCar;
				carObjectIterator.remove();
			}
		}
		
		//String that stores random sales person name
		String randomSalesPerson = salesTeam.randomSalesPerson();
		
		//generates random month and random day and stores it into variables
		Random randomM = new Random();
		int randomMonth = randomM.nextInt(12) + 1;
		Random randomD = new Random();
		int randomDay = randomD.nextInt(31) + 1;		
		
		//date: Gregorian Calendar object
		GregorianCalendar date = new GregorianCalendar();
		
		// makes sure the random day generated is appropriate for the month generated
		//return adds a new transaction into accounting system
		if (randomMonth == 2 && randomDay > 28)
		{
			randomDay -= 3;
		}
		else if ((randomMonth % 2 == 0 && randomMonth != 8) && (randomDay > 30))
		{
			randomDay -= 1;
		}
		date = new GregorianCalendar(2019, randomMonth, randomDay); 
		return accountingSystem.add(date, purchasedCar, randomSalesPerson, "BUY", purchasedCar.getPrice());
	}
	
//	/**
//	 * Returns car object to CarDealership
//	 * @param car If car was previously bought than car object returned to CarDealership
//	 */
	
	
	/**
	 * @param transactionID takes in the randomly generated transaction ID
	 * -1 indicates that there is no car transaction available for return
	 * If the transaction is not -1 then there is a car transaction available for return 
	 * New transaction id is generated, a new day within the same month is generated (day is after the bought car date) and the type of transaction is changed to RET
	 * The returned car displays the transaction information with the new data generated
	 * 
	 */
	public void returnCar(int transactionID)
	{
		if (transactionID != -1)
		{
			
		Transaction tempTransaction = accountingSystem.getTransaction(transactionID);
		listOfCars.add(tempTransaction.getSpecificCar());
		
		GregorianCalendar transactionDate = tempTransaction.getDateOfTransaction();
		
		int transactionDay = transactionDate.get(transactionDate.DAY_OF_MONTH);
		
		Random randomD = new Random();
		int randomDay = randomD.nextInt(30 - transactionDay) + transactionDay;
		
		transactionDate.set(transactionDate.DAY_OF_MONTH, randomDay);
		String salesPName = tempTransaction.getSalesPersonName();
		Car returnedCar = tempTransaction.getSpecificCar();
		double returnedSalePrice = tempTransaction.getSalePrice();
			
		Transaction returnTransaction = new Transaction(transactionDate, "RET", salesPName, returnedCar, returnedSalePrice);
		
		accountingSystem.getListOfTransactions().put(returnTransaction.getID(), returnTransaction);
		
		System.out.println(returnTransaction.display());
		}
	}
	
	/**
	 * Displays CarDealership inventory based on whether filters flag are set true or not
	 */
	public void displayInventory()
	{
		if (electric == true || AWD == true || price == true)
		{
			
			for (int index = 0; index < filteredListOfCars.size(); index++)
			{
				Car car = filteredListOfCars.get(index);
				if (listOfCars.indexOf(car) >= 0)
				{
					System.out.println(car.display());
				}
			}
		}
		else
		{
			for (int index = 0; index < listOfCars.size(); index++)
			{
				System.out.println(listOfCars.get(index).display());	
			}
		}

	}
	
	/**
	 * If filter flags: electric, AWD or price is set true than go through filteredListOfCars Array.  
	 * A temp ArrayList is created to temporarily hold the filteredListOfCars. At the end of the loop, we clear the original filteredListOfCars and assign the temp Arraylist 
	 * to filteredListOfCars ArrayList.  This is done to offset ordering issues encountered when user decides to sort before filtering.
	 * If filter flags are all false than go through listOfCars ArrayList.
	 * Set electric flag to true at the end of this method
	 */
	public void filterByElectric()
	{
		if (electric == true || AWD == true || price == true)
		{
			ArrayList<Car> temp = new ArrayList<Car>();
			for (int index = 0; index < filteredListOfCars.size(); index++)
			{
				if (filteredListOfCars.get(index).getPower() == Car.ELECTRIC_MOTOR)
				{
					temp.add(filteredListOfCars.get(index));
				}
			}
			filteredListOfCars.clear();
			filteredListOfCars.addAll(temp);
		}
		else
		{
			for (int index = 0; index < listOfCars.size(); index++)
			{
				if (listOfCars.get(index).getPower() == Car.ELECTRIC_MOTOR)
				{
					filteredListOfCars.add(listOfCars.get(index));
				}
			}
		}
		electric = true;
	}
	
	/**
	 * If filter flags: electric, AWD or price is set true than go through filteredListOfCars Array.  
	 * A temp ArrayList is created to temporarily hold the filteredListOfCars. At the end of the loop, we clear the original filteredListOfCars and assign the temp Arraylist 
	 * to filteredListOfCars ArrayList.  This is done to offset ordering issues encountered when user decides to sort before filtering.
	 * If filter flags are all false than go through listOfCars ArrayList.
	 * Set AWD flag to true at the end of this method 
	 */
	public void filterByAWD()
	{
		if (electric == true || AWD == true || price == true)
		{
			ArrayList<Car> temp = new ArrayList<Car>();
			for (int index = 0; index < filteredListOfCars.size(); index++)
			{
				if (filteredListOfCars.get(index).getAWD() == true)
				{
					temp.add(filteredListOfCars.get(index));
				}
			}
			filteredListOfCars.clear();
			filteredListOfCars.addAll(temp);
		}	
		else
		{
			for (int index = 0; index < listOfCars.size(); index++)
			{
				if (listOfCars.get(index).getAWD() == true)
				{
					filteredListOfCars.add(listOfCars.get(index));
				}
			}
		}
		AWD = true;
	}
	
	/**
	 * If filter flags: electric, AWD or price is set true than go through filteredListOfCars Array.  
	 * A temp ArrayList is created to temporarily hold the filteredListOfCars. At the end of the loop, we clear the original filteredListOfCars and assign the temp Arraylist 
	 * to filteredListOfCars ArrayList.  This is done to offset ordering issues encountered when user decides to sort before filtering.
	 * If filter flags are all false than go through listOfCars ArrayList.
	 * Set price flag to true at the end of this method
	 * @param minPrice Minimum price of car
	 * @param maxPrice Maximum price of car
	 */
	public void filterByPrice(double minPrice, double maxPrice)
	{
		if (electric == true || AWD == true || price == true)
		{
			ArrayList<Car> temp = new ArrayList<Car>();
			for (int index = 0; index < filteredListOfCars.size(); index++)
			{
				if (filteredListOfCars.get(index).getPrice() >= minPrice && filteredListOfCars.get(index).getPrice() <= maxPrice)
				{
					temp.add(filteredListOfCars.get(index));
				}
			}
			filteredListOfCars.clear();
			filteredListOfCars.addAll(temp);
		}
		else
		{
			for (int index = 0; index < listOfCars.size(); index++)
			{
				if (listOfCars.get(index).getPrice() >= minPrice && listOfCars.get(index).getPrice() <= maxPrice)
				{
					filteredListOfCars.add(listOfCars.get(index));
				}
			}
		}
		price = true;
	}
	  
	
	/**
	 * Sorts cars in CarDealership by price
	 */
	public void sortByPrice()
	{
		Collections.sort(filteredListOfCars);
		Collections.sort(listOfCars);
	}
	
	/**
	 * inner class CarSafetyRatingSorter implements Comparator for Car and sorts cars by comparing the safety rating of car 1 and car 2
	 * Returns 1 if car 1 safety rating is less than car 2 safety rating, return -1 if car 1 safety rating is greater than car 2 safety rating, otherwise return 0
	 * @return integers -1, 1 or 0 
	 * @author Mabel
	 *
	 */
	class CarSafetyRatingSorter implements Comparator<Car>
	{
		public int compare(Car car1, Car car2) 
		{
			if (car1.getSafetyRating() < car2.getSafetyRating()) 
			{
				return 1;
			}
			else if (car1.getSafetyRating() > car2.getSafetyRating()) 
			{
				return -1;
			}
			else 
			{ 
				return 0;
			}
		}
	}
	
	/**
	 * Sorts cars in CarDealership by their safety rating
	 */
	public void sortBySafetyRating()
	{
		Collections.sort(filteredListOfCars, new CarSafetyRatingSorter());
		Collections.sort(listOfCars, new CarSafetyRatingSorter());
	}
	
	/**
	 * inner class MaxRangeSorter implements Comparator for Car and sorts cars by comparing the max range of car1 and car 2
	 * Returns 1 if car 1 max range is less than car 2 max range, return -1 if car 1 max range is greater than car 2 max range, otherwise return 0
	 * @return integers -1, 1 or 0 
	 * @author Mabel
	 *
	 */
	class MaxRangeSorter implements Comparator<Car>
	{
		public int compare(Car car1, Car car2) 
		{
			if (car1.getMaxRange() < car2.getMaxRange()) 
			{
				return 1;
			}
			else if (car1.getMaxRange() > car2.getMaxRange()) 
			{
				return -1;
			}
			else 
			{
				return 0;
			}
		}
	}
	
	/**
	 * Sorts cars in CarDealership by their max range
	 */
	public void sortByMaxRange()
	{
		Collections.sort(filteredListOfCars, new MaxRangeSorter());
		Collections.sort(listOfCars, new MaxRangeSorter());
	}
	
	/**
	 * clears all filters on filteredListOfCars ArrayList and sets all filter flags to false
	 */
	public void filtersClear()
	{
		AWD = false;
		electric = false;
		price = false;
		filteredListOfCars.clear();
	}
}
