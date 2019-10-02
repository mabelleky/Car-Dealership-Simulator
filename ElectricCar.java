/**
 * This class instantiate ElectricCar objects.  Child class of class Car.  
 * @author Mabel
 *
 */
public class ElectricCar extends Car 
{
	/**
	 * rechargeTime: car's recharge time held in minutes
	 * batteryType: car's battery type. e.g. Lithium ion
	 */
	private int rechargeTime;
	private String batteryType;

	/**
	 * 
	 * @param mfr Electric car's manufacturer name
	 * @param color Electric car's color 
	 * @param model Integer representing the electric car's model
	 * @param power Type of power the car uses. Electric car would have to be electric.
	 * @param safetyRating Electric car's safety rating
	 * @param maxRange Electric car's max range
	 * @param AWD Determines if the electric car is all-wheel drive or not
	 * @param price Electric car's price
	 * @param numWheels Number of wheels of the electric car
	 * @param rechargeTime Electric car's recharge time
	 * @param batteryType Electric car's battery type
	 */
	ElectricCar(String mfr, String color, int model, int power, double safetyRating, int maxRange, boolean AWD, double price, int numWheels, int rechargeTime, String batteryType)
	{
		super(mfr, color, model, power, safetyRating, maxRange, AWD, price, numWheels);
		this.rechargeTime = rechargeTime;
		this.batteryType = batteryType;
	}
	/**
	 * Gets the electric car's recharge time in minutes
	 * @return electric car's recharge time
	 */
	public int getRechargeTime()
	{
		return rechargeTime;
	}
	
	/**
	 * Sets electric car's recharge time in minutes
	 * @param rechargeTime recharge time of electric car
	 */
	public void setRechargeTime(int rechargeTime)
	{
		this.rechargeTime = rechargeTime;
	}
	
	/**
	 * Gets the electric car's battery type
	 * @return battery type of electric car
	 */
	public String getBatteryType()
	{
		return batteryType;
	}
	
	/**
	 * Sets electric car's battery type
	 * @param batteryType battery type of electric car
	 */
	public void setBatteryType(String batteryType)
	{
		this.batteryType = batteryType;
	}
	
	/**
	 *
	 *@returns string listing details of Vehicle class, Car class, and ElectricCar class. 
	 */
	public String display()
	{
		return super.display() + "  EL, BAT: " + batteryType + " RCH: " + rechargeTime;
	}
}


