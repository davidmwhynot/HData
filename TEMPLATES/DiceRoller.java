public class DiceRoller {
	public static void main(String[] args) {
		System.out.println("Rolling...");
		for(int n = 0; n < 100000; ++n)
			for(int k = 0; k < 100000; ++k);
		double x = Math.random();
		double y = x * 10;
		System.out.println(y);
	}
}