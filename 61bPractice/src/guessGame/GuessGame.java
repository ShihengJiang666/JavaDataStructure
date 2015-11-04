package guessGame;

public class GuessGame {
	Player p1;
	Player p2;
	Player p3;
	
	public void startGame(){
		p1 = new Player();
		p2 = new Player();
		p3 = new Player();
		String winner = null;
		
		int guessp1 = 0;
		int guessp2 = 0;
		int guessp3 = 0;
		
		boolean p1isRight = false;
		boolean p2isRight = false;
		boolean p3isRight = false;
		
		int target = (int)(Math.random() * 10);
		System.out.println("I'm thinking of a # from 0 to 9...");
		
		while (true) {
			System.out.println("# to guess is " + target);
			
			p1.guess();
			p2.guess();
			p3.guess();
			
			guessp1 = p1.number;
			System.out.println("Player one guessed " + guessp1);
			guessp2 = p2.number;
			System.out.println("Player two guessed " + guessp2);
			guessp3 = p3.number;
			System.out.println("Player three guessed " + guessp3);
			
			if (guessp1 == target){
				p1isRight = true;
				winner = "p1";
			}
			if (guessp2 == target){
				p2isRight = true;
				winner = "p2";
			}
			if (guessp3 == target){
				p3isRight = true;
				winner = "p3";
			}
			
			if (p1isRight || p2isRight || p3isRight){
				System.out.println("Winner:" + winner);
				
			}else{
				System.out.println("Players gotta try again! Good luck!");
			}
			
		}
	}

}
