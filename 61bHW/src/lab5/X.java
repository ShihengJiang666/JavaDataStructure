package lab5;

public class X {
	X [] xa;
	public X(){
		xa = new X[3];
		

	}


	public static void main(String[] args){
		X apple = new X();
		Y banana = new Y();
	//	for(int i=0; i<3; i++){
		//	System.out.println(apple.xa.equals(banana.ya));
	//	}
		X donkey = new X();
		donkey.xa = (X[])banana.ya;
		Y horse = new Y();
		horse.ya= (Y[])donkey.xa;
	}
}
//2-3 pm 380 Soda