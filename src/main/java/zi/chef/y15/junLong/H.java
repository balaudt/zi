package zi.chef.y15.junLong;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class H {
	static final String FOLDER = "C:/ft/33/";

	public static void main(String[] args) throws Exception {
		ArrayList<Integer> fibs = new ArrayList<Integer>();
		fibs.add(1);
		fibs.add(1);
		int nextInd = 2;
		while (true) {
			Integer fn_1 = fibs.get(nextInd - 1);
			Integer fn_2 = fibs.get(nextInd - 2);
			if (fn_1 + fn_2 > 99991)
				break;
			fibs.add(fn_1 + fn_2);
			nextInd++;
		}
		System.out.println(fibs);

		Scanner scanner = new Scanner(new File(FOLDER + "H-sample.in"));
		// Scanner scanner = new Scanner(System.in);
		String[] inStr = scanner.nextLine().split(" ");
		int n = Integer.parseInt(inStr[0]);
		int k = Integer.parseInt(inStr[1]);
		inStr = scanner.nextLine().split(" ");
		long sum = 0;
		for (int i = 0; i < n; i++) {
			sum += Integer.parseInt(inStr[i]);
		}
		sum *= k;
		System.out.println(sum);
		scanner.close();
	}

}
