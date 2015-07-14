package zi.hrank;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Median {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("/home/bala/temp/ground.txt"));
//		Scanner scanner=new Scanner(System.in);
		int n = scanner.nextInt();
		ArrayList<Integer> in = new ArrayList<>();
		DecimalFormat formatter = new DecimalFormat("#0.0");
		for (int i = 0; i < n; i++) {
			int inNum = scanner.nextInt();
			int ip = Collections.binarySearch(in, inNum);
			if (ip >= 0)
				in.add(ip, inNum);
			else {
				ip = -ip - 1;
				in.add(ip, inNum);
			}
			if (i % 2 == 0) {
				System.out.println(formatter.format((double) in.get(i / 2)));
			} else {
				int ind = i / 2;
				System.out.println(formatter.format(((double) in.get(ind) + in.get(ind + 1)) / 2));
			}
		}
		scanner.close();
	}
}
