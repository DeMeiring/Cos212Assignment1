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
        String[] arr = {"Jan","Feb","Mar","Apr","may","jun","Jul","aug","Sep","Oct","nov","DEC"};
        for(int i=0;i<12;i++){
            clnd.addItem(1,arr[i],arr[i],"06:00",0);
        }

        for(int i=1;i<32;i++){
            clnd.addItem(i,"dec",i+" jan","06:00",0);
        }

		System.out.println("end");
	}
}
	

