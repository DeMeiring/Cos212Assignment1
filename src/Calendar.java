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

	public void addItem(int day, String month, String description, String duration, int priority) {
		/* Insert an Item at the given day and month combination according to priority. Intialize the Item with the remainder of the parameters.

		Duplicate Items are allowed.*/
		//==================================Set Indexes===========================================================

		int[] Indexes = findIndexesArray(day, month);
		int monthIndex = Indexes[0];
		int dayIndex = Indexes[1];
		//==================================End of Set Indexes===========================================================
		//==================================create new Item==============================================================
		Item newItem = new Item();
		newItem.setDescription(description);
		newItem.setDuration(duration);
		newItem.setPriority(priority);
		//==================================End of create new Item==============================================================
		if (monthArr[monthIndex].down == null && dayArr[dayIndex].right == null) {    //there does not exist an item at that day of the month
			monthArr[monthIndex].down = newItem;
			dayArr[dayIndex].right = newItem;
		} else if (monthArr[monthIndex].down != null && dayArr[dayIndex].right == null) {    //case where there is no item on that day but there are items in that month
			dayArr[dayIndex].right = newItem;

			int mfcount = 0, mlcount = 0;
			Item dayPtr = dayArr[mfcount];
			Item prevMonth = monthArr[monthIndex];
			Item first = monthArr[monthIndex].down;    //first intersection
			Item last = first;
			while (last.down != null) {    //get last intersection
				last = last.down;
			}

			while (dayPtr != first) {    //update fcount
				if (dayPtr.right == null) {
					mfcount++;
					dayPtr = dayArr[mfcount];
				} else {
					dayPtr = dayPtr.right;
				}
			}
			mlcount = mfcount;
			while (dayPtr != last) {
				if (dayPtr.right == null) {
					mlcount++;
					dayPtr = dayArr[mlcount];
				} else {
					dayPtr = dayPtr.right;
				}
			}

			if (mfcount > dayIndex) {    //day index is before first intersection
				monthArr[monthIndex].down = newItem;
				newItem.down = first;
			} else if (dayIndex > mlcount) {
				last.down = newItem;
			} else if (first.down == last && dayIndex > mfcount && dayIndex < mlcount) {
				first.down = newItem;
				newItem.down = last;
			} else {
				dayPtr = dayArr[mfcount];
				while (true) {
					if (first.down != last && first.down != null)
						first = first.down;
					while (dayPtr != first) {
						if (dayPtr.right == null) {
							mfcount++;
							dayPtr = dayArr[mfcount];
						} else {
							dayPtr = dayPtr.right;
						}
					}
					if (mfcount >= dayIndex) {
						break;
					}
				}
				first.down = newItem;
				newItem.down = last;
			}
		} else if (monthArr[monthIndex].down == null && dayArr[dayIndex].right != null) {    //if month has no events but day has other events
			monthArr[monthIndex].down = newItem;

			int dfcount = 0, dlcount = 0;
			Item monthPtr = monthArr[dfcount];
			Item first = dayArr[dayIndex].right;
			Item last = first;
			while (last.right != null) {
				last = last.right;
			}
			while (monthPtr != first) {
				if (monthPtr.down == null) {
					dfcount++;
					monthPtr = monthArr[dfcount];
				} else
					monthPtr = monthPtr.down;

			}
			dlcount = dfcount;
			while (monthPtr != last) {
				if (monthPtr.down == null) {
					dlcount++;
					monthPtr = monthArr[dlcount];
				} else
					monthPtr = monthPtr.down;
			}

			if (dfcount > monthIndex) {
				dayArr[dayIndex].right = newItem;
				newItem.right = first;
			} else if (monthIndex > dlcount) {
				last.right = newItem;
			} else if (dfcount < monthIndex && monthIndex < dlcount && first.right == last) {
				first.right = newItem;
				newItem.right = last;
			} else {
				monthPtr = monthArr[dfcount];
				while (true) {
					if (first.right != last && first.right != null)
						first = first.right;
					while (monthPtr != first) {
						if (monthPtr.down == null) {
							dfcount++;
							monthPtr = monthArr[dfcount];
						} else {
							monthPtr = monthPtr.down;
						}
					}
					if (dfcount >= monthIndex) {
						break;
					}
				}
				first.right = newItem;
				newItem.right = last;
			}
		} else {    //if both month and day are not empty
			if (!isOccupied(day, month)) {    //no item is already in the slot
				int dfcount = 0, dlcount = 0;
				Item monthPtr = monthArr[dfcount];
				Item first = dayArr[dayIndex].right;
				Item last = first;
				while (last.right != null) {
					last = last.right;
				}
				while (monthPtr != first) {
					if (monthPtr.down == null) {
						dfcount++;
						monthPtr = monthArr[dfcount];
					} else
						monthPtr = monthPtr.down;

				}
				dlcount = dfcount;
				while (monthPtr != last) {
					if (monthPtr.down == null) {
						dlcount++;
						monthPtr = monthArr[dlcount];
					} else
						monthPtr = monthPtr.down;
				}

				if (dfcount > monthIndex) {
					dayArr[dayIndex].right = newItem;
					newItem.right = first;
				} else if (monthIndex > dlcount) {
					last.right = newItem;
				} else if (dfcount < monthIndex && monthIndex < dlcount && first.right == last) {
					first.right = newItem;
					newItem.right = last;
				} else {
					monthPtr = monthArr[dfcount];
					while (true) {
						if (first.right != last && first.right != null)
							first = first.right;
						while (monthPtr != first) {
							if (monthPtr.down == null) {
								dfcount++;
								monthPtr = monthArr[dfcount];
							} else {
								monthPtr = monthPtr.down;
							}
						}
						if (dfcount >= monthIndex) {
							break;
						}
					}
					first.right = newItem;
					newItem.right = last;
				}

				int mfcount = 0, mlcount = 0;
				Item dayPtr = dayArr[mfcount];
				Item prevMonth = monthArr[monthIndex];
				 first = monthArr[monthIndex].down;    //first intersection
				 last = first;
				while (last.down != null) {    //get last intersection
					last = last.down;
				}

				while (dayPtr != first) {    //update fcount
					if (dayPtr.right == null) {
						mfcount++;
						dayPtr = dayArr[mfcount];
					} else {
						dayPtr = dayPtr.right;
					}
				}
				mlcount = mfcount;
				while (dayPtr != last) {
					if (dayPtr.right == null) {
						mlcount++;
						dayPtr = dayArr[mlcount];
					} else {
						dayPtr = dayPtr.right;
					}
				}

				if (mfcount > dayIndex) {    //day index is before first intersection
					monthArr[monthIndex].down = newItem;
					newItem.down = first;
				} else if (dayIndex > mlcount) {
					last.down = newItem;
				} else if (first.down == last && dayIndex > mfcount && dayIndex < mlcount) {
					first.down = newItem;
					newItem.down = last;
				} else {
					dayPtr = dayArr[mfcount];
					while (true) {
						if (first.down != last && first.down != null)
							first = first.down;
						while (dayPtr != first) {
							if (dayPtr.right == null) {
								mfcount++;
								dayPtr = dayArr[mfcount];
							} else {
								dayPtr = dayPtr.right;
							}
						}
						if (mfcount >= dayIndex) {
							break;
						}
					}
					first.down = newItem;
					newItem.down = last;
				}
			}else{	//find prev day and prev month ,currday ,currMonth and use loop back
				Item dNode,mNode,prevDay,prevMonth,currItem;
				dNode=dayArr[dayIndex].right;
				mNode = monthArr[monthIndex].down;
				prevDay =dayArr[dayIndex];
				prevMonth=monthArr[monthIndex];
				while(true){
					if(dNode==mNode){
						currItem=dNode;
						break;
					}
					if(dNode.right!=null){
						dNode=dNode.right;
					}
					if(mNode.down!=null){
						mNode=mNode.down;
					}
				}
				while(prevDay.right!=currItem){
					if(prevDay.right!=null){
						prevDay=prevDay.right;
					}
				}
				while(prevMonth.down!=currItem){
					if(prevMonth.down!=null){
						prevMonth=prevMonth.down;
					}
				}
				LoopBack(newItem,currItem,prevDay,prevMonth);
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
		int[] arr = findIndexesArray(day,month);
		int dayIndex,monthIndex;
		dayIndex=arr[1];
		monthIndex=arr[0];
		Item dNode,mNode,prevPrior,currPrior;
		dNode=dayArr[dayIndex];
		mNode=monthArr[monthIndex];
		if(!isOccupied(day,month)){	//is no item in the day month combo
			return;
		}else{
			Item prevMonth,prevDay,currItem;
			prevMonth=monthArr[monthIndex];
			prevDay=dayArr[dayIndex];

			while(dNode!=mNode){
				if(dNode.right!=null){
					dNode=dNode.right;
				}
				if(mNode.down!=null){
					mNode=mNode.down;
				}
			}
			currItem=dNode;
			while(prevDay.right!=currItem){
				if(dNode.right!=null){
					dNode=dNode.right;
				}
			}
			while(prevMonth.down!=currItem){
				if(mNode.down!=null){
					mNode=mNode.down;
				}
			}

			if(currItem.back==null && currItem.getPriority()==priority){
				prevDay.right=currItem.right;
				prevMonth.down=currItem.down;
			}else if(currItem.back==null && currItem.getPriority()!=priority){
				return;
			}else {
				if (currItem.back!=null && currItem.getPriority()==priority) {
					Item prevItem = currItem;
					while(currItem.back!=null){
						if(currItem.back.getPriority()==priority){
							currItem=currItem.back;
						}else{
							break;
						}
					}
					if(currItem.back!=null){
						currItem.back.down=prevItem.down;
						currItem.back.right=prevItem.right;
						prevDay.right=currItem.back;
						prevMonth.down=currItem.back;
					}else{
						prevDay.right=prevItem.right;
						prevMonth.down=prevItem.down;
					}

				} else {
					prevPrior = currPrior = currItem;
					while (true) {
						if (currItem.back == null && currItem.getPriority() != priority) {
							return;
						} else if (currPrior.back != null && currPrior.getPriority() != priority) {
							prevPrior = currPrior;
							currPrior = currPrior.back;
						} else {
							break;
						}
					}

					while (currPrior.back != null) {
						if(currPrior.back.getPriority()==priority){
							currPrior = currPrior.back;
						}else{
							break;
						}
					}
					prevPrior.back = currPrior.back;
				}
			}
		}
	}
	
	public void deleteItems(int day, String month)
	{
		/*Delete all items at the given day and month combination.*/
		int[] arr = findIndexesArray(day,month);
		int dayIndex=arr[1],monthIndex=arr[0];
		if(!isOccupied(day,month)){	//no item at given day month combo
			return;
		}else{
			Item dNode,mNode,prevDay=dayArr[dayIndex],prevMonth=monthArr[monthIndex],currItem;
			dNode=dayArr[dayIndex];
			mNode=monthArr[monthIndex];
			while (dNode!=mNode){
				if(dNode.right!=null){
					dNode=dNode.right;
				}
				if(mNode.down!=null){
					mNode=mNode.down;
				}
			}
			currItem=dNode;
			while(prevDay.right!=currItem){
				if(prevDay.right!=null){
					prevDay=prevDay.right;
				}
			}
			while(prevMonth.down!=currItem){
				if(prevMonth.down!=null){
					prevMonth=prevMonth.down;
				}
			}
			prevDay.right=currItem.right;
			prevMonth.down=currItem.down;
		}
	}
	
	/*Clearing Methods*/
	public void clearMonth(String month)
	{
		/*All items for the given month should be deleted.
		If the month has no Items, simply do nothing.*/
		int[] arr = findIndexesArray(1,month);
		int monthIndex=arr[0];
		if(monthArr[monthIndex].down==null){	//no items in that month
			return;
		}else {
			Item currMonth = monthArr[monthIndex].down;
			Item currDay, prevDay;
			if (currMonth.down.down == null) {    //just one item for that month
				for (Item day : dayArr) {
					if (day.right != null) {
						currDay = day.right;
						prevDay = day;
						while (true) {
							if (currDay == currMonth) {
								prevDay.right = currDay.right;
								break;
							} else if (currDay.right != null) {
								prevDay = currDay;
								currDay = currDay.right;
							} else {
								break;
							}
						}
					}
				}
				monthArr[monthIndex].down = null;
			} else {	//more than one item in that month
				while (currMonth.down != null) {
					for (Item day : dayArr) {
						if (day.right != null) {
							currDay = day.right;
							prevDay = day;
							while (true) {
								if (currDay == currMonth) {
									prevDay.right = currDay.right;
									break;
								}else if(currDay.right!=null){
									prevDay=currDay;
									currDay=currDay.right;
								}else{
									break;
								}
							}
						}
					}
					currMonth = currMonth.down;
				}
				monthArr[monthIndex].down = null;
			}
		}
	}
	
	public void clearDay(int day)
	{
		/*All items for the given day should be deleted.
		If the day has no Items, simply do nothing.*/
	}
	
	public void clearYear()
	{
		/*Delete all Items from the calendar.*/

		int dCount=0,mCount=0;
		Item currDay=dayArr[dCount],currMonth=monthArr[mCount];
		for (Item day:dayArr) {
			if(day.right!=null){
				day.right=null;
			}
		}
		for (Item month:monthArr) {
			if(month.down!=null){
				month.down=null;
			}
		}
		/*while(true){
			if(currDay.right!=null){
				currDay.right=null;
			}
			if(currMonth.down!=null){
				currMonth.down=null;
			}
			if(dCount<=30){
				dCount++;
			}
			if(mCount<=11){
				mCount++;
			}
			if(dCount==30&&mCount==11){
				break;
			}
			currDay=dayArr[dCount];
			currMonth=monthArr[mCount];
		}*/
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


	public void LoopBack(Item newNode,Item node,Item dayPrev,Item monthPrev){
		Item prev,curr;
		prev=curr=node;
		if(node.back==null){	//only one item in slot
			if(curr.getPriority()>newNode.getPriority()){
				curr.back=newNode;
				return;
			}else{
				newNode.right = curr.right;
				newNode.down = curr.down;
				newNode.back = curr;
				dayPrev.right=newNode;	//previous day is vertically determined
				monthPrev.down=newNode;	//previous month is horizontally determined
				curr.right=null;
				curr.down=null;
				return;
			}
		}else{	//more than one item in slot
			if(curr.getPriority()<newNode.getPriority()){
				newNode.right = curr.right;
				newNode.down = curr.down;
				newNode.back=curr;
				dayPrev.right= newNode;
				monthPrev.down=newNode;
				curr.right=null;
				curr.down=null;
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
					newNode.right=curr.right;
					newNode.down=curr.down;
					newNode.back=curr;
					curr.right=null;
					curr.down=null;
					prev.back=newNode;
				}else{	//at end of list and curr has greater priority than newNode ,hence just add to back
					newNode.right=curr.right;
					newNode.down=curr.down;
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
