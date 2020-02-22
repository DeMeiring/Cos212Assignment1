public class ToDo
{
	/*Use this class to test your implementation.  This file will be overwritten for marking purposes.*/
	//==========================================test functions===========================================
	public	static void printItemUsingDay(Calendar cal,int day){
		Item ptr = cal.getDayNext(day);
		if(ptr==null){
			System.out.println("day does not contain any items");
		}else System.out.println(ptr.getDescription());
	}

	public	static void printItemUsingMonth(Calendar cal,String month){
		Item ptr = cal.getMonthNext(month);
		if(ptr==null){
			System.out.println("month does not contain any items");
		}else System.out.println(ptr.getDescription());
	}


	//==========================================END of test functions===========================================


	public static void main(String[] args)
	{
		//day 1
		//Write code to test your implementation here.
		Calendar clnd = new Calendar();
		clnd.addItem(1,"January","test for first insert","2 hours",0);

		printItemUsingDay(clnd,1);
		printItemUsingMonth(clnd,"January");
	}
	
}
