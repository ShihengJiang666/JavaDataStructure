package test;

public class MixFor5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int x = 0;
		int y = 30;
		for (int outter = 0; outter < 3; outter ++){
			for (int inner = 4; inner > 1; inner --){
				x += 3;
				y -= 2;
				if (x == 6){
					break;
				}
				x += 3;
				
			}
			
			y -= 2;
		}
		System.out.println(x + " " + y);
		// TODO Auto-generated method stub

	}

}
