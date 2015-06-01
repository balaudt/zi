package zi.jam.y14.rAChina;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class C {
	public static final String FOLDER_ROOT = "C:/ft/10/";

	public static void main(String[] args) throws Exception {
		// BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT
		// + "C-sample.in"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] str = reader.readLine().split(" ");
		int a = Integer.parseInt(str[0]);
		int b = Integer.parseInt(str[1]);
		int n = Integer.parseInt(str[2]);
		for (int i = 0; i < n; i++) {
			str = reader.readLine().split(" ");
			int l = Integer.parseInt(str[0]);
			int t = Integer.parseInt(str[1]);
			int m = Integer.parseInt(str[2]);
			long height = a + (l - 1) * b;
			if (t < height) {
				System.out.println("-1");
				continue;
			}
			int j;
			int temp = t, count = 0;
			while (temp / (l + count) > 1) {
				temp = temp / (l + count);
				count++;
			}
			m += count;
			for (j = 0; j < m - 1; j++) {
				height += b;
				if (t < height)
					break;
			}
			System.out.println(l + j);
		}
	}
}
