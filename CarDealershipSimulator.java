//import packages
import java.util.ArrayList;
import java.util.Scanner;
import java.util.NoSuchElementException;


public class CarDealershipSimulator 
{
  public static void main(String[] args)
  {
	  // Create a CarDealership object
	  CarDealership CD = new CarDealership(); 
	  
	  // Then create an (initially empty) array list of type Car
	  ArrayList<Car> carList = new ArrayList<Car>();

	  // Then create some new car objects of different types
	  // See the cars file for car object details
	  Car toyota = new Car("Toyota", "blue", Car.SEDAN, Car.GAS_ENGINE, 9.5, 500, false, 25000, 4);
	  Car honda = new Car("Honda", "red", Car.SPORTS, Car.GAS_ENGINE, 9.2, 450, false, 30000, 4);
	  Car kia = new Car("Kia", "white", Car.MINIVAN, Car.GAS_ENGINE, 9.7, 550, false, 20000, 4);
	  Car bmw = new Car("BMW", "black", Car.SEDAN, Car.GAS_ENGINE, 9.6, 600, true, 55000, 4);
	  ElectricCar telsa = new ElectricCar("Tesla", "red", Car.SEDAN, Car.ELECTRIC_MOTOR, 9.1, 425, true, 85000, 4, 30, "Lithium");
	  Car chevy = new Car("Chevy", "red", Car.MINIVAN, Car.GAS_ENGINE, 9.25, 475, false, 40000, 4);
	  ElectricCar chevyvolt = new ElectricCar("ChevyVolt", "green", Car.SEDAN, Car.ELECTRIC_MOTOR, 8.9, 375, true, 37000, 4, 45, "Lithium");
	  Car bentley = new Car("Bentley", "black", Car.SEDAN, Car.GAS_ENGINE, 9.8, 575, false, 150000, 4);
	  ElectricCar nissanleaf = new ElectricCar("NissanLeaf", "green", Car.SEDAN, Car.ELECTRIC_MOTOR, 8.8, 325, true, 32000, 4, 55, "Lithium");
	  
	  // Add the car objects to the array list
	  carList.add(toyota);
	  carList.add(honda);
	  carList.add(kia);
	  carList.add(bmw);
	  carList.add(telsa);
	  carList.add(chevy);
	  carList.add(chevyvolt);
	  carList.add(bentley);
	  carList.add(nissanleaf);
	  
    		  
	  // The ADD command should hand this array list to CarDealership object via the addCars() method	  
	  // Create a scanner object 
	  // while the scanner has another line
	  //    read the input line
	  //    create another scanner object (call it "commandLine" or something) using the input line instead of System.in
	  //    read the next word from the commandLine scanner 
      //	check if the word (i.e. string) is equal to one of the commands and if so, call the appropriate method via the CarDealership object  
	  //String commandLine = scanner.nextInt();	 
	 
	  /**
	   * Scanner object to detect user input
	   * temp variable used to indicate whether customer has bought a car from CarDealership
	   */
	  Scanner scanner = new Scanner(System.in);
	  Car temp = null;
	 
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
			  * If user enters "L" check if ArrayListin CarDealership object is empty, if empty equals true than print statement, otherwise display inventory
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
			 * else: try and check if there is an integer in the input line after BUY. e.g. BUY 3
			 * catch NoSuchElementException error if BUY is missing integer parameters
			 * catch IndexOutOfBoundsException error when integer value entered is negative. e.g BUY -1
			 * 
			 * if (boughtCar! null): The check for an integer in the input line will also be assigned to a boughtCar variable to check if bought Car is null.  
			 * This is to prevent the broughtCar stored in the system to be erased when user tries to buy a car number that is bigger than the existing list.  
			 * e.g. Cars inventory are listed from 0 to 7.  User buys car 7 with 'BUY 7' command and car 7 info is stored in the temp variable for if customer wants to return the car, they can.  (Unless another car existing in the inventory is bought than it overrides car 7 info)
			 * However, if car 7 is stored in the temp variable and user enters 'BUY 10' which car does not exist in the inventory than temp will be set to null.
			 * Checking whether boughtCar is Null will prevent the temp variable to be set to null if user tries to buy a car that does not exist in the inventory so the previous car
			 * can still be returned.
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
						 Car boughtCar = CD.buyCar(Integer.parseInt(commandLine.next())); 
						 if (boughtCar != null) 
						 {
							 temp = boughtCar;
						 }
					 }
					 catch (NoSuchElementException error)
					 {
						System.out.println("Missing parameters.  Please try again."); 
					 }					 
					 catch (IndexOutOfBoundsException error)
					 {
						 System.out.println("Invalid entry.  Please try again.");
					 }
				 }

				 break;
				 
			/**
			 * check if a car was previously bought by checking the car bought in the temp variable.  If temp variable is null then print statement.
			 * Otherwise return the car that was previously bought and set temp variable to null. 
			 */
			 case "RET": 
				 if (temp == null)
				 {
					 System.out.println("No car to return.");
				 }
				 else
				 {
				 CD.returnCar(temp);
				 temp = null;
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