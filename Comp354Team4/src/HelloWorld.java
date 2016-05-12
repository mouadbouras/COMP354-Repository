import Models.*;
import DataAccessLayer.*;
public class HelloWorld {
	
	public static void main (String[] args) {
		System.out.println("Hello World!");
		
		
		System.out.println("Hello World2!");
		
		//Mouad : I added this to test if commits form Mac will work on PC
		System.out.println("Hello World3!");
		
		SqliteSetup setup = new SqliteSetup();
		setup.init();
	}
}
