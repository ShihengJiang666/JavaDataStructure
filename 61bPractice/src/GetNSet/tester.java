package GetNSet;

public class tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a = 5;
		int b = 5;
		Guitar one = new Guitar();
		Guitar two = new Guitar();
		String s1 = "LOVE";
		String s2 = "LOVE";
		one.brand = "FuckYou";
		one.numOfPickups = 5;
		one.rockStarUsesIt = true;
		two.brand = "FuckYou";
		two.numOfPickups = 5;
		two.rockStarUsesIt = true;
		Guitar three = one;
		System.out.println(one == two);
		System.out.println(one.equals(three));
		// TODO Auto-generated method stub

	}

}
