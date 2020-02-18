/* Complete this class to implement a fully functional sparse table. Read the comments to determine what each aspect of the class is supposed to do.
You must add any additional features (methods, references) which may aid you in your
task BUT you are not allowed to remove or change the names or properties of any of the features you were given.

Note: you are NOT allowed to use any 2D or 3D arrays to simulate the sparse table functionality. Doing so will result in a mark of zero.

Importing Java's built in data structures will result in a mark of zero. Only the use of native 1-dimensional is are allowed. */

public class Calendar
{
	public Calendar()
	{
		/*You may implement this constructor to suit your needs, or you may add additional constructors.  This is the constructor which will be used for marking*/ 
	}
	
	/*Insertion*/

	public void addItem(int day, String month, String description, String duration, int priority)
	{
		/* Insert an Item at the given day and month combination according to priority. Intialize the Item with the remainder of the parameters.
		
		Duplicate Items are allowed.*/
	}
	
	/*Deletion*/
	
	public Item deleteItem(int day, String month, String description)
	{
		/*Delete the first Item at the given day and month combination with the given description and return the deleted Item.
		If no such Item exists, return null.*/
				
		return null;
	}
	
	public void deletePriorityItem(int day, String month, int priority)
	{
		/*Delete all Items of a given priority at the given day and month combination.*/
	}
	
	public void deleteItems(int day, String month)
	{
		/*Delete all items at the given day and month combination.*/				
	}
	
	/*Clearing Methods*/
	public void clearMonth(String month)
	{
		/*All items for the given month should be deleted.
		If the month has no Items, simply do nothing.*/
	}
	
	public void clearDay(int day)
	{
		/*All items for the given day should be deleted.
		If the day has no Items, simply do nothing.*/
	}
	
	public void clearYear()
	{
		/*Delete all Items from the calendar.*/
	}
	
	
	/*Query methods*/
	public Item getItem(int day, String month)
	{
		/*Return the head Item of the day and month. If no such Item exists, return null*/
		
		return null;
	}
	
	public Item getMonthItem(String month)
	{
		/*Return the head Item for the month passed as a parameter.
		If no such Item exists, return null*/
		
		return null;
	}
	
	public Item getDayItem(int day)
	{
		/*Return the head Item for the day passed as a parameter.
		If no such Item exists, return null*/
		
		return null;
	}	
}
