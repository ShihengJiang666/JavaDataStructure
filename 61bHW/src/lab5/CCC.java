package lab5;

public class CCC extends BBB implements AAA{
	
	public static void main(String[] args){
		AAA bug = new BBB();
		
		System.out.println(bug.a);
	}
}
