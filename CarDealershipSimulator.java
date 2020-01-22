import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


/**
 * Simulates car dealership
 * @author Mabel
 *
 */
public class CarDealershipSimulator 
{
public static void main(String[] args)
  {
	  // Create a CarDealership object
	  CarDealership CD = new CarDealership(); 
	  
	  // Create a file reader and then create an (initially empty) array list of type Car
	  FileReader reader = new FileReader();

	  ArrayList<Car> carList = reader.getScannedCars();

	  if (carList.isEmpty()) {

		  System.out.println("Failed to load data.");

		  return;

	  }
	   
	  /**
	   * Scanner object to detect user input
	   * boughtCarTransactionID: variable used to indicate whether customer has bought a car from CarDealership
	   */
	  Scanner scanner = new Scanner(System.in);
	  int boughtCarTransactionID = -1;
	 
	  /**
	   * First while loop checks input line and assign it to String variable commandInput
	   * create new scanner object passing in commandInput, which is the input line
	   * Nested while loops checks if there is another token in the input line.  
	   * String variable actualCommand stores the next complete token from scanner
	   * actualCommand inputs are all automatically capitalized
	   * actualCommand passed through switch statements to check for scenarios
	   */
	 while (scanner.hasNextLine())
	 {
		 String commandInput = scanner.nextLine();
		 Scanner commandLine = new Scanner(commandInput);
		 
		 while (commandLine.hasNext())
		 {
			 String actualCommand = commandLine.next();

			 switch(actualCommand.toUpperCase())
			 {
			 
			 /**
			  * If user enters "L" check if ArrayList in CarDealership object is empty, if empty equals true than print statement, otherwise display inventory
			  */
			 case "L":
				 if (CD.isListEmpty() == true)
				 {
					 System.out.println(" *tumbleweeds*  Please add cars. ");
				 }
				 else
				 {
					 CD.displayInventory();					 
				 }
				 break;
	
			/**
			 * Close all scanners before exiting the system
			 */
			 case "Q":
				 System.out.println("Exiting the system. Goodbye.");
				 scanner.close();
				 commandLine.close();
				 System.exit(0);
				 break; 
			
			/**
			 * If user enters "BUY" check if ArrayList in CarDealership object is empty, if empty equals true than print statement
			 * else: checks if there is an integer in the input line after BUY. e.g. BUY 3 
			 * Takes the car object that's being purchased and put it into a String
			 * Takes the transaction number after "ID:" from the string and store the bought car transaction into boughtCarTransactionID variable
			 * 
			 * catch NoSuchElementException error if BUY is missing integer parameters
			 * catch IndexOutOfBoundsException error when integer value entered is negative. e.g BUY -1
			 * catch NullPointException error
			 * catch NumberFOrmatException error if symbols are entered after BUY. e.g. BUY %$#& or BUY 6$
			 *
			 */ 
			 case "BUY":
				 
				 if (CD.isListEmpty() == true)
				 {
					 System.out.println("No cars, just tumbleweeds here.");
				 }
				 else
				 {	 
					 try
					 {				 
						 String purchasedCar = CD.buyCar(Integer.parseInt(commandLine.next()));
						 Scanner purchasedCarScanner = new Scanner(purchasedCar);
						 
						 while (purchasedCarScanner.hasNext())
						 {
							 String purchasedCarDetails = purchasedCarScanner.next();
							 if (purchasedCarDetails.equals("ID:"))
							 {
								 boughtCarTransactionID = Integer.parseInt(purchasedCarScanner.next());
								 break;
							 }
						 }
						 System.out.println(purchasedCar);
						 purchasedCarScanner.close();
					 }
					 catch (NoSuchElementException error)
					 {
						System.out.println("Missing parameters.  Please try again."); 
					 }					 
					 catch (IndexOutOfBoundsException error)
					 {
						 System.out.println("Invalid entry.  Please try again.");
					 }
					 catch (NullPointerException error)
					 {
						 System.out.println("Invalid entry.  Please try again.");
					 }
					 catch (NumberFormatException error)
					 {
						 System.out.println("Invalid entry.  Please try again.");
					 }
				 }

				 break;
				 
			/**
			 * -1 means there is no car transaction information stored in bougthCarTransactionID
			 * check if a car was previously bought by checking the car bought in the boughtCarTransactionID variable.  
			 * If temp variable is -1 then print statement.
			 * Otherwise return the car that was previously bought and set boughtCarTransactionID variable to -1. 
			 */
			 case "RET": 
				 if (boughtCarTransactionID == -1)
				 {
					 System.out.println("No car to return.");
				 }
				 else
				 {
				 CD.returnCar(boughtCarTransactionID);
				 boughtCarTransactionID = -1;
				 }
				 break;
			
			/**
			 * if command does not have a next token after SALES than go through the list of transactions and print them all
			 * if there is a token after SALES there is a switch for specific inputs
			 * anything outside of these defined inputs will print a statement indicating that it is invalid	 			 
			 */
			case "SALES":
				if (!commandLine.hasNext()) 
				{
					HashMap<Integer, Transaction> allTransactions = CD.getAccountingSystem().getListOfTransactions();
					 
					for (Map.Entry<Integer, Transaction> entry: allTransactions.entrySet())
					{
						System.out.println(entry.getValue().display());
					}	
				} 
				
				else
				{
					String nextCommand = commandLine.next().toUpperCase();
					
					switch (nextCommand)
					{	
					
					/**
					 * if command is SALES TEAM, print out the sales team list
					 */
					case "TEAM":
						System.out.println(CD.getSalesTeam().getSalesTeamList());
						break;
					
					/**
					 * if command is SALES TOPSP then print out the top sales person
					 * takes the sales persons name from the transactions and put them into a HashMap
					 */
					case "TOPSP":
						
						HashMap<Integer, Transaction> allTransactions = CD.getAccountingSystem().getListOfTransactions();
						String individualSalesName = "";
						Map<String, Integer> carsSoldByEachSalesPerson = new HashMap<String, Integer>();
						
						
						for (Map.Entry<Integer, Transaction> entry: allTransactions.entrySet())
						{
							individualSalesName = entry.getValue().getSalesPersonName();
							Integer counter = carsSoldByEachSalesPerson.get(individualSalesName);
							
							if (counter == null)
							{
								counter = 0;
							}
							if (counter != null)
							{
								counter++;
							}
							carsSoldByEachSalesPerson.put(individualSalesName, counter);
						}
						
						
						/**
						 * Checks if there are multiple top sales person
						 */
						int topSales = 0;
						String topSalesPerson = "";						

						for (String key: carsSoldByEachSalesPerson.keySet())
						{			
							if (carsSoldByEachSalesPerson.get(key) > topSales)
							{	
								topSales = carsSoldByEachSalesPerson.get(key);
								topSalesPerson = key;
							}
							if (carsSoldByEachSalesPerson.get(key) == topSales)
							{
								if (topSalesPerson.equals(key))
								{
									topSalesPerson = "\n" + key;
								}
								else
								{
									topSalesPerson = topSalesPerson + "\n" + key;
								}
							}	 
						}
						System.out.println(topSalesPerson + "\n" + topSales);
						break;
										

					/**
					 * First for loop goes through all transactions and calculates the average total sales, total cars sold and total cars returned
					 * total $ sales will reduce if any cars are returned
					 * total number of cars sold and total return cars does not change
					 * 
					 * Second for loop and latter half calculates and prints the monthly figures 
					 * if there are transactions happening within that month and the month with most sales 
					 * 					 
					 */
					case "STATS":
						
						HashMap<Integer, Transaction> allTransaction = CD.getAccountingSystem().getListOfTransactions();
						HashMap<Integer, Double> monthlyTotals = new HashMap<Integer, Double>();
						HashMap<Integer, Integer> monthlyQuantities = new HashMap<Integer, Integer>();
						HashMap<Integer, Double> returnMonthlyTotals = new HashMap<Integer, Double>();
						HashMap<Integer, Integer> returnMonthlyQuantities = new HashMap<Integer, Integer>();
						
						int totalSales = 0;
						double totalValueSold = 0;
						int totalReturned = 0;
						
						for (Map.Entry<Integer, Transaction> entry: allTransaction.entrySet())
						{
							Transaction trans = entry.getValue();
							int transactionMonth = trans.getDateOfTransaction().get(Calendar.MONTH);
							
							if (trans.getTypeOfTransaction() == "BUY")
							{
								totalValueSold += trans.getSalePrice();
								totalSales ++;
								
								//monthly sales prices
								if (monthlyTotals.get(transactionMonth) == null)
								{
									monthlyTotals.put(transactionMonth, trans.getSalePrice());
								}
								else
								{
									monthlyTotals.put(transactionMonth, monthlyTotals.get(transactionMonth) + trans.getSalePrice());
								}
								
								//monthly sales
								if (monthlyQuantities.get(transactionMonth) == null)
								{
									monthlyQuantities.put(transactionMonth, 1);
								}
								else
								{
									monthlyQuantities.put(transactionMonth, monthlyQuantities.get(transactionMonth) + 1);
								}
							}
							else
							{
								//monthly sales prices
								if (returnMonthlyTotals.get(transactionMonth) == null)
								{
									returnMonthlyTotals.put(transactionMonth, trans.getSalePrice());
								}
								else
								{
									returnMonthlyTotals.put(transactionMonth, returnMonthlyTotals.get(transactionMonth) + trans.getSalePrice());
								}
								
								//monthly sales
								if (returnMonthlyQuantities.get(transactionMonth) == null)
								{
									returnMonthlyQuantities.put(transactionMonth, 1);
								}
								else
								{
									returnMonthlyQuantities.put(transactionMonth, returnMonthlyQuantities.get(transactionMonth) + 1);
								}
								
								totalValueSold -= trans.getSalePrice();
								totalReturned ++;
							}
						}
						
						System.out.println("Total Sales (2019): " + totalValueSold + 
								"\nTotal Cars sold (2019): " + totalSales +
								"\nTotal Cars returned: " + totalReturned + "\n\nMonthly figures: \n");
						
						int highestSales= 0;
						int highestSalesMonth = 0;
						
						for (Map.Entry<Integer, Double> entry: monthlyTotals.entrySet())
						{
							int quantity = monthlyQuantities.get(entry.getKey());
							
							if (quantity > highestSales)
							{
								highestSales = quantity;
								highestSalesMonth = entry.getKey();
							}
							
							double adjustedTotal = entry.getValue();
							double adjustedQuantity = quantity;
							
							if (returnMonthlyTotals.get(entry.getKey()) != null)
							{
								adjustedTotal -= returnMonthlyTotals.get(entry.getKey());
								adjustedQuantity -=returnMonthlyQuantities.get(entry.getKey());
							}
							
							String output = "0";
							
							if (adjustedQuantity > 0) 
							{
								output = Double.toString(adjustedTotal / adjustedQuantity);
							}
							
							Double.toString(adjustedTotal / adjustedQuantity);
							System.out.println("Month: " + entry.getKey() + " Average sales: " + output);
						}
						
						System.out.println("Highest sales month (in terms of # cars): " + highestSalesMonth);
						
						break;
							
					 
					default:
						try 
						{
							int month = Integer.parseInt(nextCommand);
							if (month < 0 || month > 11)
							{
								System.out.println("Not a valid month");
								break;
							}
							
							HashMap<Integer, Transaction> allTrans = CD.getAccountingSystem().getListOfTransactions();
							String ret = "";
							for (Map.Entry<Integer, Transaction> entry: allTrans.entrySet())
							{
								if (entry.getValue().getDateOfTransaction().get(Calendar.MONTH) == month) 
								{
									ret += entry.getValue().display() + "\n";
								}
							}
							if (ret == "")
							{
								System.out.println("No sales that month");
							}
							else 
							{
								System.out.println(ret);
							}
							
						}
						
						catch (NumberFormatException error) 
						{
							System.out.println("Not a valid entry.");
						}
					}
				}		
				break;
	
			/**
			 * add list of cars into ArrayList of CarDealership object
			 */
			 case "ADD":
				 CD.addCars(carList);
			 	 break;
			 	 
			 /**
			  * Sort list of cars by price range
			  */
			 case "SPR":
				 CD.sortByPrice();
			 	 break;
			 
			 /**
			  * Sort list of cars by safety rating
			  */
			 case "SSR":
				 CD.sortBySafetyRating();
			 	 break;
			 
			 /**
			  * Sort list of cars by car's max range 
			  */
			 case "SMR":
				 CD.sortByMaxRange();
			 	 break;
			 
			 /**
			  * Sets price filter
			  * try and check if correct inputs are entered, Correct input example is FPR 20000 85000.
			  * catch NoSuchElementException error if missing proper integer parameters
			  * catch NumberFormatException error if improper data type is entered, e.g. FPR 250000 FFFFF
			  */
			 case "FPR":
				 try
				 {
					 CD.filterByPrice(Double.parseDouble(commandLine.next()), Double.parseDouble(commandLine.next()));
				 } 
				 catch (NoSuchElementException error) 
				 {
					 System.out.println("Missing price(s).  Both minimum & maximum prices required.");
				 }
				 catch (NumberFormatException error)
				 {
					 System.out.println("Valid numbers required");
				 }
			 	 break;
			 
			 /**
			  * Sets electric filter
			  */
			 case "FEL":
				 CD.filterByElectric();
			 	 break;
			 	 
			 /**
			  * Sets all-wheel drive filter
			  */
			 case "FAW":
				 CD.filterByAWD();
			 	 break;
			 
			 /**
			  * Clear all filters
			  */
			 case "FCL":
				 CD.filtersClear();
			 	 break;
			
			 /**
			  * if input command is invalid
			  */
			 default:
				 System.out.println("Invalid entry. Please try again.");
				 
		 }
	
		 }
	 }
	 
	 
  }
}