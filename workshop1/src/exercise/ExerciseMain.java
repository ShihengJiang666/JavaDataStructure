package exercise;

public class ExerciseMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = new String("Prog");
		String s2 = new String("ram");
		String s3 = s1.concat(s2);
		char c = s3.charAt(0);
		int index = s3.indexOf('o');
		int length = s3.length();

		String s4 = s3.replace('a', 'b');
		String s5 = s3.substring(4);
		String s6 = s3.toUpperCase();
		String s7 = s6.toLowerCase();
		String s8 = " Program ";
		String s9 = s8.trim();
		if (s1.startsWith("P")) {
			System.out.println(1+1);
		}

		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		System.out.println(s5);
		System.out.println(s6);
		System.out.println(s7);
		System.out.println(s8);
		System.out.println(s9);
		System.out.println(index);
		System.out.println(length);
	}

}
