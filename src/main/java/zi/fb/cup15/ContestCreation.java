package zi.fb.cup15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;

public class ContestCreation {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/fb/A/A.in"));
		System.setOut(new PrintStream("/Users/balaudt/Temp/fb/A/A.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(reader.readLine());
			String[] inStr = reader.readLine().split(" ");
			int j = 0, contestCt = 0, lastProblem = 0, ans = 0;
			while (j < n) {
				if (contestCt == 4)
					contestCt = 0;
				if (contestCt == 0) {
					lastProblem = Integer.parseInt(inStr[j]);
					contestCt++;
					j++;
					continue;
				}
				int current = Integer.parseInt(inStr[j]);
				if (current <= lastProblem) {
					ans += 4 - contestCt;
					j++;
					lastProblem = current;
					contestCt = 0;
					continue;
				}
				if (current - lastProblem > 10) {
					while (contestCt < 4 && (current - lastProblem) > 10) {
						lastProblem += 10;
						ans++;
						contestCt++;
					}
					if (contestCt < 4) {
						lastProblem = current;
						j++;
						contestCt++;
					}
				} else {
					j++;
					contestCt++;
					lastProblem = current;
				}
			}
			if (contestCt < 4) {
				ans += 4 - contestCt;
			}
			System.out.println(String.format("Case #%d: %d", i + 1, ans));
		}
		reader.close();
	}
}
