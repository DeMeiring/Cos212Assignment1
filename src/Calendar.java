/* Complete this class to implement a fully functional sparse table. Read the comments to determine what each aspect of the class is supposed to do.
You must add any additional features (methods, references) which may aid you in your
task BUT you are not allowed to remove or change the names or properties of any of the features you were given.

Note: you are NOT allowed to use any 2D or 3D arrays to simulate the sparse table functionality. Doing so will result in a mark of zero.

Importing Java's built in data structures will result in a mark of zero. Only the use of native 1-dimensional is are allowed. */

public class Calendar
{

	private Item[] dayArr = new Item[31];
	private Item[] monthArr = new Item[12];
	public Calendar()
	{
		/*You may implement this constructor to suit your needs, or you may add additional constructors.  This is the constructor which will be used for marking*/
		createDay();
		createMonth();
	}
	
	/*Insertion*/

	public void addItem(int day, String month, String description, String duration, int priority)
	{
		/* Insert an Item at the given day and month combination according to priority. Intialize the Item with the remainder of the parameters.
		
		Duplicate Items are allowed.*/
		//==================================Set Indexes===========================================================
		int[] Indexes = findIndexesArray(day,month);
		int monthIndex = Indexes[0];
		int dayIndex = Indexes[1];
		//==================================End of Set Indexes===========================================================
		//==================================create new Item==============================================================
		Item newItem = new Item();
		newItem.setDescription(description);
		newItem.setDuration(duration);
		newItem.setPriority(priority);
		//==================================End of create new Item==============================================================
		if(monthArr[monthIndex].down==null && dayArr[dayIndex].right==null){	//there does not exist an item at that day of the month
			monthArr[monthIndex].down=newItem;
			dayArr[dayIndex].right = newItem;
		}

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
	//============================================helper functions =====================================================
	public void createDay(){
		int d =1;	//var for the actual day count in the array
		for(int i=0;i<31;i++){		//loop through all the days creating a list of days to right
			Item curr = new Item();
			String day = String.valueOf(d);
			curr.setDescription(day);
			this.dayArr[i] = curr;
			d++;
		}
	}

	public void createMonth(){
		for(int i=0;i<12;i++){
			Item curr = new Item();
			switch(i){
				case 0: curr.setDescription("January");
					break;
				case 1: curr.setDescription("February");
					break;
				case 2: curr.setDescription("March");
					break;
				case 3: curr.setDescription("April");
					break;
				case 4: curr.setDescription("May");
					break;
				case 5: curr.setDescription("June");
					break;
				case 6: curr.setDescription("July");
					break;
				case 7: curr.setDescription("August");
					break;
				case 8: curr.setDescription("September");
					break;
				case 9: curr.setDescription("October");
					break;
				case 10: curr.setDescription("November");
					break;
				case 11: curr.setDescription("December");
					break;
			}
			this.monthArr[i] = curr;
		}
	}


	public int[] findIndexesArray(int d,String month){
		int[] indexArr = new int[2];	//array to be used to send back array with correct indexes
		String day = String.valueOf(d);
		//================================find indexes======================================
		for(int i=0;i<12;i++){
			if(monthArr[i].getDescription().equals(month)){
				indexArr[0]=i;	//set index for 0(month index) for correct month
			}
		}
		for(int j=0;j<31;j++){
			if(dayArr[j].getDescription().equals(day)){
				indexArr[1]=j;	//set index for 1(day) for correct day
			}
		}
		//================================End of find indexes======================================
		return indexArr;
	}

	public Item getDayNext(int day){	//get immediate next of that day
		int dayIndex = day-1;
		return dayArr[dayIndex].right;
	}

	public Item getMonthNext(String month){	//get immediate next of that month
		int monthIndex=0;
		for(int i=0;i<12;i++){
			if(monthArr[i].getDescription().equals(month)){
				monthIndex=i;
				return monthArr[monthIndex].down;
			}
		}
		return null;
	}


}
