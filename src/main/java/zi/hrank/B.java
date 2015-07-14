package zi.hrank;
import java.util.Scanner;

public class B {

	static final String FOLDER = "/home/bala/temp/7/";

	public static void main(String[] args) throws Exception {
//		Scanner scanner = new Scanner(new File(FOLDER + "B-sample.in"));
		 Scanner scanner = new Scanner(System.in);
		Integer.parseInt(scanner.nextLine());
		char[] cs = scanner.nextLine().toCharArray();
		int[] freq = new int[26];
		for (int i = 0; i < cs.length; i++) {
			freq[cs[i] - 'a']++;
		}
		long ans = cs.length;
		for (int i = 0; i < freq.length; i++) {
			if (freq[i] == 1 || freq[i] == 0)
				continue;
			ans += freq[i] * (freq[i] - 1) / 2;
		}
		System.out.println(ans);
		scanner.close();
	}
}
