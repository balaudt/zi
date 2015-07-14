package zi.hrank;
import java.util.Arrays;
import java.util.Scanner;

public class C {

	static final String FOLDER = "/home/bala/temp/7/";

	public static void main(String[] args) throws Exception {
//		Scanner scanner = new Scanner(new File(FOLDER + "C-sample.in"));
		 Scanner scanner = new Scanner(System.in);
		String[] inStr = scanner.nextLine().split(" ");
		int n = Integer.parseInt(inStr[0]);
		int k = Integer.parseInt(inStr[1]);
		inStr = scanner.nextLine().split(" ");
		String[] inStr1 = scanner.nextLine().split(" ");
		int[] boys = new int[n];
		int[] girls = new int[n];
		for (int i = 0; i < n; i++) {
			boys[i] = Integer.parseInt(inStr[i]);
			girls[i] = Integer.parseInt(inStr1[i]);
		}
		Arrays.sort(boys);
		Arrays.sort(girls);
		int bi = 0, gi = 0, ans = 0;
		while (bi < n && gi < n) {
			if (Math.abs(boys[bi] - girls[gi]) <= k) {
				ans++;
				bi++;
				gi++;
				continue;
			}
			if (bi == n - 1 && gi == n - 1)
				break;
			if (bi == n - 1)
				gi++;
			else if (gi == n - 1)
				bi++;
			else {
				int d1 = Math.abs(boys[bi] - girls[gi + 1]);
				int d2 = Math.abs(boys[bi + 1] - girls[gi]);
				if (d1 < d2)
					gi++;
				else
					bi++;
			}
		}
		System.out.println(ans);
		scanner.close();
	}
}
