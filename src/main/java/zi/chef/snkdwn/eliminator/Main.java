package zi.chef.snkdwn.eliminator;
import java.util.Scanner;

public class Main {
	static final String FOLDER = "C:/ft/35/";

	public static void main(String[] args) throws Exception {
		// Scanner scanner = new Scanner(new File(FOLDER + "A-gen.in"));
		Scanner scanner = new Scanner(System.in);
		int t = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < t; i++) {
			char[] in = scanner.nextLine().toCharArray();
			int sum = 0;
			for (int j = 0; j < in.length; j++) {
				sum += in[j] - '0';
				sum %= 9;
			}
			if (sum == 0) {
				System.out.println("0");
				continue;
			}
			int ac = getAddMinCount(sum, in);
			int sc = getSubMinCount(sum, in);
			if (ac >= 0 && ac < sc)
				System.out.println(ac);
			else if (sc >= 0)
				System.out.println(sc);
			else
				System.out.println(ac);
		}
		scanner.close();
	}

	static int getSubMinCount(int sum, char[] in) {
		int origSum = sum;
		if (in[0] != '1')
			sum -= in[0] - '1';
		for (int j = 1; j < in.length & sum > 0; j++) {
			if (in[j] != '0')
				sum -= in[j] - '0';
		}
		return sum > 0 ? -1 : origSum;
	}

	static int getAddMinCount(int sum, char[] in) {
		int origSum = (int) (9 - sum);
		sum = origSum;
		sum -= 9 - in[0] + '0';
		for (int j = 1; j < in.length & sum > 0; j++) {
			if (in[j] != '9')
				sum -= 9 - in[j] + '0';
		}
		return sum > 0 ? -1 : origSum;
	}
}