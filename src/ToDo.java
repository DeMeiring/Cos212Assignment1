public class ToDo
{
	/*Use this class to test your implementation.  This file will be overwritten for marking purposes.*/



	public static void main(String[] args)
	{
		//day 1
		//Write code to test your implementation here.
		Calendar clnd = new Calendar();
		clnd.addItem(1,"January","test for first insert","2 hours",0);
		clnd.addItem(2,"January","test for first insert","2 hours",1);
		clnd.addItem(2,"January","test for SECOND insert","1 hours",0);
		System.out.println(clnd.getDayHead(2));

		/*clnd.addItem(1,"January","test for first insert","2 hours",3);
		clnd.addItem(1,"January","test for first insert","2 hours",5);*/
		//clnd.addItem(2,"January","test for second insert","2 hours",0);
		//clnd.addItem(1,"February","2nd item on same day test","2Hours",0);


		}

}
	

