/**
 * This class instantiates Vehicle objects
 * @author Mabel
 *
 */
public class Vehicle 
{
	/**
	 * Creating static string constants for Vehnicle's power type.
	 */
	public static final int ELECTRIC_MOTOR = 0;
	public static final int GAS_ENGINE = 1;
	
	/**
	 * Initializing instance variables for class Vehicle
	 * mfr: Holds the manufacturer name
	 * color: Holds the color of the vehicle
	 * power: Holds the power the Vehicle uses, i.e. Gas or Electric
	 * numWheels: Holds the number of wheels the vehicle has
	 */
	private String mfr;
	private String color;
	private int power;
	private int numWheels;

	/**
	 * 
	 * @param mfr Vehicle's manufacture name
	 * @param color Vehicle's color
	 * @param power Power vehicle uses, Gas or Electric (Static string constant)
	 * @param numWheels Number of wheels vehicle has
	 */
	Vehicle(String mfr, String color, int power, int numWheels)
	{
		this.mfr = mfr;
		this.color = color;
		this.power = power;
		this.numWheels = numWheels;
	}
	
	/**
	 * Gets vehicle's manufacturer name
	 * @return vehicle's manufacturer
	 */
	public String getMFR()
	{
		return mfr;
	}
	
	/**
	 * Sets vehicle's manufacture name
	 * @param mfr vehicle's manufacturer
	 */
	public void setMFR(String mfr)
	{
		this.mfr = mfr;
	}
	
	/**
	 * Gets vehicle's color
	 * @return vehicle's color
	 */
	public String getColor()
	{
		return color;
	}
	
	/**
	 * Sets vehicle's color
	 * @param color vehicle's color
	 */
	public void setColor(String color)
	{
		this.color = color;
	}
	
	/**
	 * Gets vehicle's power
	 * @return an integer representing vehicle's power
	 */
	public int getPower()
	{
		return power;
	}
	
//	did not create setter for model since static string constants are created for car models
	
	/**
	 * Gets number of wheels vehicle has
	 * @return the number of wheels of vehicle has
	 */
	public int getNumWheels()
	{
		return numWheels;
	}
	
	/**
	 * Sets number of wheels for vehicle has
	 * @param numWheels Number of wheels vehicle has
	 */
	public void setNumWheels(int numWheels)
	{
		this.numWheels = numWheels;
	}
	
	/**
	 * Compares this vehicle with other vehicle and see whether they are equal
	 * Returns true if they are equal and returns false if not equal
	 * @param other The other vehicle object
	 * @return boolean value 
	 */
	public boolean equals(Object other)
	{
		Vehicle otherVehicle = (Vehicle) other;
		if (this.mfr == otherVehicle.mfr && this.power == otherVehicle.power && this.numWheels == otherVehicle.numWheels)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	/**
	 * 
	 * @return string listing details of Vehicle class
	 */
	public String display()
	{
		return mfr + " " + color;
	}
}

