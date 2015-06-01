package zi.chef.y15.aprCookoff;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B {

	public static final String FOLDER_ROOT = "C:/ft/12/";

	public static void main(String[] args) throws Exception {
//		BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT + "B-sample.in"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(reader.readLine());
			long[] a = new long[n];
			long sum = 0;
			String[] inStr = reader.readLine().split(" ");
			for (int j = 0; j < a.length; j++) {
				a[j] = Long.parseLong(inStr[j]);
				sum += a[j];
			}
			n--;
			for (int j = 0; j < inStr.length; j++) {
				long ans = ((1 - n) * a[j] + sum - a[j]) / n;
				System.out.print(ans + " ");
			}
			System.out.println();
		}
		reader.close();
	}
}
