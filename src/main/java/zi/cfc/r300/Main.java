package zi.cfc.r300;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		String[] inStr = reader.readLine().split(" ");
		int[] in = new int[n];
		long sum = 0;
		for (int i = 0; i < n; i++) {
			in[i] = Integer.parseInt(inStr[i]);
			sum += in[i];
		}
		int diff = in[1] - in[0], a = in[0];
		if (in[2] - in[1] < diff)
			diff = in[2] - in[1];
		long expSum = a * (n + 1) + diff * (n * (n + 1) / 2);
		System.out.println(expSum - sum);
		reader.close();
	}
}
