public class ToDo
{
	/*Use this class to test your implementation.  This file will be overwritten for marking purposes.*/



	public static void main(String[] args) {
		//day 1
		//Write code to test your implementation here.
		Calendar clnd = new Calendar();
		/*clnd.addItem(3,"Jan","3rd Jan","06:00",0);
		clnd.addItem(1,"Mar","1st Mar","06:00",0);
		clnd.addItem(4,"Mar","4th Mar","06:00",0);
		clnd.addItem(9,"Mar","9th Mar","06:00",0);
		clnd.addItem(5,"Mar","5th Mar","06:00",0);
		clnd.addItem(1,"Feb","1st Feb","06:00",0);

		clnd.addItem(3,"Mar","3rd Mar","06:00",9);
		clnd.addItem(3,"Feb","3rd Feb","06:00",9);*/
		/*clnd.addItem(9,"Jan","9th Jan","06:00",0);
		clnd.addItem(9,"Feb","9th Feb","06:00",0);
		clnd.addItem(9,"Mar","9th Mar","06:00",0);
		clnd.addItem(4,"Mar","4th Mar","06:00",0);
		clnd.addItem(2,"Mar","2nd Mar","06:00",0);
		clnd.addItem(23,"Mar","23rd Mar","06:00",0);*/

		clnd.addItem(2,"Jan","2nd Jan","06:00",0);
		clnd.addItem(2,"Mar","2nd Mar","06:00",0);
		clnd.addItem(3,"Feb","3rd Feb","06:00",0);
		clnd.addItem(2,"Feb","2nd Feb","06:00",0);
		clnd.addItem(2,"Dec","2nd Dec","06:00",0);
		clnd.addItem(2,"Jan","2nd Jan first priority","06:00",1);
		clnd.addItem(2,"Jan","2nd Jan last priority","06:00",0);
		clnd.addItem(2,"Jan","2nd Jan priority 6","06:00",6);
		clnd.addItem(31,"Jan","31st Jan priority 6","06:00",6);
		clnd.addItem(2,"Jan","2nd Jan priority 6","06:00",6);
		clnd.addItem(2,"Jan","2nd Jan priority 2","06:00",2);
		clnd.addItem(2,"Jul","2nd Jul priority 2","06:00",2);

		//clnd.deletePriorityItem(2,"Jan",6);
		//clnd.clearMonth("Jan");
		clnd.clearYear();




		System.out.println("end");
	}
}
	

