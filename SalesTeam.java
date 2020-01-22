
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

/**
 * This class instantiates Sales Team objects.
 * @author Mabel
 *
 */
public class SalesTeam 
{
	//salesTeam: Holds list of names from sales team
	LinkedList<String> salesTeam = new LinkedList<String>();
	
	//Sales Team constructor
	SalesTeam()
	{
		salesTeam.add("Mabel");
		salesTeam.add("Adam");
		salesTeam.add("Dave");
		salesTeam.add("Bart");
		salesTeam.add("Matt");
		salesTeam.add("Brandon");		
	}
	
	
	/**
	 * randomly takes a name from the sales team list
	 * @return a name from the sales team list
	 */
	public String randomSalesPerson()
	{
		Random randomSalesNum = new Random();
		return salesTeam.get(randomSalesNum.nextInt(salesTeam.size()));
	}
	
	/**
	 * iterates the sales team list to return all names from sales team
	 * @return the entire list of names on the sales team 
	 */
	public String getSalesTeamList()
	{
		ListIterator<String> iterator = salesTeam.listIterator();
		String salesTeamList = "";
	
		while (iterator.hasNext())
		{
			String temp = iterator.next();
			salesTeamList = salesTeamList + temp + " \n";
		}
		
		return salesTeamList;
	}
}
