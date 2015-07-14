package zi.chef.y15.junLong;
import java.util.Scanner;

public class D {
	static final String FOLDER = "C:/ft/33/";

	public static void main(String[] args) throws Exception {
//		long startT = System.currentTimeMillis();
//		Scanner scanner = new Scanner(new File(FOLDER + "D-gen.in"));
		 Scanner scanner = new Scanner(System.in);
		int t = Integer.parseInt(scanner.nextLine());
		String[] inStr;
		double T1, T2, t1, t2, probA, probB, value;
		for (int i = 0; i < t; i++) {
			inStr = scanner.nextLine().split(" ");
			T1 = Integer.parseInt(inStr[0]);
			T2 = Integer.parseInt(inStr[1]);
			t1 = Integer.parseInt(inStr[2]);
			t2 = Integer.parseInt(inStr[3]);
			probA = t1 == 0 ? 1 / T2 : t1 / T2;
			probB = t2 == 0 ? 1 / T1 : t2 / T1;
			value = probA * probB;
			System.out.printf("%.6f\n", value > 1 ? 1 : value);
		}
//		System.out.println(System.currentTimeMillis() - startT);
		scanner.close();
	}

}
