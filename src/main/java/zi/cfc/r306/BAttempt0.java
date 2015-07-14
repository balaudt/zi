package zi.cfc.r306;
import java.util.Arrays;
import java.util.Scanner;

public class BAttempt0 {

	static final String ROOT = "C:/ft/32/";

	public static void main(String[] args) throws Exception {
//		Scanner scanner = new Scanner(new File(ROOT + "B-sample.in"));
		 Scanner scanner = new Scanner(System.in);
		String[] inStr = scanner.nextLine().split(" ");
		int n = Integer.parseInt(inStr[0]);
		int l = Integer.parseInt(inStr[1]);
		int r = Integer.parseInt(inStr[2]);
		int x = Integer.parseInt(inStr[3]);
		int c[] = new int[n];
		inStr = scanner.nextLine().split(" ");
		for (int i = 0; i < n; i++) {
			c[i] = Integer.parseInt(inStr[i]);
		}
		Arrays.sort(c);
		int count = 0;
		for (int start = 0; start < n - 1; start++) {
			int sum = c[start] + c[start + 1];
			if (sum > r)
				break;
			int minLen = 2;
			while (sum < l && minLen < n - start) {
				sum += c[start + minLen];
				minLen++;
			}
			if (sum < l)
				break;
			minLen--;
			while (c[start + minLen] - c[start] < x && minLen < n - start - 1) {
				minLen++;
				sum += c[start + minLen];
			}
			if (c[start + minLen] - c[start] < x)
				break;
			count++;
			for (int len = minLen + 1; len < n - start && sum <= r; len++) {
				sum += c[start + len];
				count++;
			}
		}
		System.out.println(count);
		scanner.close();
	}
	
}
