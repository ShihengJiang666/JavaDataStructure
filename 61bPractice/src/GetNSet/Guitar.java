package GetNSet;

public class Guitar {
	String brand;
	int numOfPickups;
	boolean rockStarUsesIt;
	
	String getBrand(){
		return brand;
	}
	
	void setBrand(String aBrand){
		brand = aBrand;
	}
	
	int getNumOfPickups(){
		return numOfPickups;
	}
	
	void setNumOfPickups(int num){
		numOfPickups = num;
	}
	
	void setRockStarUsesIt(boolean result){
		rockStarUsesIt = result;
	}
	
	boolean getRockStarUsesIt(){
		return rockStarUsesIt;
	}

}
