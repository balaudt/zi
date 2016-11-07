package zi.chef.y16.feb;

import java.util.BitSet;
import java.util.Scanner;

public class A {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		BitSet report = new BitSet(n);
		for (int i = 0; i < n; i++) {
			int reportTo = scanner.nextInt() - 1;
			if (reportTo >= 0)
				report.set(reportTo);
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < n; i++) {
			if (!report.get(i))
				builder.append(i + 1).append(' ');
		}
		if (builder.length() > 0)
			builder.deleteCharAt(builder.length() - 1);
		System.out.println(builder.toString());
		scanner.close();
	}
}
