package zi.chef.y15.decLong;
import java.io.PrintStream;
import java.util.Random;

public class DGen {

	static final short S = 10;
	static final short FILTER_MAX = (short) Math.pow(2, S);
	static final Random RANDOM = new Random();

	public static void main(String[] args) throws Exception {
		System.setOut(new PrintStream("/Users/balaudt/Temp/dec/D-gen.in"));
		System.out.println("1");
		printRandStr("wb");
		int filtersCt = RANDOM.nextInt(7)+7;
		System.out.println(filtersCt);
		for (int i = 0; i < filtersCt; i++) {
			printRandStr("+-");
		}
	}

	private static void printRandStr(String str) {
		int in = RANDOM.nextInt(FILTER_MAX);
		for (int i = 0; i < S; i++) {
			if (in % 2 == 0)
				System.out.print(str.charAt(0));
			else
				System.out.print(str.charAt(1));
			in /= 2;
		}
		System.out.println();
	}
}
