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
		}else if(monthArr[monthIndex].down!=null && dayArr[dayIndex].right==null){	//case where there is no item on that day but there are items in that month
			dayArr[dayIndex].right = newItem;	//create new Item for that day
			Item currMonth;
			currMonth = recSearchMonthSlot(monthArr[monthIndex].down);
			currMonth.down = newItem;
		}else if(monthArr[monthIndex].down==null && dayArr[dayIndex].right!=null){	//if month has no events but day has other events
			monthArr[monthIndex].down=newItem;
			Item currDay;
			currDay = recSearchDaySlot(dayArr[dayIndex].right);
			currDay.right=newItem;
		}else{	//if both month and day are not empty
			Item currMonth,currDay;
			if(isOccupied(day,month)){	//check if the slot is occupied
				Item dNode,mNode,curr,prev;
				dNode=getDayHead(day);
				mNode=getMonthHead(month);
				while(dNode.right!=null && mNode.down!=null && !dNode.getDescription().equals(mNode.getDescription())){
					dNode=dNode.right;
					mNode=mNode.down;
				}
				if(dNode.getDescription().equals(mNode.getDescription())){	//at item intersection
					prev=curr=dNode;
					//must loop through back list and prioritise
					LoopBack(newItem,dNode);
				}
			}else{	//no item intersection, hence just add item to avail slot
				currDay=recSearchDaySlot(dayArr[dayIndex]);
				currMonth=recSearchMonthSlot(monthArr[monthIndex]);
			}
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
		Item dNode;
		dNode = getDayHead(day);
		if(dNode.right==null){
			return null;
		}else
		return dNode.right;
	}
	//============================================helper functions =====================================================
	public void createDay(){
		int d =1;	//var for the actual day count in the array
		for(int i=0;i<31;i++){		//loop through all the days creating a list of days to right
			Item curr = new Item();
			String day = String.valueOf(d);
			curr.setDescription(day);
			dayArr[i] = curr;
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
			monthArr[i] = curr;
		}
	}


	public int[] findIndexesArray(int d,String month){
		int[] indexArr = new int[2];	//array to be used to send back array with correct indexes
		String day = String.valueOf(d);
		//================================find indexes======================================
		for(int i=0;i<12;i++){
			if(monthArr[i].getDescription().equals(month)){
				indexArr[0]=i;	//set index for 0(month index) for correct month
				break;
			}
		}
		for(int j=0;j<31;j++){
			if(dayArr[j].getDescription().equals(day)){
				indexArr[1]=j;	//set index for 1(day) for correct day
				break;
			}
		}
		//================================End of find indexes======================================
		return indexArr;
	}

	public Item getDayHead(int day){	//retrun head of list for that day
		return dayArr[day-1];
	}

	public Item getMonthHead(String month){		//return head of list for that month
		int index =0;
		switch(month){
			case "January":index = 0;
				break;
			case "February":index = 1;
				break;
			case "March":index = 2;
				break;
			case "April":index = 3;
				break;
			case "May":index = 4;
				break;
			case "June":index = 5;
				break;
			case "July":index = 6;
				break;
			case "August":index = 7;
				break;
			case "September":index = 8;
				break;
			case "October":index = 9;
				break;
			case "November":index = 10;
				break;
			case "December":index = 11;
				break;
		}
		return monthArr[index];
	}

	public void printMonth(Item node){
		if(node.down==null){
			System.out.println(node.getDescription());
			return;
		}else
			System.out.println(node.getDescription());
			printMonth(node.down);
	}

	public void printDay(Item node){
		if(node.right==null){
			System.out.println(node.getDescription());
			return;
		}else{
			System.out.println(node.getDescription());
			printDay(node.right);
		}
	}

	public Item recSearchDaySlot(Item dayNode){
		if(dayNode.right==null){
			return dayNode;
		}else{
			return recSearchDaySlot(dayNode.right);
		}
	}

	public Item recSearchMonthSlot(Item monthNode){
		if(monthNode.down==null){
			return monthNode;
		}else	return recSearchMonthSlot(monthNode.down);
	}




	public void LoopBack(Item newNode,Item node){
		Item curr,prev;
		prev=curr=node;
		if(node.back==null){	//only one item in slot
			if(curr.getPriority()>newNode.getPriority()){
				curr.back=newNode;
			}else{
				newNode.right = curr.right;
				newNode.down = curr.down;
				newNode.back = curr;
				curr.right=null;
				curr.down=null;
			}
		}else{
			while(curr.back!=null && curr.getPriority()>newNode.getPriority()){
				prev=curr;
				curr=curr.back;
			}
			if(curr.back==null){	//last item in the list but still has higher priority
				curr.back = newNode;
			}else{
				prev.back=newNode;
				newNode.back = curr;
			}

		}
	}

	public boolean isOccupied(int day, String month){	//function to see if slot is occupied
		Item dNode,mNode;
		dNode=getDayHead(day);
		mNode=getMonthHead(month);
		while (dNode.right!=null && mNode.down!=null && !dNode.getDescription().equals(mNode.getDescription())){
			dNode=dNode.right;
			mNode=mNode.down;
		}
		if(dNode.getDescription().equals(mNode.getDescription())){
			return true;
		}else
			return false;
	}

}
