public class ToDo
{
	/*Use this class to test your implementation.  This file will be overwritten for marking purposes.*/



	public static void main(String[] args)
	{
		//day 1
		//Write code to test your implementation here.
		Calendar clnd = new Calendar();
		//==================================================Test Back Creator==================================================
		clnd.addItem(2,"January","test for lowest insert","2 hours",0);
		clnd.addItem(2,"January","test for second lowest insert","2 hours",1);
		clnd.addItem(2,"January","test for highest insert","1 hours",2);
		clnd.addItem(2,"January","test for highest insert","1 hours",2);
		clnd.addItem(2,"January","test for highest highest insert","8 hours",3);
		clnd.addItem(2,"January","test for lowest insert","1 hours",0);
		//==================================================End Test Back Creator==================================================

		clnd.addItem(2,"March","test for march insert","2 hours",0);
		clnd.addItem(13,"March","test 2 for march insert","2 hours",0);
		clnd.addItem(2,"December","test for december insert higher priority","2 hours",1);
		clnd.addItem(2,"December","test for december insert lower priority","2 hours",0);
		clnd.addItem(2,"December","test for december insert priority 5","2 hours",5);
		clnd.addItem(2,"December","test for december insert priority 4","2 hours",4);
		System.out.println(clnd.isOccupied(2,"December"));
		System.out.println(clnd.findIndexesArray(2,"March")[0]+" "+clnd.findIndexesArray(2,"March")[1]);


		/*clnd.addItem(1,"January","test for first insert","2 hours",3);
		clnd.addItem(1,"January","test for first insert","2 hours",5);*/
		//clnd.addItem(2,"January","test for second insert","2 hours",0);
		//clnd.addItem(1,"February","2nd item on same day test","2Hours",0);


		}

}
	

