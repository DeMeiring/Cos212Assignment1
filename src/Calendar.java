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
		}else if(monthArr[monthIndex].down!=null && dayArr[dayIndex].right==null) {    //case where there is no item on that day but there are items in that month
			dayArr[dayIndex].right = newItem;

			int fcount=0,lcount=0;
			Item dayPtr=dayArr[fcount];
			Item prevMonth = monthArr[monthIndex];
			Item first = monthArr[monthIndex].down;	//first intersection
			Item last = first;
			while(last.down!=null){	//get last intersection
				last=last.down;
			}

			while (dayPtr!=first){	//update fcount
				if(dayPtr.right==null){
					fcount++;
					dayPtr=dayArr[fcount];
				}else {
					dayPtr=dayPtr.right;
				}
			}
			lcount=fcount;
			while(dayPtr!=last){
				if(dayPtr.right==null){
					lcount++;
					dayPtr=dayArr[lcount];
				}else{
					dayPtr=dayPtr.right;
				}
			}

			if(fcount>dayIndex){	//day index is before first intersection
				monthArr[monthIndex].down=newItem;
				newItem.down=first;
			}else if(dayIndex>lcount){
				last.down=newItem;
			}else if(first.down==last && dayIndex>fcount && dayIndex<lcount){
				first.down=newItem;
				newItem.down = last;
			}else{
				dayPtr=dayArr[fcount];
				while (true){
					if(first.down!=last && first.down!=null)
						first=first.down;
					while (dayPtr!=first){
						if(dayPtr.right==null){
							fcount++;
							dayPtr=dayArr[fcount];
						}else{
							dayPtr=dayPtr.right;
						}
					}
					if(fcount>=dayIndex){
						break;
					}
				}
				first.down=newItem;
				newItem.down=last;
			}





		}else if(monthArr[monthIndex].down==null && dayArr[dayIndex].right!=null){	//if month has no events but day has other events
			monthArr[monthIndex].down=newItem;

			int fcount=0,lcount=0;
			Item monthPtr = monthArr[fcount];
			Item first = dayArr[dayIndex].right;
			Item last=first;
			while (last.right!=null){
				last=last.right;
			}
			while(monthPtr!=first){
				if(monthPtr.down==null){
					fcount++;
					monthPtr=monthArr[fcount];
				}else
					monthPtr=monthPtr.down;

			}
			lcount=fcount;
			while (monthPtr!=last){
				if(monthPtr.down==null){
					lcount++;
					monthPtr=monthArr[lcount];
				}else
					monthPtr=monthPtr.down;
			}

			if(fcount>monthIndex){
				dayArr[dayIndex].right = newItem;
				newItem.right=first;
			}else if(monthIndex>lcount){
				last.right=newItem;
			}else if(fcount<monthIndex && monthIndex<lcount && first.right==last){
				first.right=newItem;
				newItem.right=last;
			}else{
				monthPtr=monthArr[fcount];
				while (true){
					if(first.right!=last && first.right!=null)
						first=first.right;
					while (monthPtr!=first){
						if(monthPtr.down==null){
							fcount++;
							monthPtr=monthArr[fcount];
						}else{
							monthPtr=monthPtr.down;
						}
					}
					if(fcount>=monthIndex){
						break;
					}
				}
				first.right=newItem;
				newItem.right=last;
			}




		}else{	//if both month and day are not empty
			if(!isOccupied(day, month)) {	//no item is already in the slot
				Item monthptr = monthArr[monthIndex], dayptr = dayArr[dayIndex];
				Item prevMonth = monthArr[monthIndex], currMonth = monthArr[monthIndex].down;
				Item prevDay = dayArr[dayIndex], currDay = dayArr[dayIndex].right;
				//do day figure out first
				int dCount = 0;
				int mCount = 0;
				while (dayArr[dCount].right == null) {    //find index of first day poiniting to item
					if (currMonth.down != null) {    //update month pointers for each day that goes down
						prevMonth = currMonth;
						currMonth = currMonth.down;
					}
					dCount++;
				}

				while (monthArr[mCount].down == null) {    //find index of first month pointing to item
					if (currDay.right != null) {    //update day pointers for each month that goes right
						prevDay = currDay;
						currDay = currDay.right;
					}
					mCount++;
				}

				if (dCount > dayIndex && mCount > monthIndex) {
					prevMonth.down = newItem;
					newItem.down = currMonth;
					prevDay.right = newItem;
					newItem.right=currDay;

				}else if(dCount<dayIndex && mCount>monthIndex){
					while(dCount!=dayIndex){	//update dcount till matches the desired day index
						if(currMonth.down!=null){
							prevMonth=currMonth;
							currMonth=currMonth.down;
						}
						dCount++;
					}
					if(currMonth.down!=null){
						prevMonth.down=newItem;
						newItem.down=currMonth;
						prevDay.right=newItem;
						newItem.right=currDay;
					}else{
						currMonth.down=newItem;
						prevDay.right=newItem;
						newItem.right=currDay;
					}

				}else if(dCount>dayIndex && mCount<monthIndex){
					while(mCount!=monthIndex){
						if(currDay.right!=null){
							prevDay=currDay;
							currDay=currDay.right;
						}
						mCount++;
					}
					if(currDay.right!=null){
						prevDay.right=newItem;
						newItem.right=currDay;
						prevMonth.down=newItem;
						newItem.down=currMonth;
					}else{
						currDay.right=newItem;
						prevMonth.down=newItem;
						newItem.down=currMonth;
					}
				}else{
					while(mCount!=monthIndex){	//update index and day pointers
						if(currDay.right!=null){
							prevDay=currDay;
							currDay=currDay.right;
						}
						mCount++;
					}

					while(dCount!=dayIndex){	//update dcount till matches the desired day index
						if(currMonth.down!=null){
							prevMonth=currMonth;
							currMonth=currMonth.down;
						}
						dCount++;
					}

					if(currDay.right!=null && currMonth.down!=null){
						prevMonth.down=newItem;
						newItem.down=currMonth;
						prevDay.right=newItem;
						newItem.right=currDay;
					}else if(currDay.right!=null && currMonth.down==null){
						prevDay.right=newItem;
						newItem.right=currDay;
						currMonth.down=newItem;
					}else if(currDay.right==null && currMonth.down!=null){
						prevMonth.down=newItem;
						newItem.down=currMonth;
						currDay.right=newItem;
					}
				}

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
		if(!isOccupied(day,month)){	//no item at that intersection
			return null;
		}else{	//does contain intersection
			Item dNode,mNode;
			mNode = getMonthHead(month);
			dNode = getDayHead(day);
			while(dNode!=mNode){	//loop through corresponding month and day list
				if(dNode.right !=null){
					dNode=dNode.right;
				}else if(mNode.down!=null){
					mNode=mNode.down;
				}else{
					break;
				}
			}
			if(dNode==mNode) {
				Item retItem = new Item();
				retItem.setPriority(dNode.getPriority());
				retItem.setDuration(dNode.getDuration());
				retItem.setDescription(dNode.getDescription());
				retItem.back = dNode.back;
				retItem.right = dNode.right;
				retItem.down = dNode.down;
				return retItem;
			}else return null;
		}
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
				case 0: curr.setDescription("JAN");
					break;
				case 1: curr.setDescription("FEB");
					break;
				case 2: curr.setDescription("MAR");
					break;
				case 3: curr.setDescription("APR");
					break;
				case 4: curr.setDescription("MAY");
					break;
				case 5: curr.setDescription("JUN");
					break;
				case 6: curr.setDescription("JUL");
					break;
				case 7: curr.setDescription("AUG");
					break;
				case 8: curr.setDescription("SEP");
					break;
				case 9: curr.setDescription("OCT");
					break;
				case 10: curr.setDescription("NOV");
					break;
				case 11: curr.setDescription("DEC");
					break;
			}
			monthArr[i] = curr;
		}
	}


	public int[] findIndexesArray(int d,String month){
		month=month.toUpperCase();
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
		month=month.toUpperCase();
		int index =0;
		switch(month){
			case "JAN":index = 0;
				break;
			case "FEB":index = 1;
				break;
			case "MAR":index = 2;
				break;
			case "APR":index = 3;
				break;
			case "MAY":index = 4;
				break;
			case "JUN":index = 5;
				break;
			case "JUL":index = 6;
				break;
			case "AUG":index = 7;
				break;
			case "SEP":index = 8;
				break;
			case "OCT":index = 9;
				break;
			case "NOV":index = 10;
				break;
			case "DEC":index = 11;
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
		}else
			return recSearchMonthSlot(monthNode.down);
	}






	public void LoopBack(Item newNode,Item node,Item dayPrev,Item monthPrev){
		Item curr,prev;
		prev=curr=node;
		if(node.back==null){	//only one item in slot
			if(curr.getPriority()>newNode.getPriority()){
				curr.back=newNode;
				return;
			}else{
				newNode.right = curr.right;
				newNode.down = curr.down;
				newNode.back = curr;
				dayPrev.down=newNode;	//previous day is vertically determined
				monthPrev.right=newNode;	//previous month is horizontally determined
				curr.right=null;
				curr.down=null;
				return;
			}
		}else{	//more than one item in slot
			if(curr.getPriority()<newNode.getPriority()){
				newNode.right = curr.right;
				newNode.down = curr.down;
				newNode.back=curr;
				dayPrev.down= newNode;
				monthPrev.right=newNode;
				return;
			}else {
				while (curr.back != null) {
					if(curr.getPriority()==newNode.getPriority()){	//newNode has same priority as curr then just add to back of curr
						newNode.back=curr.back;
						curr.back=newNode;
						return;
					}else if(curr.getPriority()>newNode.getPriority()){
						prev=curr;
						curr=curr.back;
					}else{
						break;
					}
				}
				if(curr.getPriority()<newNode.getPriority()){	//at end if the list and curr's priority is less than newNodes
					newNode.back=curr;
					prev.back=newNode;
				}else{	//at end of list and curr has greater priority than newNode ,hence just add to back
					curr.back=newNode;
				}

			}

		}
	}

	public boolean isOccupied(int day, String month){	//function to see if slot is occupied
		Item dNode,mNode;
		dNode=getDayHead(day);
		mNode=getMonthHead(month);
		while (true){
			if(dNode==mNode){
				return true;
			}
			if(dNode.right!=null){
				dNode=dNode.right;
			}
			if(mNode.down!=null){
				mNode=mNode.down;
			}
			if(dNode.right==null && mNode.down==null && dNode!=mNode){
				return false;
			}
		}
	}


}
