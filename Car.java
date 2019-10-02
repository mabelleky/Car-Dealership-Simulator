/**
 * This class instantiates Car objects.  class Car is the child class of class Vehicle and implements the Comparable Interface.
 * @author Mabel
 *
 */
public class Car extends Vehicle implements Comparable<Car>
{
	/**
	 * Creating static string constants for car models.
	 */
	public static final int SEDAN = 0;
	public static final int SUV = 1;
	public static final int SPORTS = 2;
	public static final int MINIVAN = 3;
	
	/**
	 * Initializing instance variables for the Car class
	 * model: Holds the integer the car model name is assigned to. e.g. 0 represents SEDAN
	 * maxRange: Holds the car's max range it can travel
	 * safetyRating: Holds the car's safety rating
	 * AWD:  Holds a boolean checking if the car is all-wheel drive
	 * price: Hold the price of the car
	 */
	private int model;
	private int maxRange;
	private double safetyRating;
	private boolean AWD;
	private double price;
	
	/**
	 * 
	 * @param mfr Name of the car manufacturer
	 * @param color The car's color
	 * @param model The integer representing the car's model
	 * @param power Type of power the car uses e.g. Gas or electric
	 * @param safetyRating The car's safety rating 
	 * @param maxRange The car's max range
	 * @param AWD Determines if the car is all-wheel drive or not
	 * @param price The car's price
	 * @param numWheels Number of wheels of the car
	 */
	Car(String mfr,String color, int model, int power, double safetyRating, int maxRange, boolean AWD, double price, int numWheels)
	{
		super(mfr, color, power, numWheels);
		this.model = model;
		this.maxRange = maxRange;
		this.safetyRating = safetyRating;
		this.AWD = AWD;
		this.price = price;
	}
	
	/**
	 * Gets the integer representing the car's model
	 * @return assigned integer for the car's model
	 */
	public int getModel()
	{
		return model;
	}
//	did not create setter for model since static string constants are created for car models
	
	/**
	 * Gets the car's max range
	 * @return car's max range
	 */
	public int getMaxRange()
	{
		return maxRange;
	}
	
	/**
	 * Sets the car's max range
	 * @param maxRange car's max range
	 */
	public void setMaxRange(int maxRange)
	{
		this.maxRange = maxRange;
	}
	
	/**
	 * Gets the car's safety rating
	 * @return car's safety rating
	 */
	public double getSafetyRating()
	{
		return safetyRating;
	}
	
	/**
	 * Sets the car's safety rating
	 * @param safetyRating car's safety rating
	 */
	public void setSafetyRating(double safetyRating)
	{
		this.safetyRating = safetyRating;
	}
	
	/**
	 * Gets if the car is all-wheel drive or not
	 * @return true or false check if car is all-wheel drive
	 */
	public boolean getAWD()
	{
		return AWD;
	}
	
	/**
	 * Sets if the car is all-wheel drive or not
	 * @param AWD sets true or false for all-wheel drive
	 */
	public void setAWD(boolean AWD)
	{
		this.AWD = AWD;
	}
	
	/**
	 * Gets the car's price
	 * @return car's price
	 */
	public double getPrice()
	{
		return price;
	}
	
	/**
	 * Sets the car's price
	 * @param price of the car
	 */
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	/**
	 * modelNameToString() is a helper method to convert model from integer to String (model name)
	 * @return "SEDAN" if 0, "SUV" if 1, "SPORTS" if 2, "MINIVAN" if 3, "New Model?" if all other cases. 
	 */
	public String modelNameToString()
	{
		switch(model)
		{
		case 0:
			return "SEDAN";
		case 1:
			return "SUV";
		case 2:
			return "SPORTS";
		case 3:
			return "MINIVAN";
		default:
			return "New Model?";
		}
	}
	
	/**
	 *@returns string listing details of Vehicle class and Car class 
	 */
	public String display()
	{
		return super.display() + " " + modelNameToString() + " $" + price + " SF: " + safetyRating + " RNG: " + maxRange;
	
	}
	
	
	/**
	 * Compares this car object with other car object on whether they are equal
	 * returns true if the objects are equal and false if they are not
	 * @param other the other car object
	 * @return boolean value
	 */
	public boolean equals(Object other)
	{
		Car otherCar = (Car) other;
		if (super.equals(other) == true)
		{
			if (this.model == otherCar.model && this.AWD == otherCar.AWD)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Compares this car price with other car price
	 * Returns -1 if this price is less than other price, return 1 if this price is greater than other price, otherwise return 0
	 * @return integers -1, 1 or 0 
	 */
	public int compareTo(Car otherCar)
	{
		if (this.price < otherCar.price)
		{
			return -1;
		}
		else if (this.price > otherCar.price)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
}
