package DotGuess;
import java.util.*;

public class DotComBust {
	private GameHelper helper = new GameHelper();
	private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
	private int numOfGuesses = 0;
	
	private void setUpGame(){
		DotCom one = new DotCom();
		one.setName("Junk.com");
		DotCom two = new DotCom();
		two.setName("Fuck.com");
		DotCom three = new DotCom();
		three.setName("Damn.com");
		dotComsList.add(one);
		dotComsList.add(two);
		dotComsList.add(three);
		
		System.out.println("Your goal is to fuck three assholes!");
		System.out.println("Junk.com, Fuck.com, Damn.com");
		System.out.println("Try to fuck'em up in fewest turns");
		
		for (DotCom dotComToSet : dotComsList){
			ArrayList<String> newLocation = helper.placeDotCom(3);
			dotComToSet.setLocationCells(newLocation);
		}
		
		
		
		}
	private void startPlaying(){
		while (!dotComsList.isEmpty()){
			String userGuess = helper.getUserInput("enter");
			checkUserGuess(userGuess);
		}
	}
	
	private void checkUserGuess(String userGuess){
		numOfGuesses++;
		String result = "miss";
		for (DotCom dotComToTest : dotComsList){
			result = dotComToTest.checkYourself(userGuess);
			if (result.equals("hit")) {
				break;
			}
			if (result.equals("kill")){
				dotComsList.remove(dotComToTest);
				break;
			}
			
		}
		System.out.println(result);
		
	}
	private void finishGame(){
		System.out.println("All assholes are gone!");
		if (numOfGuesses<=18) {
			System.out.println("It took you " + numOfGuesses + " to finish these assholes!");
		}else{
			System.out.println("Fuck urself dumbass. " + numOfGuesses + " steps? How Stupid u are!");
		}
	}
	
	public static void main(String[] args){
		DotComBust game = new DotComBust();
		game.setUpGame();
		game.startPlaying();
	}

}
