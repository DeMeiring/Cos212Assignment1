public class ToDo
{
	/*Use this class to test your implementation.  This file will be overwritten for marking purposes.*/
	public static int randomDay(){
		double rand = Math.random();
		rand=rand*31+1;
		return (int)rand;
	}

	public static String randomMonth(){
		double rand = Math.random();
		rand=rand*12+1;
		String[] arr = {"Jan","Feb","Mar","Apr","may","jun","Jul","aug","Sep","Oct","nov","DEC"};
		return arr[(int)rand];
	}




	public static void main(String[] args) {
		Calendar clnd = new Calendar();
		/*for(int i=0;i<6;i++){
			int rd=randomDay();
			System.out.println(rd+" ");
			clnd.addItem(rd,"Jun",String.valueOf(rd)+" JUN","06:00",0);
		}
		System.out.println("============================================================================================");

		for(int i=0;i<6;i++){
			int rd=randomDay();
			System.out.println(rd+" ");
			clnd.addItem(rd,"Dec",String.valueOf(rd)+" Dec","06:00",0);
		}

		System.out.println("============================================================================================");
		for(int i=0;i<6;i++){
			int rd=randomDay();
			System.out.println(rd+" ");
			clnd.addItem(rd,"Jan",String.valueOf(rd)+" Jan","06:00",0);
		}

		System.out.println("============================================================================================");
		for(int i=0;i<6;i++){
			int rd=randomDay();
			System.out.println(rd+" ");
			clnd.addItem(rd,"Mar",String.valueOf(rd)+" Mar","06:00",0);
		}

		System.out.println("============================================================================================");
		for(int i=0;i<6;i++){
			int rd=randomDay();
			System.out.println(rd+" ");
			clnd.addItem(rd,"Oct",String.valueOf(rd)+" Oct","06:00",0);
		}*/
		//clnd.print();

		/*clnd.addItem(2,"Jan","2nd Jan","06:00",0);
		clnd.addItem(2,"Mar","2nd Mar","06:00",0);
		clnd.addItem(3,"Jan","3rd Jan first priority","06:00",1);
		clnd.addItem(2,"Jul","2nd Jul","06:00",0);
		clnd.addItem(2,"Dec","2nd Dec","06:00",0);
		clnd.addItem(21,"Jan","21st Jan","06:00",0);
		clnd.addItem(4,"Jan","4th Jan","06:00",0);
		clnd.addItem(4,"Feb","4th Feb","06:00",0);
		clnd.addItem(4,"Jun","4th Jun","06:00",0);
		clnd.addItem(4,"Nov","4th Nov","06:00",0);*/



		//clnd.deletePriorityItem(2,"Jan",6);
		//clnd.clearMonth("Jan");
		//clnd.clearYear();

		clnd.addItem(12,"Jul","12 Jul","06:00",0);
		clnd.addItem(12,"Jul","12 Jul","06:00",1);
		clnd.addItem(12,"Jan","12 Jan","06:00",0);
		clnd.addItem(12,"Sep","12 Sep","06:00",0);
		clnd.addItem(2,"Jan","2 Jan","06:00",0);
		clnd.addItem(12,"Mar","12 Mar","06:00",0);
		clnd.addItem(9,"Jan","9 Jan","06:00",0);
		clnd.addItem(9,"Jan","9 Jan","06:00",0);
		clnd.addItem(12,"Dec","12 Dec","06:00",0);
		clnd.addItem(12,"Dec","12 Dec","06:00",3);
		clnd.addItem(12,"Dec","12 Dec","06:00",2);
		clnd.addItem(2,"Dec","2nd Dec","06:00",2);
		clnd.addItem(24,"Dec","24th Dec","06:00",2);
		clnd.addItem(31,"Dec","31st Dec","06:00",2);

	//	clnd.clearMonth("Dec");
		clnd.clearDay(12);


		System.out.println("end");
	}
}
	

