package movie;
public class movieTester{
	public static void main(String[] args){
		Movie one = new Movie();
		one.title = "Fuck you off";
		one.genre = "porn";
		one. rating = -100;
		Movie two = new Movie();
		two.title = "nasty fuck";
		two.genre = "thriller";
		two.rating = -200;
		two.playIt();
	}
}