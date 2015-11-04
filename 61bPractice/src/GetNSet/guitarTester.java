package GetNSet;

public class guitarTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Guitar one = new Guitar();
		one.brand = "Fuck";
		one.numOfPickups = 56;
		one.rockStarUsesIt = true;
		
		System.out.println("Brand: " + one.brand);
		System.out.println("# of pickups: " + one.numOfPickups);
		System.out.println("Rock Star belonging: " + one.rockStarUsesIt);
		
		one.setBrand("DamnJunk");
		one.setNumOfPickups(80);
		one.setRockStarUsesIt(false);
		
		System.out.println("Brand: " + one.getBrand());
		System.out.println("# of pickups: " + one.getNumOfPickups());
		System.out.println("Rock Star belonging: " + one.getRockStarUsesIt());
		

	}

}
