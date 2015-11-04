package lab5;

public class Lower extends Uppper{
	public void bark(){
		System.out.println("changed!!!");
	};
	public void use(){
		super.bark();
	}
}
