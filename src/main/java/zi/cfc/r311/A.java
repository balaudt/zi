package zi.cfc.r311;

import java.util.Scanner;

public class A{

	static final String FOLDER = "C:/ft/39/";

	public static void main(String[] args) throws Exception {
//		Scanner scanner = new Scanner(new File(FOLDER + "A-sample.in"));
		 Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(scanner.nextLine());
		int min[] = new int[3];
		int max[] = new int[3];
		int sum = 0, i;
		int out[] = new int[3];
		for (i = 0; i < max.length; i++) {
			String[] inStr = scanner.nextLine().split(" ");
			min[i] = Integer.parseInt(inStr[0]);
			max[i] = Integer.parseInt(inStr[1]);
			sum += min[i];
			out[i] = min[i];
		}
		i = 0;
		while (sum < n) {
			int maxAdd = max[i] - min[i];
			if (n - sum < maxAdd) {
				out[i] += n - sum;
				sum = n;
			} else {
				sum += maxAdd;
				out[i] += maxAdd;
			}
			i++;
		}
		for (int j = 0; j < out.length; j++) {
			System.out.print(out[j] + " ");
		}
		scanner.close();
	}
}
