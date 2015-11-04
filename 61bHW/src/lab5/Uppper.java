package lab5;

public class Uppper {
	public void bark(){
		System.out.println("wowoowowowo!");
	};
	
	
	public static void main(String[] args){
		Lower low = new Lower();
		((Uppper)low).bark();
//		Uppper up = new Uppper();
	//	((Lower) up).bark();
		Lower low2 = new Lower();
		low2.use();
	}
}
